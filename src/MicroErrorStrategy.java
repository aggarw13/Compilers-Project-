import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

public class MicroErrorStrategy extends DefaultErrorStrategy
{
	public MicroErrorStrategy()
	{
		super();
	}

	@Override
	public void reportError(Parser Recognizer,  RecognitionException e)
	{
		System.out.println("Not accepted");	
		throw new RuntimeException(e);
	}

	@Override
	public Token recoverInline(Parser Recognizer)
	{
		System.out.println("Not accepted");	
		throw new RuntimeException(new Exception());
	}
}
