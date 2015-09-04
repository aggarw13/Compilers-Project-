import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class Micro 
{
	public static void main (String[] args) throws Exception
	{
		ANTLRFileStream data = new ANTLRFileStream(args[0]);
		try
		{
			data = new ANTLRFileStream(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Please enter the filename to be scanned!");
		}
		MicroLexer lexer = new MicroLexer(data);
		Token token = lexer.nextToken();
		String[] tokenNames = lexer.getTokenNames();;
		while(token.getType() != MicroLexer.EOF)
		{
			System.out.println("Token Type: "+tokenNames[token.getType()]);
			System.out.println("Value: "+token.getText());
			token = lexer.nextToken();
		}

	}
}