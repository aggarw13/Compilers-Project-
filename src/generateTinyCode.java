/*

Class storing static methods for converting IR to Tiny Code

*/
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class regData
{	
	public boolean dirty;
	public String loc;
	public String temp = null;

	regData(String var)
	{		
		dirty = false;
		loc = var;
	}

	regData(String var, boolean status)
	{
		dirty = status;
		loc = var;
	}

	public boolean tempCheck(String opt_map, String new_loc)
	{
		if(opt_map == null || temp == null)
			return false;
		else if(opt_map.equals(temp))
			this.loc = new_loc;
		return true;
	}

}

class generateTinyCode
{

	public static List<TinyCode> CodeList = new ArrayList<TinyCode>();

	public static int regNumber = 0;
	public static int availReg = 0;

	//Resest stack this on function call
	public static int stackOffset = 0; 

    //Map from IR Temp registers to Tiny Code temp registers
	public static Map<String, String> IRTempMap = new HashMap<String, String>();
	//public static Stack<Map<String,String>> tempMapStack = new Stack<Map<String,String>>();
	public static List<regData> regMap = new ArrayList<regData>(4);
	public static Map<String, String> tempTrack = new HashMap<String, String>();
	//public static Stack<Integer> regStack = new Stack<Integer>();
	//public static currentIRtemp = 0;

	/*Register Allocation Algo
     1. Check current status of Registers
     2. 
*/
	public static boolean checkifInt(String dest)
	{
		try {
    		Integer.parseInt(dest);
    		return true;
			}
		catch (NumberFormatException e) {
    		return false;
    	}
	}

