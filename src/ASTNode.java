/*

Class for reprsenting and facilitating AST operations 
*/

import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;


class ASTNode
{
	private String id_value;
	private SymbolTable scopeTable;
	private ASTNode leftNode = null, rightNode = null;
	private ASTNode parent = null;
	private DataObject nodeData = new DataObject();
	private ASTNodeProp nodeInfo;

	public ASTNode(ASTNodeType type, String IDVal, SymbolTable scope)
	{
		this.nodeInfo = new ASTNodeProp();
		this.id_value = IDVal;
		this.nodeInfo.type = type;
		this.scopeTable = scope;
		this.setDataObject();
	}

	public String getNameValue()
	{
		return this.id_value;
	}

	public void setOperation(OPERATION op)
	{
		this.nodeInfo.operator = op;
	}

	public OPERATION getOperation()
	{
		return nodeInfo.operator;
	}

	public ASTNodeType getType()
	{
		return this.nodeInfo.type;
	}

	public void setIOType(IO_TYPE io_op)
	{
		this.nodeInfo.ioOp = io_op;
	}

	public void setLeftChild(ASTNode left)
	{
		this.leftNode = left;
	}

	public void setRightChild(ASTNode right)
	{
		this.rightNode = right;
	}

	public ASTNode getLeftChild()
	{
		return this.leftNode;
	}

	public ASTNode getRightChild()
	{
		return this.rightNode;
	}

	public DataObject getDataObject()
	{
		return this.nodeData;
	}

	public void printNode()
	{
		String oper;
		switch(this.nodeInfo.type)
		{
			case ASSIGNMENT : System.out.print(" = "); break;
			case OPERATOR : 
					switch(this.nodeInfo.operator)
			    	{
			    		case ADD : oper = "+"; break;
			    		case SUB : oper = "-"; break;
			    		case MULT : oper = "*"; break;
			    		case DIV : oper = "/"; break;
			    		default : oper = "Invalid";
			    	}
			    	System.out.print(oper + " "); break;
			case LITERAL : System.out.print(this.id_value + " "); break;
			case IDENTIFIER : System.out.print(this.id_value + " "); break;
			case IO : 
					if(this.nodeInfo.ioOp ==  IO_TYPE.READ)
						System.out.print("Read"); 
					else
						System.out.print("Write"); 
		}
	}

	public void generateIRCode()
	{
		IRNode code;
		switch(this.nodeInfo.type)
		{
			case ASSIGNMENT : code = generateIR.assignmentOp(this.leftNode.getDataObject(), this.rightNode.getDataObject()); break;
			case OPERATOR : 
							code = generateIR.arithmeticOp(this.nodeInfo.operator, this.leftNode.getDataObject(), this.rightNode.getDataObject());
							this.nodeData.setDest(Integer.toString(generateIR.tempNumber - 1)); 
							if(code.opcode.name().substring(code.opcode.name().length() - 1).equals("F"))
								this.nodeData.setDataType(VARTYPE.FLOAT);
							else if(code.opcode.name().substring(code.opcode.name().length() - 1).equals("I"))
								this.nodeData.setDataType(VARTYPE.INT);
							break;
			case IO : for(String id : this.leftNode.getNameValue().split(",")){
						generateIR.IOop(this.nodeInfo.ioOp, id, SemanticDataHandler.findRecordScope(id, scopeTable));}
		}					  		
	}

	public void setDataObject()
	{
		if(this.nodeInfo.type == ASTNodeType.IDENTIFIER)
		{
			//System.out.println("Enters IDentifier : "+this.getType().name());
			this.nodeData.setDest(this.id_value);
			//System.out.println("")
			this.nodeData.setType(DataObject.TYPE.L);
			if(scopeTable != null)
				this.nodeData.setDataType(scopeTable.getRecord(this.id_value).getType());
			//System.out.print("Exits Identifier");
		}
		else if(this.nodeInfo.type == ASTNodeType.LITERAL)
		{
			//System.out.println("Enters Literal");
			if(this.id_value.indexOf('.') != -1)
				this.nodeData.setDataType(VARTYPE.FLOAT);
			else
				this.nodeData.setDataType(VARTYPE.INT);
			this.nodeData.setType(DataObject.TYPE.CONSTANT);
			this.nodeData.setDest(this.id_value);
			//System.out.print("Exits Literal");
		}
		else if(this.nodeInfo.type == ASTNodeType.OPERATOR)
		{
			//System.out.println("Enters Operator object creation");
			this.nodeData.setType(DataObject.TYPE.R);	

			//System.out.println("Exits Operator object creation");
			//this.nodeData.setDataType(this.leftNode.getDataObject().getDataType());
		}
	}
}