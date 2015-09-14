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
		Token token = lexer.nextToken();
		BufferedTokenStream tokenStream = new BufferedTokenStream(lexer);
		MicroParser parser = new MicroParser(tokenStream);
		parser.setErrorHandler(new MicroErrorStrategy());
		try
		{
			parser.program();
			System.out.println("Accepted");
		}
		catch(Exception e)
		{
			//System.err.println("Not Accepted");
		}
	}
}