	/* TODO : Need to change to accommodate variables from all scopes */
	public static void allocateMemory()
	{
		SymbolTable scope = SemanticDataHandler.globalScope;
		Symbol record; 
		for(String name : scope.getRecords())
		{	
			record = scope.getRecord(name);
			
			switch(record.getType())
			{
				case FLOAT :
				generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.VAR, record.getName(), null ,null)); 
				break;

				case INT :
				generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.VAR, record.getName(), null ,null)); 
				break;

				case STRING:
				TinyCode instr = new TinyCode(TinyCode.INSTR_TYPE.STR, record.getName(), null ,null); 
				instr.setStrVal(record.getVal());
				generateTinyCode.CodeList.add(instr);
			}	
		}
	}

	public static void mainJumpCode()
	{
		TinyCode node = new TinyCode(TinyCode.INSTR_TYPE.STACK, null,null,null);
		node.setStackOp(TinyCode.OPCODE_STACK.PUSH);
		generateTinyCode.CodeList.add(node);
		generateTinyCode.saveRegisters(TinyCode.OPCODE_STACK.PUSH);
		node = new TinyCode(TinyCode.INSTR_TYPE.JMP, null, null, null);
		node.setJmpOp(TinyCode.OPCODE_JUMP.JSR);
		node.setTarget(new String("main"));
		generateTinyCode.CodeList.add(node);
		generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.HALT, null, null, null));
	}

	public static boolean getAvailableRegister()
	{
		generateTinyCode.availReg = -1;
		for(int index = 0; index < 4; index++)
		{
			if(generateTinyCode.regMap.get(index) == null)
			{
				generateTinyCode.availReg = index;
				//System.out.println("Available register "+ generateTinyCode.availReg);
				return true;
			}
		}
		return false;
	}

	public static void emptyRegisters(IRNode node)
	{	
		//System.out.println("rEGISTER sIZE : "+ generateTinyCode.regMap.size());
		if(node != null)
			generateTinyCode.updateMemoryVar(node);
		for(int i = 0; i < 4; i++)
		{
			if(generateTinyCode.regMap.size() < 4)
				generateTinyCode.regMap.add(null);
			else 
				generateTinyCode.regMap.set(i, null);
		}

		//System.out.println("Size of Reg list "+generateTinyCode.regMap.size());
	}

	public static void saveRegisters(TinyCode.OPCODE_STACK stack_op)
	{
		//TinyCode.INSTR_TYPE instr = TinyCode.INSTR_TYPE.STACK;s
		TinyCode node;
		if(stack_op == TinyCode.OPCODE_STACK.PUSH)
		{
			for(int i = 0; i < 4; i++)
			{	
				node = new TinyCode(TinyCode.INSTR_TYPE.STACK, Integer.toString(i), null, null);
				//if(stack_op == TinyCode.OPCODE_STACK.PUSH && generateTinyCode.regNumber < 4)
				//	generateTinyCode.regNumber++;
				node.setStackOp(stack_op);
				generateTinyCode.CodeList.add(node);
			}
		}
		else
		{
			for(int i = 3; i >= 0; i--)
			{	
				node = new TinyCode(TinyCode.INSTR_TYPE.STACK, Integer.toString(i), null, null);
				//if(stack_op == TinyCode.OPCODE_STACK.PUSH && generateTinyCode.regNumber < 4)
				//	generateTinyCode.regNumber++;
				node.setStackOp(stack_op);
				generateTinyCode.CodeList.add(node);
			}

		}	

	}

	public static String obtainMemory(IRNode node, String var, String temp_map)
	{
		if(var == null)
			return null;
		int index = 0;
		String regno;
		//System.out.println("Obtaining memory for " + var);
		for(regData data : generateTinyCode.regMap)
		{
			//if(data != null && index == 0)
				//System.out.println("Reg Mapping of "+index+" to "+data.loc);
			if(data != null && (data.loc.equals(var) || data.tempCheck(temp_map, var)))
				return Integer.toString(index);
			index++;
		}

		regno = generateTinyCode.allocateRegister(node, var, temp_map);

		if(node.live_in.contains(var))
		{	
			String src = var;
			if(generateTinyCode.checkifInt(var))
			{
				src = "$L" + generateTinyCode.tempTrack.get(generateTinyCode.IRTempMap.get(var));
			}
			generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.MOVE, src, regno, null));
		}
		//Fail Safe recomputation of  avail Reg
		//generateTinyCode.getAvailableRegister();
		return regno;
	}

	//Allocate register for memory, temp variable
	public static String allocateRegister(IRNode node, String var, String temp_map)
	{	
		boolean full = false;
		int allocReg = -1, dirty_live_reg = -1, live_reg = -1;

		generateTinyCode.getAvailableRegister();

		for(int index = 0; index < 4; index++)
		{
			if(index != generateTinyCode.availReg)
			{				
				full = true;
				if(node.live_out.contains(generateTinyCode.regMap.get(index).loc))	
				{
					dirty_live_reg = (generateTinyCode.regMap.get(index).dirty)? index : dirty_live_reg; 
					live_reg = index;
				}
				else if(!node.live_in.contains(generateTinyCode.regMap.get(index).loc))
				{
					allocReg = index;
					full = false;
					break;
				}
			}
			else
			{
				full = false;
				allocReg = generateTinyCode.availReg;
				break;
			}
		}
	
		if(full)
		{
			if(dirty_live_reg != -1)
			{
				//System.out.println("Chosen Dirty Register for spilling "+dirty_live_reg);
				allocReg = dirty_live_reg;	
				generateTinyCode.spillRegister(dirty_live_reg);
			}
			else 
				allocReg = live_reg;
		}
		/*else if(allocReg == -1)
		{
			generateTinyCode.getAvailableRegister();
			allocReg = generateTinyCode.availReg;
		}*/

		//System.out.println("Allocation chosen for "+var+ " "+allocReg);
		
		//System.out.println("Allocation Chosen reg :"+allocReg);

		if(generateTinyCode.checkifInt(var))
			generateTinyCode.regMap.set(allocReg, new regData(var, true));
		else
			generateTinyCode.regMap.set(allocReg, new regData(var));

		if(generateTinyCode.checkifInt(var))
			generateTinyCode.regMap.get(allocReg).temp = temp_map;

		//System.out.println("Reg 0 occupies  "+generateTinyCode.regMap.get(0).loc);	

		return Integer.toString(allocReg);
	}

	public static void spillRegister(int regno)
	{
		System.out.println("Chosen Register for Spilling" + regno);
		generateTinyCode.regMap.get(regno).dirty = false;
		String var = generateTinyCode.regMap.get(regno).loc, dest = var;
		//Remeber : Reg Data contains IR Temp number AND IRTempMap, TempTrack contains optimized Temp number
		if(generateTinyCode.checkifInt(generateTinyCode.regMap.get(regno).loc))
		{
			//DEBUG_CHECK : Temp Stack Offset needs to be consistent
			generateTinyCode.tempTrack.put(generateTinyCode.IRTempMap.get(var), Integer.toString(generateTinyCode.stackOffset++));
			TinyCode temp_spill = new TinyCode(TinyCode.INSTR_TYPE.STACK, Integer.toString(regno), dest, null);
			temp_spill.setStackOp(TinyCode.OPCODE_STACK.PUSH);
			generateTinyCode.CodeList.add(temp_spill);
		}
		else
			generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.MOVE, Integer.toString(regno), dest, null));
		generateTinyCode.regMap.set(regno, null);
		generateTinyCode.availReg = regno;
	}

	public static void updateMemoryVar(IRNode node)
	{
		int index = 1;
		for(regData data : generateTinyCode.regMap)
		{
			if(data == null)
				continue;

			if(node.opcode == IRNode.OPCODE.RET && (generateTinyCode.checkifInt(data.loc) || data.loc.substring(0,2).equals("$L")))
				continue;

			if(data.dirty && node.live_out.contains(data.loc))
				generateTinyCode.spillRegister(index);
			/*if(!SemanticDataHandler.globalScope.checkSymbolValidity(data.loc))
				&& data.dirty == 1 && generateTinyCode.spillRegister(index);
			if(!generateTinyCode.checkifInt(data.loc)
			 && generateTinyCode.data.loc.substring(0,1).equals("L"))
				generateTinyCode.spillRegister(index);*/								
			index++;
		}
		//generateTinyCode.put(entry.getKey(), null);
	}

	public static void updateReg(String old_map, String new_var, String new_temp)
	{
		for(regData data : generateTinyCode.regMap)
		{
			if(data != null && data.loc.equals(old_map))
			{
				data.loc = new_var;
				data.temp = new_temp;
				break;
			}
		}
	}


	public static void createCode(IRNode node)
	{
		TinyCode instr;
		TinyCode.INSTR_TYPE instr_type = TinyCode.INSTR_TYPE.MOVE;
		TinyCode.OPCODE_ARITH op_arith = TinyCode.OPCODE_ARITH.ADDI;
		TinyCode.OPCODE_IO op_io = TinyCode.OPCODE_IO.READI;
		TinyCode.OPCODE_JUMP op_jmp = TinyCode.OPCODE_JUMP.JMP;
		TinyCode.OPCODE_STACK op_stack = TinyCode.OPCODE_STACK.PUSH;
		String dest = null, op1 = null, target = null;
		switch(node.opcode)
		{ 
			case ADDI :  instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.ADDI; break;
			case ADDF :	 instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.ADDR; break;
			case SUBI :  instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.SUBI; break;
			case SUBF :  instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.SUBR; break; 
			case MULTI : instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.MULI; break;
			case MULTF :  instr_type = TinyCode.INSTR_TYPE.ALU; op_arith = TinyCode.OPCODE_ARITH.MULR; break;
			case DIVI :  instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.DIVI; break;
			case DIVF :  instr_type = TinyCode.INSTR_TYPE.ALU;  op_arith = TinyCode.OPCODE_ARITH.DIVR; break;
			case STOREI : instr_type = TinyCode.INSTR_TYPE.MOVE; break;
			case STOREF : instr_type = TinyCode.INSTR_TYPE.MOVE; break;
			case READI :  instr_type = TinyCode.INSTR_TYPE.IO;  op_io = TinyCode.OPCODE_IO.READI; break;
			case READF :  instr_type = TinyCode.INSTR_TYPE.IO;  op_io = TinyCode.OPCODE_IO.READR; break;
			case READS : instr_type = TinyCode.INSTR_TYPE.IO;  op_io = TinyCode.OPCODE_IO.READS; break;
			case WRITEI : instr_type = TinyCode.INSTR_TYPE.IO;  op_io = TinyCode.OPCODE_IO.WRITEI; break;
			case WRITES : instr_type = TinyCode.INSTR_TYPE.IO;   op_io = TinyCode.OPCODE_IO.WRITES; break;
			case WRITEF : instr_type = TinyCode.INSTR_TYPE.IO;  op_io = TinyCode.OPCODE_IO.WRITER; break;
			case JUMP : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JMP; break;
			case GE : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JGE; break;
			case EQ : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JEQ; break;
			case NE : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JNE; break;
			case LE : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JLE; break;
			case LT : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JLT; break;
			case GT : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JGT; break; 
			case JSR : instr_type = TinyCode.INSTR_TYPE.JMP; op_jmp = TinyCode.OPCODE_JUMP.JSR; break;
			case LABEL : instr_type = TinyCode.INSTR_TYPE.LABEL; break;
			case PUSH : instr_type =  TinyCode.INSTR_TYPE.STACK; break;  
			case POP : instr_type = TinyCode.INSTR_TYPE.STACK; op_stack = TinyCode.OPCODE_STACK.POP; break;
			case LINK : instr_type = TinyCode.INSTR_TYPE.STACK;	op_stack = TinyCode.OPCODE_STACK.LINK; break;
			case RET : instr_type = TinyCode.INSTR_TYPE.STACK;	op_stack = TinyCode.OPCODE_STACK.RET;
		}
		

		if(instr_type == TinyCode.INSTR_TYPE.MOVE)
		{
			//System.out.println("Comes here for MOVE INSTR with dest : "+node.dest);
			instr = null;
			dest = node.dest;
			if(generateTinyCode.checkifInt(dest)){
				if(generateTinyCode.IRTempMap.containsKey(dest))
					dest = generateTinyCode.IRTempMap.get(dest);
				else{
					dest = Integer.toString(generateTinyCode.regNumber++);
					generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber - 1));
				}
				dest = generateTinyCode.obtainMemory(node, node.dest, generateTinyCode.IRTempMap.get(dest));
			}

			if(node.operand1 != null)
			{
				op1 = node.operand1;
				if(generateTinyCode.IRTempMap.containsKey(node.operand1))
				{
					op1 = generateTinyCode.obtainMemory(node, node.operand1, generateTinyCode.IRTempMap.get(op1));
					generateTinyCode.regMap.get(Integer.parseInt(op1)).dirty = false;
				}
				instr = new TinyCode(instr_type, op1, dest, null);
				
				op1 = node.operand1;
			}
			else if(node.imm1 != null)
			{
				instr = new TinyCode(instr_type, null, dest, node.imm1);
				op1 = node.imm1;
			}
			
			generateTinyCode.CodeList.add(instr);
			//if(generateTinyCode.checkifInt(dest))
			//	generateTinyCode.updateReg(dest, op1, generateTinyCode.IRTempMap.get(op1));			
		}

		else if(instr_type == TinyCode.INSTR_TYPE.ALU)
		{
			/*


			//Case 1 : When both of the IR node operands are immediate values
			if(node.imm1 != null && node.imm2 != null)
			{
				//TODO : Posible Optimization for Expression with operands as Integer 
				/*if(node.opcode.name().substring(opcode.name().length() - 1) == "I")
				*/
				/* ----Rest fo the Code for Floating point Literal Operands--- */
				/*
				instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, null, generateTinyCode.regNumber++, node.imm1);
				generateTinyCode.CodeList.add(instr);
				if(node)
				op2 = (node.operand1 != null)?
				instr = new	*/		
			

			//Case 2: none of the source operands is register address
			if(!generateTinyCode.checkifInt(node.operand1) && !generateTinyCode.checkifInt(node.operand2) && node.operand1 != null && node.operand2 != null)
			{
				String op2 = null;
				//op1 = node.operand1, op2 = node.operand2; 
				/*if(node.operand2 == null)
					op1 = node.imm1;
				if(node.operand2 == null)
					op2 = node.imm2;*/
				//instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand1, Integer.toString(generateTinyCode.regNumber++), node.imm1);
				//generateTinyCode.CodeList.add(instr);
				//instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand2, Integer.toString(generateTinyCode.regNumber++), node.imm2);
				//generateTinyCode.CodeList.add(instr);
				op1 = generateTinyCode.obtainMemory(node, node.operand1, null);
				op2 = generateTinyCode.obtainMemory(node, node.operand2, null);
				generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber++));
				dest = generateTinyCode.obtainMemory(node, node.dest, dest);

				//POSSIBLE OPTIMIZATION OF REGISTER ALLOCATION FOR OP1
				generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.MOVE, op1, dest, null));
				instr = new TinyCode(instr_type, op2, dest, null);
				instr.setArithOp(op_arith);
				generateTinyCode.CodeList.add(instr);
				//generate MOVE code
				//generate 1st ALU code
			}

			//Case3 : Atleast one of the operands is a register address
			else
			{
				String interm, imm;
				imm = (node.imm1 != null)? node.imm1 : node.imm2;

				String map, op2 = null;

				op1 =node.operand1;

				if(node.operand2 != null)
				{
					if(generateTinyCode.checkifInt(node.operand2))
					{
						if((map = generateTinyCode.IRTempMap.get(node.operand2)) == null)
						{
							generateTinyCode.IRTempMap.put(node.operand2, Integer.toString(generateTinyCode.regNumber++));
							map = Integer.toString(generateTinyCode.regNumber - 1);
						}
						op2 = generateTinyCode.obtainMemory(node, node.operand2, map);
					}
					else
						op2 = node.operand2;
				}

				System.out.println("Operand 1 is "+node.operand1);

				if(node.operand1 != null)
				{
					map = null;
					if(generateTinyCode.checkifInt(node.operand1) && (map = generateTinyCode.IRTempMap.get(node.operand1)) == null)
					{
						generateTinyCode.IRTempMap.put(node.operand1, Integer.toString(generateTinyCode.regNumber++));
						map = Integer.toString(generateTinyCode.regNumber - 1);
					}
					op1 = generateTinyCode.obtainMemory(node, node.operand1, map);
				}

				//System.out.println("Allocation of valid operand1");

				dest = Integer.toString(generateTinyCode.regNumber++);
				dest = generateTinyCode.obtainMemory(node, node.dest, dest);
				generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber - 1));
				generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.MOVE, op1, dest, node.imm1));
				
				//System.out.println("Guranteed allocation of register");

				//dest = (node.operand2 != null)? node.operand2 : node.imm2;

				//Check for Division Condition
