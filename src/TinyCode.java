/*

Class representing Tiny Code 

*/
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

 class TinyCode
 {
 	//public static List<IRNode> IRList = new ArrayList<IRNode>(); 
 	public enum INSTR_TYPE
 	{
 		MOVE,
 		ALU,
 		CMP,
 		JMP,
 		IO,
 		LABEL,
 		STACK,
 		HALT,
 		VAR,
 		STR
  	}

 	public enum OPCODE_ARITH{
 		ADDI,
 		ADDR,
 		SUBI,
 		SUBR,
 		MULI,
 		MULR,
 		DIVI,
 		DIVR,
 		INCI,
 		DECI,
  	}

 	public enum OPCODE_JUMP
 	{
 		JMP,
 		JGT,
 		JLT,
 		JLE,
 		JGE,
 		JNE,
 		JEQ,
 		CMPR,
 		CMPI,
 		JSR
 	}

 	public enum OPCODE_IO
 	{	
 		READI,
 		READR,
 		READS,
 		WRITEI,
 		WRITER,
 		WRITES
 	}

 	public enum OPCODE_STACK
 	{
 		PUSH,
 		POP,
 		LINK,
 		UNLNK,
 		RET
 	}

 	public INSTR_TYPE instr_type;
 	public OPCODE_ARITH op_arith;
 	public OPCODE_IO op_io;
 	public OPCODE_JUMP op_jmp;
 	public OPCODE_STACK op_stack;

	public String target = null;
	public String operand1 = null, dest = null, imm = null;
	public String strValue = null;
	public int linkCount = 0;

	public TinyCode(TinyCode.INSTR_TYPE opcode, String op1, String dest, String imm)
	{
		this.instr_type = opcode;
		this.operand1 = this.setSrcDest(op1);
		this.dest = this.setSrcDest(dest);
		this.imm = imm;
	}

	private String setSrcDest(String id)
	{
		String updated_id = id;
		if(id != null)
		{
			if(generateTinyCode.checkifInt(id))
				 updated_id = "r" + id;
			else if(id.equals("$R"))
				updated_id = "$" + Integer.toString(5 + ASTStackHandler.currFunct.paramCount + 1);
			else if(id.length() > 2)
			{
				if(id.substring(0,2).equals("$L"))
					updated_id = "$-" + id.substring(2);
				else if(id.substring(0,2).equals("$P"))
					updated_id = "$" + (5 + Integer.valueOf(id.substring(2))); 
			}
		}	
		return updated_id;
	}

	public void setArithOp(TinyCode.OPCODE_ARITH opcode)
	{
		this.op_arith = opcode;
	}

	public void setIOOp(TinyCode.OPCODE_IO opcode)
	{
		this.op_io = opcode;
	}

	public void setJmpOp(TinyCode.OPCODE_JUMP opcode)
	{
		this.op_jmp = opcode;
	}

	public void setStackOp(TinyCode.OPCODE_STACK opcode)
	{
		this.op_stack = opcode;
	}

	public void setStrVal(String value)
	{
		this.strValue = value;
	}

	public void setLinkCount(int count)
	{
		this.linkCount = count;
	}
	public void printInstr()
	{
		//System.out.println("Tiny Instruction Type :"+this.instr_type.name());
		String src = (this.operand1 != null)? this.operand1 : this.imm;
		String target = this.target;
		if(generateTinyCode.checkifInt(target))
			target = "label" + target;
		if(this.instr_type == TinyCode.INSTR_TYPE.VAR)
			System.out.println("var "+this.operand1);
		else if(this.instr_type == TinyCode.INSTR_TYPE.STR)
			System.out.println("str "+this.operand1+" "+this.strValue);
		else if(this.instr_type == TinyCode.INSTR_TYPE.MOVE)
			System.out.println("move " + src+" "+this.dest);
		else if(this.instr_type == TinyCode.INSTR_TYPE.ALU)
			System.out.println(this.op_arith.name().toLowerCase() + " "+ src+" "+this.dest);
		else if(this.instr_type == TinyCode.INSTR_TYPE.IO)
			System.out.println("sys "+this.op_io.name().toLowerCase() +" "+this.dest);
		else if(this.instr_type == TinyCode.INSTR_TYPE.CMP)
			System.out.println(this.op_jmp.name().toLowerCase() + " " +  src + " " + this.dest);
		else if(this.instr_type == TinyCode.INSTR_TYPE.JMP)
			System.out.println(this.op_jmp.name().toLowerCase() + " " + target);
		else if(this.instr_type == TinyCode.INSTR_TYPE.LABEL)
			System.out.println("label "+ target);
		else if(this.instr_type == TinyCode.INSTR_TYPE.STACK)
		{	
			if(op_stack == TinyCode.OPCODE_STACK.LINK)
				System.out.println("link "+this.linkCount);
			else if(this.operand1 != null)
				System.out.println(this.op_stack.name().toLowerCase() + " " + this.operand1);
			else
				System.out.println(this.op_stack.name().toLowerCase());
		}

		else if(this.instr_type == TinyCode.INSTR_TYPE.HALT)
			System.out.println("sys halt");
	}
	public void setTarget(String target)
	{
		this.target = target;
	}

}
