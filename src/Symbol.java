/*
 Symbol (Activation Record) representation for data members of Scopes (Symbol tables)
 */

import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class Symbol
{
	private String name;
	private String value;
	private VARTYPE Type;

	public Symbol(String symName, String symType)
	{
		this.name = symName;
		if(symType == "STRING")
			this.Type = VARTYPE.STRING;
		else if(symType == "FLOAT")
			this.Type = VARTYPE.FLOAT;
		else if(symType == "INT")
			this.Type = VARTYPE.INT;
	}

	public String getName()
	{
		return this.name;
	} 

	public VARTYPE getType()
	{
		return this.Type;		
	}

	public void setVal(String value)
	{
		this.value = value;
	}

	public void displayRecord()
	{
		if(this.Type == VARTYPE.STRING)
			System.out.println("name "+this.name+" type STRING value "+this.value);		
		else
			System.out.println("name "+this.name+" type "+this.Type.name());		
	}
}