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
 		READS("READS"),
 		WRITEI("WRITEI"),
 		WRITEF("WRITEF"),
 		WRITES("WRITES"),
 		LINK("LINK"),
 		UNLNK("UNLNK"),
 		PUSH("PUSH"),
 		POP("POP"),
 		RET("RET"),
 		JSR("JSR");

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
	public String imm1, imm2;
	public VARTYPE cmprType = VARTYPE.INT;
	public List<IRNode> predec = new LinkedList<IRNode>(), success = new LinkedList<IRNode>();
	public Set<String> live_in = new HashSet<String>(), live_out = new HashSet<String>(), kill = new HashSet<String>(), gen = new HashSet<String>();

	public IRNode(IRNode.OPCODE opcode, String op1, String op2, String result)
	{
		this.opcode = opcode;
		this.operand1 = op1;
		this.operand2 = op2;
		this.dest = result;
		if(dest != null && opcode != OPCODE.JSR)
		{
			if(opcode == OPCODE.WRITEI || opcode == OPCODE.WRITEF || opcode == OPCODE.WRITES) 
				this.gen.add(this.dest);
			else
				this.kill.add(dest);
		}
		if(op1 != null)
			this.gen.add(this.operand1);
		if(op2 != null)
			this.gen.add(this.operand2);
		if(opcode == OPCODE.JSR)
			this.gen.addAll(SemanticDataHandler.globalScope.getRecords());		
		if(opcode == OPCODE.RET)
			this.live_out.addAll(SemanticDataHandler.globalScope.getRecords());
	}

	public void printIR()
	{
		String src1 = (this.operand1 != null)? (generateTinyCode.checkifInt(this.operand1)? "$T" + this.operand1 : this.operand1) : this.imm1;
		String src2 = (this.operand2 != null)? (generateTinyCode.checkifInt(this.operand2)? "$T" + this.operand2 : this.operand2) : this.imm2;
		String dest = this.dest;
		if(this.dest == null && this.labelTarget != null)
		{
			dest = this.labelTarget;
			if(generateTinyCode.checkifInt(this.labelTarget))
				dest = "label" + this.labelTarget;
		}
		else 
			if(generateTinyCode.checkifInt(dest))
			dest = "$T" + dest;
		if(src2 == null && src1 == null && dest != null)
			System.out.println(this.opcode.name() + " " + dest);
		else if(src1 != null && src2 == null && dest != null)
			System.out.println(this.opcode.name() + " " + src1 + " " + dest);
		else if(src2 == null && dest == null && src1 != null)
			System.out.println(this.opcode.name() + " " + src1);
		else if(src2 == null && src1 == null && dest == null)
			System.out.println(this.opcode.name());
		else 
			System.out.println(this.opcode.name() + " " + src1 + " " + src2 + " " + dest);
	}

	public void setImm1(String value)
	{
		this.imm1 = value;
	}

	public void setImm2(String value)
	{
		this.imm2 = value;
	}

	public void setTarget(String target)
	{
		this.labelTarget = target;
	}

	public void printPredecessors()
	{
		System.out.println();
		for(IRNode node : this.predec)
		{
			System.out.println("Label : "+ node.labelTarget);
		}
		System.out.println();
	}

	public void printSuccessors()
	{
		System.out.println();
		for(IRNode node : this.success)
		{
			System.out.println("Label : "+ node.labelTarget);
		}
		System.out.println();
	}
}