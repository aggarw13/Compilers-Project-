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
		//if(symName == "a")
					//System.out.println("Type of Variable 'A' record : "+symName+" "+symType+" "+(symType.equals("INT")));
		this.name = symName;
		if(symType.equals("STRING"))
			this.Type = VARTYPE.STRING;
		else if(symType.equals("FLOAT"))
			this.Type = VARTYPE.FLOAT;
		else if(symType.equals("INT")){
			this.Type = VARTYPE.INT;}
	}

	public String getName()
	{
		return this.name;
	} 

	public VARTYPE getType()
	{
		//System.out.println("Record type : "+this.Type.name());
		return this.Type;		
	}

	public void setVal(String value)
	{
		this.value = value;
	}

	public String getVal(String value)
	{
		return this.value;
	}

	public void displayRecord()
	{
		if(this.Type == VARTYPE.STRING)
			System.out.println("name "+this.name+" type STRING value "+this.value);		
		else
			System.out.println("name "+this.name+" type "+this.Type.name());		
	}
}