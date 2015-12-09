/*
   Module for  storing IR code generation Routine calls 
 */
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

 public class generateIR
 {

 	public static int tempNumber = 1;
 	public static int labelNumber = 1;
 	//public static String 
 	public static int funcRetTemp = 0;
 	public static List<IRNode> IRCodeList = new ArrayList<IRNode>();
  public static List<IRNode> labelNodes = new ArrayList<IRNode>();

 	generateIR(){}

 	public static void jumpLabelIR(IRNode.OPCODE opcode, String labelNo)
 	{
 		IRNode node = new IRNode(opcode, null, null, null);
 		node.setTarget(labelNo);
 		generateIR.IRCodeList.add(node);
 		if(opcode == IRNode.OPCODE.LABEL)
      generateIR.labelNodes.add(node);
    //return node;
  	}

  	public static void funcScope(ASTNodeType type)
  	{
  		//System.out.println("Enters function scope code generation");
  		if(type == ASTNodeType.FUNC_BEGIN)
  			generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.LINK, null,null,null));
  		else if(type == ASTNodeType.FUNC_END)
  		{
  			//generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.UNLNK, null, null, null));
  			generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.RET, null, null, null));
  		}
  	}

  	public static void returnOp(DataObject rightNode)
  	{
  		IRNode.OPCODE op = IRNode.OPCODE.STOREI;
  		if(ASTStackHandler.currFunct.returnType == VARTYPE.FLOAT)
  			op = IRNode.OPCODE.STOREF;
  		String op1 = rightNode.getDest(), imm = null;
  		if(rightNode.getValueType() == DataObject.TYPE.CONSTANT)
  		{
  			imm = op1; op1 = null;
  		}
  		else if(rightNode.getValueType() == DataObject.TYPE.L)
  		{
  			op1 = Integer.toString(generateIR.tempNumber++);
  			generateIR.IRCodeList.add(new IRNode(op, rightNode.getDest(), null, op1));
  			ASTStackHandler.currFunct.incTempCount();
  		}	
		IRNode node = new IRNode(op, op1, null, "$R");
		node.setImm1(imm);	
		generateIR.IRCodeList.add(node);
  	}

  	public static void functionCall(String funcName, List<String> params)
  	{
  		generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.PUSH, null, null, null));
		  for(String param : params)
  		{
  			generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.PUSH, param, null, null));
  		}  		
  		generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.JSR, null, null, funcName));
  		for(String param : params)
  		{
  			generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.POP, null, null, null));
  		} 
		  generateIR.IRCodeList.add(new IRNode(IRNode.OPCODE.POP, null, null, Integer.toString(generateIR.tempNumber++)));
  		generateIR.funcRetTemp = generateIR.tempNumber - 1;
  	}

 	public static IRNode compOp(OPERATION operator, DataObject left, DataObject right)
 	{
 		IRNode.OPCODE opcode = IRNode.OPCODE.EQ;
 		IRNode opNode;
 		boolean compOrder = true, isFloat = false;
 		String imm1 = null, imm2 = null, op1 = left.getDest(), op2 = right.getDest();
 		//System.out.println("Enters Generate Comparison Expression Code");
 		if(left.getDataType() == VARTYPE.INT && right.getDataType() == VARTYPE.INT)
 			opcode = IRNode.OPCODE.STOREI;
 		else if(left.getDataType() == VARTYPE.FLOAT && right.getDataType() == VARTYPE.FLOAT)
 		{
 			opcode = IRNode.OPCODE.STOREF;
 			isFloat = true;
 		}

 		/*if(left.getValueType() == DataObject.TYPE.CONSTANT)
 		{	
 			op1 = Integer.toString(generateIR.tempNumber++);
 			imm1 = left.getDest();
 			opNode = new IRNode(opcode, null, null, Integer.toString(generateIR.tempNumber - 1));
 			opNode.setImm1(imm1);
			generateIR.IRCodeList.add(opNode);
 		}*/
  		if(right.getValueType() == DataObject.TYPE.CONSTANT || right.getValueType() == DataObject.TYPE.L)
 		{
 			if(left.getValueType() != DataObject.TYPE.CONSTANT && generateTinyCode.checkifInt(left.getDest()))
 			{
 				compOrder = false;
 				op1 = right.getDest();
 				op2 = left.getDest();
 			}
 			else
 			{
 				op2 = Integer.toString(generateIR.tempNumber++);
 				op1 = null;
 				if(right.getValueType() == DataObject.TYPE.L)
 					op1 = right.getDest();
 				else	
 					imm1 = right.getDest();
 				opNode = new IRNode(opcode, op1, null, op2);
 				opNode.setImm1(imm1);
 				op1 = left.getDest();
 				generateIR.IRCodeList.add(opNode);
 			}
 		}

 		switch(operator)
 		{
 			case EQ : opcode = compOrder? IRNode.OPCODE.NE : IRNode.OPCODE.EQ; break; 
 			case NE : opcode = compOrder? IRNode.OPCODE.EQ : IRNode.OPCODE.NE; break;
 			case GE : opcode = compOrder? IRNode.OPCODE.LT : IRNode.OPCODE.GE; break;
 			case LE : opcode = compOrder? IRNode.OPCODE.GT : IRNode.OPCODE.LE; break;
 			case GT : opcode = compOrder? IRNode.OPCODE.LE : IRNode.OPCODE.GT; break;
 			case LT : opcode = compOrder? IRNode.OPCODE.GE : IRNode.OPCODE.LT; 
 		} 		

		opNode = new IRNode(opcode, op1, op2, null); 
		if(isFloat)
			opNode.cmprType = VARTYPE.FLOAT;
		//System.out.println("Number of instructions in IR List :" + generateIR.IRCodeList.size());
 		generateIR.IRCodeList.add(opNode);
 		return opNode;
 	}

 	public static IRNode arithmeticOp(OPERATION operator, DataObject left, DataObject right)
 	{
 		IRNode.OPCODE opcode = IRNode.OPCODE.ADDI;
 		IRNode opNode;
 		String temp_dest;
 		short resType = 0;
 		String imm1 = null, imm2 = null, op1 = left.getDest(), op2 = right.getDest();
 		//System.out.println("Enters Generate Arithmetic Code");
 		if(left.getDataType() == VARTYPE.INT && right.getDataType() == VARTYPE.INT)
 			resType = 1;
 		else if(left.getDataType() == VARTYPE.FLOAT && right.getDataType() == VARTYPE.FLOAT)
 			resType = 2;
 		//opcode = Enum.valueOf(IRNode.OPCODE.class, operator.name() + resType);
 		switch(operator)
 		{
 			case ADD : opcode = (resType == 1)?  IRNode.OPCODE.ADDI : IRNode.OPCODE.ADDF; break; 
 			case SUB : opcode = (resType == 1)?  IRNode.OPCODE.SUBI : IRNode.OPCODE.SUBF; break;
 			case MULT : opcode = (resType == 1)? IRNode.OPCODE.MULTI : IRNode.OPCODE.MULTF; break;
 			case DIV : opcode = (resType == 1)?  IRNode.OPCODE.DIVI : IRNode.OPCODE.DIVF;
 		}
 		if(left.getValueType() == DataObject.TYPE.CONSTANT)
 			{imm1 = left.getDest(); op1 = null;}
 		if(right.getValueType() == DataObject.TYPE.CONSTANT)
 			{imm2 = right.getDest(); op2 = null;}

		opNode = new IRNode(opcode, op1, op2, Integer.toString(generateIR.tempNumber++)); 
		ASTStackHandler.currFunct.incTempCount();
	 	opNode.setImm1(imm1);
 		opNode.setImm2(imm2);

		//System.out.println("Number of instructions in IR List :" + generateIR.IRCodeList.size());
 		generateIR.IRCodeList.add(opNode);
 		return opNode;
 	} 	

 	public static IRNode assignmentOp(DataObject Lnode, DataObject Rnode)
 	{	
 		IRNode node;
 		//System.out.println("Enters Assignment Code generation");
 		IRNode.OPCODE assgnOP = IRNode.OPCODE.STOREI;
 		IRNode assgnNode;
 		if(Lnode.getDataType() == VARTYPE.FLOAT && Rnode.getDataType() == VARTYPE.FLOAT)
 			assgnOP = IRNode.OPCODE.STOREF;

 		if(/*Rnode.getValueType() == DataObject.TYPE.CONSTANT || */Rnode.getValueType() == DataObject.TYPE.L)
 		{
 			node = new IRNode(assgnOP, null, null, Integer.toString(generateIR.tempNumber++));
      node.operand1 = Rnode.getDest();
 			/*else
 				node.setImm1(Rnode.getDest());*/
 			generateIR.IRCodeList.add(node);
 			Rnode.setDest(Integer.toString(generateIR.tempNumber - 1));
 		}
 		assgnNode = new IRNode(assgnOP, null, null, Lnode.getDest());
 		if(Rnode.getValueType() == DataObject.TYPE.CONSTANT)
 			assgnNode.setImm1(Rnode.getDest());
 		else if(Rnode.getValueType() == DataObject.TYPE.FUNC_CALL)
 			assgnNode.operand1 = Integer.toString(generateIR.funcRetTemp);
    else 
      assgnNode.operand1 = Rnode.getDest();
    generateIR.IRCodeList.add(assgnNode);
 		//System.out.println("EXITS Assignment Code generation");
 		//assgnNode.printIR();

 		return assgnNode;
 	}

 	public static void IOop(IO_TYPE io_op, String mapped, String id, SymbolTable scope)
 	{
 		IRNode.OPCODE opcode = IRNode.OPCODE.READI; 
 		IRNode IOcode;
 		//System.out.println("Scope searchrd for IO id : "+scope);
 		if(io_op == IO_TYPE.READ)
 		{
 			if(scope.getRecord(id).getType() == VARTYPE.INT)
 				opcode = IRNode.OPCODE.READI;
 			else if(scope.getRecord(id).getType() == VARTYPE.FLOAT)
 				opcode = IRNode.OPCODE.READF;
 			else
 				opcode = IRNode.OPCODE.READS;
 		}
 		else if(io_op == IO_TYPE.WRITE)
 		{
 			if(scope.getRecord(id).getType() == VARTYPE.INT)
 				opcode = IRNode.OPCODE.WRITEI;
 			else if(scope.getRecord(id).getType() == VARTYPE.FLOAT)
 				opcode = IRNode.OPCODE.WRITEF;
 			else 
 				opcode = IRNode.OPCODE.WRITES;
 		}
 		//System.out.println("Reaches end of opcode selection in OP code generation");
 		IOcode = new IRNode(opcode, null,null, mapped);
 		//IOcode.printIR();
 		IRCodeList.add(IOcode);
 		//System.out.println("EXITS IO Code generation");
	}
}

