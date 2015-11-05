/*

Class storing static methods for converting IR to Tiny Code

*/
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class generateTinyCode
{

	public static List<TinyCode> CodeList = new ArrayList<TinyCode>();

	public static int tempNumber = 1;

    //Map from IR Temp registers to Tiny Code temp registers
	public static Map<String, String> IRTempMap = new HashMap<String, String>();
	//public static currentIRtemp = 0;

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

	public static void createCode(IRNode node)
	{
		TinyCode instr;
		TinyCode.INSTR_TYPE instr_type = TinyCode.INSTR_TYPE.MOVE;
		TinyCode.OPCODE_ARITH op_arith = TinyCode.OPCODE_ARITH.ADDI;
		TinyCode.OPCODE_IO op_io = TinyCode.OPCODE_IO.READI;
		TinyCode.OPCODE_JUMP op_jmp = TinyCode.OPCODE_JUMP.JMP;
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
			case LABEL : instr_type = TinyCode.INSTR_TYPE.LABEL; 
		}

		//System.out.println("instr type : "+instr_type.name());
	
		if(instr_type == TinyCode.INSTR_TYPE.MOVE)
		{
			//System.out.println("Comes here for MOVE INSTR with dest : "+node.dest);
			instr = null;
			dest = node.dest;
			if(generateTinyCode.checkifInt(dest)){
				if(generateTinyCode.IRTempMap.containsKey(dest))
					dest = generateTinyCode.IRTempMap.get(dest);
				else{
					dest = Integer.toString(generateTinyCode.tempNumber++);
					generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.tempNumber - 1));
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

				instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, null, generateTinyCode.tempNumber++, node.imm1);
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
				instr = new TinyCode(TinyCode.INSTR_TYPE.MOVE, node.operand1, Integer.toString(generateTinyCode.tempNumber++), node.imm1);
				generateTinyCode.CodeList.add(instr);
				instr = new TinyCode(instr_type, node.operand2, Integer.toString(generateTinyCode.tempNumber - 1), node.imm2);
				instr.setArithOp(op_arith);
				generateTinyCode.CodeList.add(instr);
				generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.tempNumber - 1));
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
				}
				if(generateTinyCode.checkifInt(node.operand1)){
					op1 = node.operand2;
					dest = generateTinyCode.IRTempMap.get(node.operand1);
					if(generateTinyCode.IRTempMap.containsKey(op1))
						op1 = generateTinyCode.IRTempMap.get(op1);
				}

				if(!generateTinyCode.IRTempMap.containsKey(node.operand1) && (node.opcode == IRNode.OPCODE.DIVF || node.opcode == IRNode.OPCODE.SUBF)){
					dest = Integer.toString(generateTinyCode.tempNumber++);
					generateTinyCode.IRTempMap.put(node.dest, Integer.toString(generateTinyCode.tempNumber - 1));
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
			if(op_jmp != TinyCode.OPCODE_JUMP.JMP)
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
			instr.setTarget(node.labelTarget);
			generateTinyCode.CodeList.add(instr);
		}
	}

}
