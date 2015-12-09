/*

Function.java for storing attributes, local variables, temporary variables

*/

import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class Function
{

	public String label = null;
	private SymbolTable table = null;
	public int localVar = 0;
	public int tempCount = 0;
	public int paramCount = 0;
	public VARTYPE returnType = VARTYPE.INV;
	public HashMap<String, Integer> localMap = new HashMap<String, Integer>(); 
	public HashMap<String, Integer> paramMap = new HashMap<String, Integer>();

	public Function(String funcName, SymbolTable table)
	{
		this.label = funcName;
		this.table = table;
	}

	public void incLocalCount()
	{
		this.localVar++;
	}

	public void incTempCount()
	{
		this.tempCount++;
	}

	public int getLocalCount()
	{
		return this.localVar;
	}

	public int getTempCount()
	{
		return this.tempCount;
	}

	public void setParamCount(short count)
	{
		this.paramCount = count;
	}

	public void setReturnType(String type)
	{
		if(type.equals("STRING"))
			this.returnType = VARTYPE.STRING;
		else if(type.equals("FLOAT"))
			this.returnType = VARTYPE.FLOAT;
		else if(type.equals("INT")){
			this.returnType = VARTYPE.INT;}
	}

	//Add method for directly assigning [arsed local declarations to local numbers	
	//and hash map them
	public boolean addLocalVar(String name_list)
	{
		//System.out.println("Obtained var list "+name_list.split(",").length);
		for(String id : name_list.split(","))
		{
			if(this.table.getRecords().contains(id))
			{
				//System.out.println("new Entry Key" + id);
				this.localMap.put(id, ++this.localVar);
			}
		}
		return false;
	}

	public boolean addParamVar(String name)
	{
		if(this.table.getRecords().contains(name))
		{
			this.paramMap.put(name, ++this.paramCount);
			return true;
		}
		return false;
	}

}