/*

				if(generateTinyCode.checkifInt(node.operand2))
				{
					op1 = node.operand1;
					dest = generateTinyCode.IRTempMap.get(node.operand2);
					if(generateTinyCode.IRTempMap.containsKey(op1))
						op1 = generateTinyCode.IRTempMap.get(op1);
					else if(!generateTinyCode.checkifInt(node.operand1) && node.operand1 != null)
					{	
						instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand1, Integer.toString(generateTinyCode.regNumber++), null);	
						generateTinyCode.CodeList.add(instr);
						//generateTinyCode.IRTempMap.put(node.operand1, generateTinyCode.regNumber++);
						op1 = Integer.toString(generateTinyCode.regNumber - 1);
					}
				}
				if(generateTinyCode.checkifInt(node.operand1)){
					op1 = node.operand2;
					dest = generateTinyCode.IRTempMap.get(node.operand1);
					if(generateTinyCode.IRTempMap.containsKey(op1))
						op1 = generateTinyCode.IRTempMap.get(op1);
					else if(!generateTinyCode.checkifInt(node.operand2) && node.operand2 != null)
					{	
						instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand2, Integer.toString(generateTinyCode.regNumber++), null);
						generateTinyCode.CodeList.add(instr);
						op1 = Integer.toString(generateTinyCode.regNumber - 1);
					}
				}

				if(!generateTinyCode.IRTempMap.containsKey(node.operand1) && (node.opcode == IRNode.OPCODE.DIVF || node.opcode == IRNode.OPCODE.SUBF)){
					//dest = Integer.toString(generateTinyCode.regNumber++);
					op1 = generateTinyCode.IRTempMap.get(node.operand2);
					if(node.operand1 == null)
					{
						instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.imm1, Integer.toString(generateTinyCode.regNumber++), null);
						dest = Integer.toString(generateTinyCode.regNumber++);
						generateTinyCode.CodeList.add(instr);
					}
					else 
						dest = Integer.toString(generateTinyCode.regNumber - 1);
					//generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber - 1));
				}*/

				instr = new TinyCode(instr_type, op2, dest, imm);
				//generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber - 1));
				instr.setArithOp(op_arith);
				generateTinyCode.CodeList.add(instr);
			}
		}
		else if(instr_type == TinyCode.INSTR_TYPE.IO)
		{
			//Check if destination is temp register or memory address 
			//POSSIBLE REDUNDANCY
			instr = new TinyCode(instr_type, null, node.dest,  null);
			instr.setIOOp(op_io);
			generateTinyCode.CodeList.add(instr);
		}

		else if(instr_type == TinyCode.INSTR_TYPE.LABEL)
		{
			instr = new TinyCode(instr_type, null, node.dest, null);
			instr.setTarget(node.labelTarget);
			generateTinyCode.CodeList.add(instr);
		}

		else if(instr_type == TinyCode.INSTR_TYPE.JMP)
		{
			op1 = (node.operand1 != null)? (generateTinyCode.checkifInt(node.operand1)? generateTinyCode.obtainMemory(node, node.operand1, generateTinyCode.IRTempMap.get(node.operand1)) : node.operand1) : node.imm1;
			if(op_jmp == TinyCode.OPCODE_JUMP.JSR)
				generateTinyCode.saveRegisters(TinyCode.OPCODE_STACK.PUSH);
			else if(op_jmp != TinyCode.OPCODE_JUMP.JMP)
			{
				TinyCode.OPCODE_JUMP cmp_type = TinyCode.OPCODE_JUMP.CMPR;
				if(node.cmprType == VARTYPE.INT)
					cmp_type = TinyCode.OPCODE_JUMP.CMPI;
				instr = new TinyCode(TinyCode.INSTR_TYPE.CMP, op1 , generateTinyCode.obtainMemory(node, node.operand2, generateTinyCode.IRTempMap.get(node.operand2)), null);		
				instr.setJmpOp(cmp_type);
				generateTinyCode.CodeList.add(instr);
			}
			instr = new TinyCode(instr_type, null, node.dest, null);
			instr.setJmpOp(op_jmp);
			if(op_jmp == TinyCode.OPCODE_JUMP.JSR)
				instr.setTarget(node.dest);
			else
				instr.setTarget(node.labelTarget);
			generateTinyCode.CodeList.add(instr);
			if(op_jmp == TinyCode.OPCODE_JUMP.JSR)
				generateTinyCode.saveRegisters(TinyCode.OPCODE_STACK.POP);
		}
		else if(instr_type == TinyCode.INSTR_TYPE.STACK)
		{	
			/*if(op_stack == TinyCode.OPCODE_STACK.PUSH) 
			{
				op1 = node.operand1; 
			}
			else
			{*/
			String temp = null;
			op1 = (op_stack == TinyCode.OPCODE_STACK.PUSH)? node.operand1 : node.dest;
			if(op1 != null && generateTinyCode.checkifInt(op1))
			{
				if(generateTinyCode.IRTempMap.containsKey(op1))
					temp = generateTinyCode.IRTempMap.get(op1);
				else
				{
					temp = Integer.toString(generateTinyCode.regNumber++);
					generateTinyCode.IRTempMap.put(node.dest, op1);				
				}
			}

			op1 = generateTinyCode.obtainMemory(node, op1, temp);

			instr = new TinyCode(instr_type, op1, null, null);

			//POSSIBLE OPTIMIZATION OF LINK COUNT FOR UPDATING ALL function temp counts after register allocation 
			if(op_stack == TinyCode.OPCODE_STACK.LINK)		

				instr.setLinkCount(ASTStackHandler.currFunct.localVar);
			else if(op_stack == TinyCode.OPCODE_STACK.RET)
			{	
				instr.setStackOp(TinyCode.OPCODE_STACK.UNLNK);
				generateTinyCode.CodeList.add(instr);
				instr = new TinyCode(instr_type, null, null, null);
			}	
			instr.setStackOp(op_stack);
			generateTinyCode.CodeList.add(instr);
		}
	}

}