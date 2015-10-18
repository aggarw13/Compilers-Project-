/*

Class repsernting data objects for AST nodes required for IR generation 

*/
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class DataObject
{
	public enum TYPE
	{
		L,
		R
	}

	private TYPE valType;
	private IRNode firstNode;
	private IRNode lastNode;
	private String dest = null;
	private VARTYPE dataType = null; 

	public DataObject()
	{}

	public void setType(DataObject.TYPE lrType)
	{
		this.valType = lrType;
	}

	public DataObject.TYPE getValueType()
	{
		return this.valType;
	}

	public String getDest()
	{
		return dest;
	}

	public VARTYPE getDataType()
	{
		return dataType;
	}

	public void setDataType(VARTYPE dataType)
	{
		this.dataType = dataType;
	}

	public void setDest(String dest)
	{
		this.dest = dest;
	}

}