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
				//System.out.println("Currently generating code for "+root.getNameValue());
				
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
				ASTStackHandler.traverseTree(root);
			}		

			System.out.println("Number of IR instructions : "+generateIR.IRCodeList.size());

			generateTinyCode.allocateMemory();
			generateTinyCode.mainJumpCode();
			func_index = -1;
			for(IRNode instr : generateIR.IRCodeList)
			{
				System.out.print(";");
				instr.printIR();
				if(instr.opcode == IRNode.OPCODE.LINK)
				{
					ASTStackHandler.currFunct = ASTStackHandler.functionList.get(++func_index);
					generateTinyCode.IRTempMap = new HashMap<String,String>();
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
		}
	}
}
