/*

 Class for  Representing AST Tree Stack 

 Helpful Links :
  
  http://stackoverflow.com/questions/4931346/how-to-output-the-ast-built-using-antlr

  http://www.antlr2.org/doc/trees.html
 
 */

import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

public class ASTStackHandler
{
	public static ASTNode first = null, last = null;
	public static List<ASTNode> ASTStack = new ArrayList<ASTNode>(); 
    public static  Stack<ASTNode> SubTreeStack = new Stack<ASTNode>();

	public static ASTNode currTermNode = null;

    public ASTStackHandler()
    {}

    public static List<ASTNode> getASTFIFO()
    {
        return ASTStackHandler.ASTStack;
    }

    public static void updateCurrTree()
    {
        ASTNode rightChild = (ASTStackHandler.SubTreeStack.size() == 0)? ASTStackHandler.currTermNode : ASTStackHandler.SubTreeStack.pop();
        ASTStackHandler.last.setRightChild(rightChild);
    }

    public static void updateCurrSubTree()
    {
        //System.out.println("Enters sub tree completion method");
        ASTNode rightChild = null;
        if(ASTStackHandler.SubTreeStack.size() > 1 && ASTStackHandler.SubTreeStack.peek().getRightChild() != null)
        {
            rightChild = ASTStackHandler.SubTreeStack.pop();
            ASTStackHandler.SubTreeStack.peek().setRightChild(rightChild);
            //System.out.println("Completes the Subtree at root : " + ASTStackHandler.SubTreeStack.peek().getNameValue() + " with right Child "+ rightChild.getNameValue());
        }
        else if(ASTStackHandler.SubTreeStack.size() > 0)
        {
            ASTStackHandler.SubTreeStack.peek().setRightChild(ASTStackHandler.currTermNode);    
            //System.out.println("Completes the Subtree at root : " + ASTStackHandler.SubTreeStack.peek().getNameValue() + " with right Child "+ ASTStackHandler.SubTreeStack.peek().getRightChild().getNameValue());
        }    
    }

    public static void pushAssignmentTree(SymbolTable treeScope, String id)
    {
    	ASTNode newRoot = new ASTNode(ASTNodeType.ASSIGNMENT, null, treeScope);
    	
    	// Initialize first pointer to FIFO
    	if(ASTStackHandler.first == null)
    		ASTStackHandler.first = newRoot;
    	ASTStackHandler.ASTStack.add(newRoot);
    	
    	ASTNode IDChild = new ASTNode(ASTNodeType.IDENTIFIER, id, treeScope);
        System.out.println("Left Child "+id);
    	newRoot.setLeftChild(IDChild);
    	//IDChild.setParent(newRoot);
    	last = newRoot;
    }

    public static void pushIOTree(IO_TYPE ioOp, String id_list, SymbolTable treeScope)
    {
    	ASTNode newRoot = new ASTNode(ASTNodeType.IO, null, null);

    	if(ASTStackHandler.first == null)
    		ASTStackHandler.first = newRoot;

        ASTStackHandler.ASTStack.add(newRoot);


       /* for(String id : id_list.split(","))
        {
            if(SemanticDataHandler.currentScope.getRecord(id) == null){
                System.out.println("IO ERROR : "+ id+ " does not exist in current scope");
                System.exit(0);}
        }*/

    	ASTNode leftIDChild = new ASTNode(ASTNodeType.IDENTIFIER, id_list, SemanticDataHandler.currentScope);

        newRoot.setLeftChild(leftIDChild);
    	newRoot.setIOType(ioOp);
    	//System.out.println("Leaves IO Tree creation");
    	last = newRoot;
    }

    public static void createArithmeticTree(String mathOp)
    {

        //System.out.println("Enter operator Node creation ");
    	OPERATION oper = OPERATION.ADD;
    	switch(mathOp.toCharArray()[0])
    	{
    		case '+' : oper = OPERATION.ADD; break;
    		case '-' : oper = OPERATION.SUB; break;
    		case '*' : oper = OPERATION.MULT; break;
    		case '/' : oper = OPERATION.DIV; break;
    	}

	   	ASTNode opRoot = new ASTNode(ASTNodeType.OPERATOR, mathOp, SemanticDataHandler.currentScope);
        opRoot.setOperation(oper);

        //Case when stack is empty : Push Root to stack and add current Term as left child
        if(ASTStackHandler.SubTreeStack.size() == 0)
        {
            ASTStackHandler.SubTreeStack.push(opRoot);
            opRoot.setLeftChild(ASTStackHandler.currTermNode);
        }	   

        //Case when Top is Stack is Left Child of Parent Root Node  (Push Root Node to Staxck after poppig Left SUb Tree)
        else if(ASTStackHandler.SubTreeStack.peek().getRightChild() != null)
        {
            ASTNode leftChildTree = ASTStackHandler.SubTreeStack.pop();
            opRoot.setLeftChild(leftChildTree);
            //System.out.println("Operator Tree Left Node : " + ASTStackHandler.currTermNode.getNameValue());
            ASTStackHandler.SubTreeStack.push(opRoot);
        }
        else if(ASTStackHandler.SubTreeStack.peek().getRightChild() == null)
        {
            ASTStackHandler.SubTreeStack.push(opRoot);
            opRoot.setLeftChild(ASTStackHandler.currTermNode);
        }
        ASTNode temp;
        //System.out.println("Creating Operator Root Node : "+ mathOp+ " Left Node : "+opRoot.getLeftChild().getNameValue());
    }

    public static void addTermNode(ASTNodeType leafASTNodeType, String name_value)
    {

    	ASTNode litNode = new ASTNode(leafASTNodeType, name_value, null);
    	ASTStackHandler.currTermNode = litNode;
    }

    //In Order Traversal of tree
    public static void traverseTree(ASTNode root)
    {
        if(root == null)
            return;
        //if(root.getLeftChild())
        ASTStackHandler.traverseTree(root.getLeftChild());
        //if(root.getRightChild())
        ASTStackHandler.traverseTree(root.getRightChild());
        root.printNode();
        root.generateIRCode();  
        /*if(root.getType() = OPERATOR)
            IRCode.add(root.generateIRCode());
        else if(root.getType() == IO)
            IRCode.addAll(root.generateIRCode());
    */

    }


}

