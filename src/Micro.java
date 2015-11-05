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
			//SemanticDataHandler.printSemanticStack(SemanticDataHandler.globalScope);
			for(ASTNode root : ASTStackHandler.getASTFIFO())
			{			
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
				ASTStackHandler.traverseTree(root);
			}		

			System.out.println("Number of IR instructions : "+generateIR.IRCodeList.size());

			generateTinyCode.allocateMemory();
			for(IRNode instr : generateIR.IRCodeList)
			{
				System.out.print(";");
				instr.printIR();
				generateTinyCode.createCode(instr);
			}

			//System.out.println(";Tiny Code");
			for(TinyCode instr : generateTinyCode.CodeList)
			{
				instr.printInstr();
			}
			System.out.println("sys halt");						
		}
		catch(Exception e)
		{
		}
	}
}
