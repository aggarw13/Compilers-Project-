import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class Micro 
{
	public static void main (String[] args) throws Exception
	{
		ANTLRFileStream data = null;
		try
		{
			data = new ANTLRFileStream(args[0]);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Please enter the filename to be scanned!");
		}
		catch(FileNotFoundException ex) 
		{
         	System.err.println("Invalid micro file!");
		}	        
		MicroLexer lexer = new MicroLexer(data);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		MicroParser parser = new MicroParser(tokenStream);
		parser.setErrorHandler(new MicroErrorStrategy());
		try
		{
			parser.program();
			ASTNode root;
			short func_index = -1;
			//SemanticDataHandler.printSemanticStack(SemanticDataHandler.globalScope);
			//System.out.println("Parsed Functions detected "+ ASTStackHandler.functionList.size());
			for(Iterator<ASTNode> it = ASTStackHandler.getASTFIFO().iterator() ; it.hasNext();)
			{	
				root = it.next();
		        if(root.getType() == ASTNodeType.FOR_INCR)
		        {
		            ASTStackHandler.FORincrStack.add(root);
		        	continue;
		        }
		        else if(root.getType() == ASTNodeType.ROF && ASTStackHandler.FORincrStack.size() > 0)
		        {
		        	//System.out.println("Enters here for priting ROF! size : "+ASTStackHandler.FORincrStack.size());
		            ASTStackHandler.traverseTree(ASTStackHandler.FORincrStack.pop());
		        }
		        else if(root.getType() == ASTNodeType.FUNC_BEGIN)
		        {
		        	func_index++;
		        	generateIR.tempNumber = 0;
		        	ASTStackHandler.currFunct = ASTStackHandler.functionList.get(func_index);
		        	//ASTStackHandler.currFunct = ASTStackHandler.functionList.get(ASTStackHandler.functionList.indexOf(root.getNameValue()));
		        }
		        else if(root.getType() == ASTNodeType.RETURN)
		        {
		      		ASTNode next = it.next();
		      		ASTStackHandler.traverseTree(root);
		      		if(next.getType() != ASTNodeType.FUNC_END)
		      			generateIR.funcScope(ASTNodeType.FUNC_END);
		      		ASTStackHandler.traverseTree(next);
		        	root = null;
		        }
		        //TODO : Needs to be changed to accommodate Subexpression Function Calls
		        else if(root.getType() == ASTNodeType.ASSIGNMENT && root.getRightChild().getType() == ASTNodeType.TEMP_VAR)
		        {
		        	ASTNode assignNode = root;
		        	root = it.next();
			        if(root.getType() == ASTNodeType.FUNC_CALL_BEGIN)
			        {
			        	ASTNode paramNode = it.next();
			        	while(paramNode.getType() != ASTNodeType.FUNC_CALL_END)
			    		{
			    			ASTStackHandler.traverseTree(paramNode);
			    			root.addParam(paramNode.getDataObject().getDest());
			    			paramNode = it.next();
			    		}
			        }
			        ASTStackHandler.traverseTree(root);
			       	root = assignNode;
			    }
			    if(root != null)
					ASTStackHandler.traverseTree(root);
			}		

			CFGHandler.generateCFG();

			CFGHandler.computeLiveness();

			//CFGHandler.printLiveSets();

			//System.out.println("Number of IR instructions : "+generateIR.IRCodeList.size());
			System.out.println(";IR Code");
			generateTinyCode.allocateMemory();
			generateTinyCode.mainJumpCode();
			func_index = -1;
			generateTinyCode.emptyRegisters(null);

			for(IRNode instr : generateIR.IRCodeList)
			{
				if(instr.opcode == IRNode.OPCODE.LABEL && instr.labelTarget.length() > 3 
					&& !generateTinyCode.checkifInt(instr.labelTarget))
						System.out.println();
				System.out.print(";");
				instr.printIR();

				//DEBUG CFG
				//instr.printPredecessors();
				//instr.printSuccessors();

				if(instr.leader)
					generateTinyCode.emptyRegisters(instr);

				if(instr.opcode == IRNode.OPCODE.LABEL && !generateTinyCode.checkifInt(instr.labelTarget))
				{
					ASTStackHandler.currFunct = ASTStackHandler.functionList.get(++func_index);
					generateTinyCode.IRTempMap = new HashMap<String,String>();
					generateTinyCode.regNumber = 0;
				}
				generateTinyCode.createCode(instr);
			}

			//System.out.println(";Tiny Code");
			for(TinyCode instr : generateTinyCode.CodeList)
			{
				instr.printInstr();
			}
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
