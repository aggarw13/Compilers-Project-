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
         	System.err.println("Invalid micro fil0e!");
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
				ASTStackHandler.traverseTree(root);
			}		

			generateTinyCode.allocateMemory();
			for(IRNode instr : generateIR.IRCodeList)
			{
				System.out.print(";");
				instr.printIR();
				generateTinyCode.createCode(instr);
			}

			System.out.println(";Tiny Code");
			for(TinyCode instr : generateTinyCode.CodeList)
			{
				instr.printInstr();
			}
			System.out.println("sys halt");
			//System.out.println("Number of Tiny instructions :" + generateTinyCode.CodeList.size());

			//ParseTreeWalker walker = new ParseTreeWalker();
			//SemanticDataBuilder semanticStack = new SemanticDataBuilder(parser);						
		}
		catch(Exception e)
		{
		}
	}
}
