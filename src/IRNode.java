/*

  Class representing IR code 

 */
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

 class IRNode
 {
 	//public static List<IRNode> IRList = new ArrayList<IRNode>(); 
 	
 	public enum OPCODE{
 		ADDI("ADDI"),
 		ADDF("ADDF"),
 		SUBI("SUBI"),
 		SUBF("SUBF"),
 		MULTI("MULTI"),
 		MULTF("MULTF"),
 		DIVI("DIVI"),
 		DIVF("DIVF"),
 		STOREI("STOREI"),
 		STOREF("STOREF"),
 		GT("GT"),
 		GE("GE"),
 		LT("LT"),
 		LE("LE"),
 		NE("NE"),
 		EQ("EQ"),
 		JUMP("JUMP"),
 		LABEL("LABEL"),
 		READI("READI"),
 		READF("READF"),
 		WRITEI("WRITEI"),
 		WRITEF("WRITEF");

 		private final String  OPname;

 		OPCODE(String name)
 		{
 			this.OPname = name;
 		}
/*
 		public String name()
 		{return this.OPname;}*/
 	}

 	public OPCODE opcode;
	public String labelTarget;
	public String operand1, operand2, dest;

	public IRNode(IRNode.OPCODE opcode, String op1, String op2, String result)
	{
		this.opcode = opcode;
		this.operand1 = op1;
		this.operand2 = op2;
		this.dest= result;
	}

	public void printIR()
	{
		/*if(this.opcode == STOREI || this.opcode == STOREF)
			System.out.print(this.opcode.name() + " " + this.result);
		else if(this.opcode.name() == STOREI || this.opcode.name() == STOREF)*/
		System.out.println(this.opcode.name() + " "+ this.operand1 + " " + this.operand2 + " " + this.dest);
	}

	public void setTarget(String target)
	{
		this.labelTarget = target;
	}

}