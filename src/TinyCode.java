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
 		JUMP,
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
 		CMPI,
 		CMPR
 	}
 	public enum OPCODE_IO
 	{	
 		READI,
 		READR,
 		WRITEI,
 		WRITER,
 		WRITES
 	}

 	public INSTR_TYPE instr_type;
 	public OPCODE_ARITH op_arith;
 	public OPCODE_IO op_io;

	public String target = null;
	public String operand1 = null, dest = null, imm = null;
	public String strValue;

	public TinyCode(TinyCode.INSTR_TYPE opcode, String op1, String dest, String imm)
	{
		this.instr_type = opcode;
		if(generateTinyCode.checkifInt(op1))
			this.operand1 = "r" + op1;
		else
			this.operand1 = op1;
		if(generateTinyCode.checkifInt(dest))
			this.dest = "r" + dest;
		else
			this.dest= dest;
		this.imm = imm;
	}

	public void setArithOp(TinyCode.OPCODE_ARITH opcode)
	{
		this.op_arith = opcode;
	}

	public void setIOOp(TinyCode.OPCODE_IO opcode)
	{
		this.op_io = opcode;
	}

	public void setStrVal(String value)
	{
		this.strValue = value;
	}
	public void printInstr()
	{
		//System.out.println("Tiny Instruction Type :"+this.instr_type.name());
		String src = (this.operand1 != null)? this.operand1 : this.imm;
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
			
	}
	public void setTarget(String target)
	{
		this.target = target;
	}

}
