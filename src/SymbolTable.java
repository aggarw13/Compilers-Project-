/*
  Symbol Table class for representing scope specific symbol table in Semantic Record Stack 	
*/

import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;
 public class SymbolTable
{
	private String name;
	public SCOPE Scope; 
	HashMap<String, Symbol> semanticRecords = new HashMap<String, Symbol>();	
	private List<String> recordNames = new ArrayList<String>();
	private SymbolTable enclosingTable;
	private List<SymbolTable> nestedTable = new ArrayList<SymbolTable>();

	public SymbolTable(String TableName, SCOPE scope)
	{
		this.name = TableName;	
		this.Scope = scope;
	}

	public String getName()
	{
		return this.name;
	}

	public SCOPE getTableScope()
	{
		return this.Scope;
	}

	public void addSemanticRecord(String symName, String type) 
	{
		for(String name : symName.split(","))
		{
			Symbol symbol = new Symbol(name, type);
			if(!this.semanticRecords.containsKey(name))
			{
				recordNames.add(name);
				semanticRecords.put(name, symbol);
			}
			else 
				throw new RuntimeException(name);
		}
	}

	public void setRecordVal(String recName, String val)
	{
		semanticRecords.get(recName).setVal(val);
	} 

	public List<String> getRecords()
	{
		return this.recordNames;
	}

	public SymbolTable getEnclosingScope()
	{
		return this.enclosingTable;
	}

	public Symbol getRecord(String recName)
	{
		return semanticRecords.get(recName);
	}	

	public void printTable()
	{
		for(String record : recordNames)
		{
			semanticRecords.get(record).displayRecord();
		}
	}

	public List<SymbolTable> getNestedScopes()
	{
		return nestedTable;
	}

	public void setEnclosingScope(SymbolTable parentScope)
	{
		enclosingTable = parentScope;
	}

	public void addNestedScope(SymbolTable nestScope)
	{
		nestedTable.add(nestScope);
	} 

	public boolean checkSymbolValidity(String symName)
	{
		//if(symName == "n")
			System.out.println("Symbol List : "+recordNames);
		return !recordNames.contains(symName);
	}
}