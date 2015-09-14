import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

public class MicroErrorStrategy implements ANTLRErrorStrategy
{
	public MicroErrorStrategy()
	{
	}

	@Override
	public boolean inErrorRecoveryMode(Parser recognizer)
	{
		return false;
	}

	@Override
	public void recover(Parser recognizer, RecognitionException e)
	{
		System.out.println("Not accepted");
	}

	@Override
	public void reportError(Parser Recognizer,  RecognitionException e)
	{
		System.out.println("Not accepted");	
	}

	@Override
	public Token recoverInline(Parser Recognizer)
	{
		System.out.println("Not accepted");	
		return null;
	}

	@Override
	public void reportMatch(Parser Recognizer)
	{}

	@Override
	public void reset(Parser Recognizer)
	{}

	@Override
	public void sync(Parser Recognizer)
	{}
}
