/*
   Module for  storing IR code generation Routine calls 
 */
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

 public class generateIR
 {

 	public static int tempNumber = 1;

 	public static List<IRNode> IRCodeList = new LinkedList<IRNode>();

 	generateIR(){}

 	public static IRNode arithmeticOp(OPERATION operator, DataObject left, DataObject right)
 	{
 		IRNode.OPCODE opcode;
 		IRNode opNode;
 		String resType = "";
 		if(left.getDataType() == VARTYPE.INT && right.getDataType() == VARTYPE.INT)
 			resType = "I";
 		else if(left.getDataType() == VARTYPE.FLOAT && right.getDataType() == VARTYPE.FLOAT)
 			resType = "F";

 		opcode = Enum.valueOf(IRNode.OPCODE.class, operator.name() + resType);
 		
 		opNode = new IRNode(opcode, left.getDest(), right.getDest(), Integer.toString(generateIR.tempNumber++)); 
 		opNode.printIR();
 		generateIR.IRCodeList.add(opNode);
 		return opNode;
 	} 	

 	public static IRNode assignmentOp(DataObject Lnode, DataObject Rnode)
 	{	
 		IRNode.OPCODE assgnOP = IRNode.OPCODE.STOREI;
 		IRNode assgnNode;
 		if(Lnode.getDataType() == VARTYPE.FLOAT && Rnode.getDataType() == VARTYPE.FLOAT)
 			assgnOP = IRNode.OPCODE.STOREF;
 		 
 		assgnNode = new IRNode(assgnOP, Rnode.getDest(), null, Rnode.getDest());
 		assgnNode.printIR();
 		return assgnNode;
 	}

 	public static void IOop(IO_TYPE io_op, String term_list, SymbolTable scope)
 	{
 		IRNode.OPCODE opcode = IRNode.OPCODE.READI; IRNode IOcode;
 		for(String id : term_list.split(","))
 		{
 			if(io_op == IO_TYPE.READ)
 			{
 				if(scope.getRecord(id).getType() == VARTYPE.INT)
 					opcode = IRNode.OPCODE.READI;
 				else
 					opcode= IRNode.OPCODE.READF;
 			}
 			else if(io_op == IO_TYPE.WRITE)
 			{
 				if(scope.getRecord(id).getType() == VARTYPE.INT)
 					opcode = IRNode.OPCODE.WRITEI;
 				else
 					opcode = IRNode.OPCODE.WRITEF;
 			}
 			IOcode = new IRNode(opcode, null,null, id);
 			IOcode.printIR();
 			IRCodeList.add(IOcode);
 			
 		}
 	} 
}

