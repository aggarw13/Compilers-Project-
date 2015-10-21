/*
	Semantic Stack class for creating Symbol Table required by IR
	for each parser rule implemented using listeners 
*/

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;
import java.io.*;
import java.lang.Exception.*;

public class SemanticDataHandler{
	
	static SymbolTable currentScope = null;
	static short blockScopeCount = 0;
	static SymbolTable globalScope = null;

	public static void pushNewScope(SCOPE scope, String name)
	{
		if(scope == SCOPE.BLOCK)
			name = Integer.toString(++blockScopeCount);
		SymbolTable newScope = new SymbolTable(name, scope);
		if(currentScope != null)
			currentScope.addNestedScope(newScope);
		newScope.setEnclosingScope(currentScope);
		currentScope = newScope; 
		if(scope == SCOPE.GLOBAL)
			globalScope = currentScope;
	}

	public static void popCurrentScope()
	{
		currentScope = currentScope.getEnclosingScope();  
	}
	
	public static void displayCurrentScope()
	{
		currentScope.printTable();
	}	

	public static void printSemanticStack(SymbolTable scope)
	{
	
		if(scope.getTableScope() == SCOPE.BLOCK)
			System.out.println("\nSymbol table BLOCK "+scope.getName());
		else if(scope.getTableScope() == SCOPE.GLOBAL)
			System.out.println("Symbol table GLOBAL");
		else 
			System.out.println("\nSymbol table "+scope.getName());
		scope.printTable();
		for(SymbolTable nestScope : scope.getNestedScopes())
		{
			SemanticDataHandler.printSemanticStack(nestScope);
		}	
	}

	public static void setRecordValue(String name, String value)
	{
		currentScope.setRecordVal(name, value);
	}

	public static void addScopeRecord(String name, String Type)
	{
		try
		{
			currentScope.addSemanticRecord(name, Type);
		}
		catch(Exception e)	
		{
			System.out.println("DECLARATION ERROR "+name);
			System.exit(0);
		}
	}

	public static SymbolTable findRecordScope(String recName, SymbolTable baseScope)
	{
		boolean foundScope = false;
		SymbolTable scope = baseScope;
		while(!foundScope && scope != null)
		{
			if(scope.getRecords().contains(recName))
				foundScope = true;
			else
				scope = scope.getEnclosingScope();
		}
		return scope;
	}

}