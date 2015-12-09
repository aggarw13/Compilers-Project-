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
	public static Map<String, String> tempTrack = new Map<String, String>();
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
		generateTinyCode.availReg = 0;
		for(int index = 0; index < 4; index++)
		{
			if(generateTinyCode.regMap.get(index) == null)
				generateTinyCode.availReg = Integer.parseInt(entry.getKey());
		}
	}

	public static void emptyRegisters()
	{
		for(int i = 1; i <=4; i++)
		{
			generateTinyCode.regMap.put(i - 1, null);
		}
	}

	public static void saveRegisters(TinyCode.OPCODE_STACK stack_op)
	{
		//TinyCode.INSTR_TYPE instr = TinyCode.INSTR_TYPE.STACK;s
		TinyCode node;
		for(int i = 0; i < 4; i ++)
		{	
			node = new TinyCode(TinyCode.INSTR_TYPE.STACK, Integer.toString(i), null, null);
			if(stack_op == TinyCode.OPCODE_STACK.PUSH && generateTinyCode.regNumber < 4)
				generateTinyCode.regNumber++;
			node.setStackOp(stack_op);
			generateTinyCode.CodeList.add(node);
		}
	}

	public static String obtainMemory(IRNode node, String var)
	{
		String regno = ;
		for(EntrySet<String, regData> entry : generateTinyCode.regMap)
		{
			if(entry.getValue().loc.equals(var))
				return entry.getKey();
		}
		return generateTinyCode.allocateRegister(node, var);
	}

	//Alocate register for memory, temp variable
	public static void allocateRegister(IRnode node, String var)
	{	
		boolean allocated = false;
		for(EntrySet<String, regData> entry : generateTinyCode.regMap)
		{


		}

		if(generateTinyCode.checkifInt(var))
		{
			generate;
		}

	}

	public static void spillRegister(int regno)
	{
		generateTinyCode.regMap.get(regno).dirty = 0;
		String var = generateTinyCode.regMap.get(regno - 1).loc, dest = var;
		if(genetrateTinyCode.checkifInt(var))
		{
			dest = "L" + generateTinyCode.tempTrack.get(var);
		}
		generateTinyCode.CodeList.add(new TinyCode(TinyCode.INSTR_TYPE.MOVE, regno, dest,);
		generateTinyCode.regMap.set(regno - 1, null);
		generateTinyCode.availReg = regno;
	}

	public static void updateMemoryVar()
	{
		for(regData data : generateTinyCode.regMap)
		{
			if(!SemanticDataHandler.globalScope.checkSymbolValidity(data.local))
				&& data.dirty == 1 && generateTinyCode.spillRegister();
			if(!generateTinyCode.checkifInt(entry.getValue())
			 && generateTinyCode.entry.getValue().substring(0,1).equals("L"))
				generateTinyCode.spillRegister(entry.getKey());								
		}
		//generateTinyCode.put(entry.getKey(), null);
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

		//System.out.println("instr type : "+instr_type.name());
	
		if(instr_type == TinyCode.INSTR_TYPE.MOVE)
		{
			//System.out.println("Comes here for MOVE INSTR with dest : "+node.dest);
			instr = null;
			dest = generateTinyCode.obtainMemory(node.dest);
			if(generateTinyCode.checkifInt(dest)){
				if(generateTinyCode.IRTempMap.containsKey(dest))
					dest = generateTinyCode.IRTempMap.get(dest);
				else{
					dest = Integer.toString(generateTinyCode.regNumber++);
					generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber - 1));
				}
			}
			if(node.operand1 != null){
				op1 = node.operand1;
				if(generateTinyCode.IRTempMap.containsKey(node.operand1))
						op1 = generateTinyCode.IRTempMap.get(op1);
				instr = new TinyCode(instr_type, op1, dest, null);}
			else if(node.imm1 != null)
				instr = new TinyCode(instr_type, null, dest, node.imm1);
			generateTinyCode.CodeList.add(instr);
		}

		else if(instr_type == TinyCode.INSTR_TYPE.ALU)
		{

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
			}

			//Case 2: none of the source operands is register address
			if(!generateTinyCode.checkifInt(node.operand1) && !generateTinyCode.checkifInt(node.operand2))
			{
				//op1 = node.operand1, op2 = node.operand2; 
				/*if(node.operand2 == null)
					op1 = node.imm1;
				if(node.operand2 == null)
					op2 = node.imm2;*/
				instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand1, Integer.toString(generateTinyCode.regNumber++), node.imm1);
				generateTinyCode.CodeList.add(instr);
				instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand2, Integer.toString(generateTinyCode.regNumber++), node.imm2);
				generateTinyCode.CodeList.add(instr);
				instr = new TinyCode(instr_type, Integer.toString(generateTinyCode.regNumber - 1), Integer.toString(generateTinyCode.regNumber - 2), null);
				instr.setArithOp(op_arith);
				generateTinyCode.CodeList.add(instr);
				generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.regNumber - 2));
				//generate MOVE code
				//generate 1st ALU code
			}

			//Case3 : Atleast one of the operands is a register address
			else
			{
				String interm, imm;
				imm = (node.imm1 != null)? node.imm1 : node.imm2;
				//op1 = (node.operand1 != null)? node.operand1 : node.imm1;
				//dest = (node.operand2 != null)? node.operand2 : node.imm2;

				//Check for Division Condition
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
				}

				instr = new TinyCode(instr_type, op1, dest, imm);
				instr.setArithOp(op_arith);
				generateTinyCode.IRTempMap.put(node.dest, dest);
				generateTinyCode.CodeList.add(instr);
			}
		}
		else if(instr_type == TinyCode.INSTR_TYPE.IO)
		{
			//Check if destination is temp register or memory address 
			instr = new TinyCode(instr_type, null, node.dest, null);
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
			op1 = (node.operand1 != null)? (generateTinyCode.IRTempMap.containsKey(node.operand1)? generateTinyCode.IRTempMap.get(node.operand1) : node.operand1 ): node.imm1;
			if(op_jmp == TinyCode.OPCODE_JUMP.JSR)
				generateTinyCode.saveRegisters(TinyCode.OPCODE_STACK.PUSH);
			else if(op_jmp != TinyCode.OPCODE_JUMP.JMP)
			{
				TinyCode.OPCODE_JUMP cmp_type = TinyCode.OPCODE_JUMP.CMPR;
				if(node.cmprType == VARTYPE.INT)
					cmp_type = TinyCode.OPCODE_JUMP.CMPI;
				instr = new TinyCode(TinyCode.INSTR_TYPE.CMP, op1, generateTinyCode.IRTempMap.get(node.operand2), null);		
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
			op1 = (op_stack == TinyCode.OPCODE_STACK.PUSH)? node.operand1 : node.dest;
			if(op1 != null && generateTinyCode.checkifInt(op1))
			{
				if(generateTinyCode.IRTempMap.containsKey(op1))
					op1 = generateTinyCode.IRTempMap.get(op1);
				else
				{
					op1 = Integer.toString(generateTinyCode.regNumber++);
					generateTinyCode.IRTempMap.put(node.dest, op1);				
				}
			}

			instr = new TinyCode(instr_type, op1, null, null);
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

