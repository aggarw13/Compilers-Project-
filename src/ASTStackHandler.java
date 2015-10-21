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
    public static Stack<String> subExprStack = new Stack<String>();

    public static boolean SubTreeBlock = false;
    public static boolean priorityOperatorTree = false;
    public static boolean SubTreeBlockEnded = false;

	public static ASTNode currTermNode = null;

    public ASTStackHandler()
    {}

    public static List<ASTNode> getASTFIFO()
    {
        return ASTStackHandler.ASTStack;
    }

    public static void updateCurrTree()
    {
         //ASTNode rightChild = (ASTStackHandler.SubTreeStack.size() > 0)? ASTStackHandler.currTermNode : ASTStackHandler.SubTreeStack.pop();
        if(ASTStackHandler.SubTreeStack.size() == 0)
            ASTStackHandler.last.setRightChild(ASTStackHandler.currTermNode);
        else
            ASTStackHandler.last.setRightChild(ASTStackHandler.SubTreeStack.pop());
    }

    public static boolean checkExprStackTop(String op)
    {
        return (ASTStackHandler.subExprStack.size() > 0 && ASTStackHandler.subExprStack.peek().equals(op));
    }

    public static void updateCurrSubTree()
    {
        ASTNode rightChild = null;
        if(ASTStackHandler.SubTreeStack.size() > 1 && ASTStackHandler.SubTreeStack.peek().getRightChild() != null)
        {
            if(ASTStackHandler.checkExprStackTop(")"))
            {
                while(ASTStackHandler.subExprStack.peek() != "("){
                    ASTStackHandler.subExprStack.pop();
                }
                ASTStackHandler.subExprStack.pop();
            }

            rightChild = ASTStackHandler.SubTreeStack.pop();
            ASTStackHandler.SubTreeStack.peek().setRightChild(rightChild);
            //System.out.println("Completes the Subtree at root : " + ASTStackHandler.SubTreeStack.peek().getNameValue() + " with right Child "+ rightChild.getNameValue());
        }
        //else if(ASTStackHandler.SubTreeStack.size() > 0 && !ASTStackHandler.SubTreeBlock)
        else if(ASTStackHandler.SubTreeStack.size() > 0 && !ASTStackHandler.checkExprStackTop("("))
        {
            if(ASTStackHandler.SubTreeStack.size() == 1 && ASTStackHandler.checkExprStackTop(")"))
                return;

            ASTStackHandler.SubTreeStack.peek().setRightChild(ASTStackHandler.currTermNode);    
            //System.out.println("Completes the Subtree at root : " + ASTStackHandler.SubTreeStack.peek().getNameValue() + " with right Child "+ ASTStackHandler.SubTreeStack.peek().getRightChild().getNameValue());
            if(ASTStackHandler.priorityOperatorTree)
            {
                ASTStackHandler.priorityOperatorTree = false;
                ASTStackHandler.updateCurrSubTree();
            }
        }    
    }

    public static void pushAssignmentTree(SymbolTable treeScope)
    {
    	ASTNode newRoot = new ASTNode(ASTNodeType.ASSIGNMENT, null, treeScope);
    	
    	// Initialize first pointer to FIFO
    	if(ASTStackHandler.first == null)
    		ASTStackHandler.first = newRoot;
    	ASTStackHandler.ASTStack.add(newRoot);
    	
        //System.out.println("Left Child "+id);
    	newRoot.setLeftChild(ASTStackHandler.currTermNode);
    	last = newRoot;
        //newRoot.setDataObject();
    }

    public static void pushIOTree(IO_TYPE ioOp, String id_list, SymbolTable treeScope)
    {
    	ASTNode newRoot = new ASTNode(ASTNodeType.IO, null, SemanticDataHandler.currentScope);
        newRoot.setIOType(ioOp);

    	if(ASTStackHandler.first == null)
    		ASTStackHandler.first = newRoot;

        ASTStackHandler.ASTStack.add(newRoot);
        SymbolTable scopeSearch;
        boolean foundID;
        for(String id : id_list.split(","))
        {
            if(SemanticDataHandler.findRecordScope(id, SemanticDataHandler.currentScope) == null){
                System.out.println("IO ERROR : "+ id+ " does not contain declaration");
                System.exit(0);}
        }

    	ASTNode leftIDChild = new ASTNode(ASTNodeType.IDENTIFIER, id_list, null);

        newRoot.setLeftChild(leftIDChild);
    	//System.out.println("Leaves IO Tree creation");
    	last = newRoot;
    }

    public static void createArithmeticTree(String mathOp)
    {
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
            //System.out.println("Creates a new EXPR TREE with base node " + mathOp + " and left child "+ currTermNode.getNameValue());
        }      

        //Case when Top is Stack is Left Child of Parent Root Node  (Push Root Node to Staxck after poppig Left SUb Tree)
        else if(ASTStackHandler.SubTreeStack.peek().getRightChild() != null)
        {
            //if(OPERATION.checkPriority(ASTStackHandler.SubTreeStack.peek().getOperation(), oper) && !ASTStackHandler.SubTreeBlock || ASTStackHandler.SubTreeBlockEnded)
            if((OPERATION.checkPriority(ASTStackHandler.SubTreeStack.peek().getOperation(), oper) && !ASTStackHandler.checkExprStackTop("(")) || ASTStackHandler.checkExprStackTop(")"))
            {
                ASTNode leftChildTree = ASTStackHandler.SubTreeStack.pop();
                //System.out.println("Reachs here!");
                opRoot.setLeftChild(leftChildTree);
                //ASTStackHandler.SubTreeStack.peek().setRightChild(null);
                //System.out.println("Adds a new PARENT TREE with node " + mathOp + " with left child "+leftChildTree.getNameValue());
            }
            //else if(!ASTStackHandler.SubTreeBlockEnded)
            else if (!ASTStackHandler.checkExprStackTop(")"))
            {
                ASTStackHandler.priorityOperatorTree = true;
                //System.out.println("Adds a RIGHT CHILD PRIORITY TREE  with " + mathOp + " with left child "+ASTStackHandler.currTermNode.getNameValue());
                opRoot.setLeftChild(ASTStackHandler.SubTreeStack.peek().getRightChild()); }
                //System.out.println("Operator Tree Left Node : " + ASTStackHandler.currTermNode.getNameValue());
            ASTStackHandler.SubTreeStack.push(opRoot);
        }
        else if(ASTStackHandler.SubTreeStack.peek().getRightChild() == null)
        {
             ASTStackHandler.SubTreeStack.push(opRoot);
            opRoot.setLeftChild(ASTStackHandler.currTermNode);
            //System.out.println("Adds a new RIGHT CHILD tree " + mathOp + " with left child " + ASTStackHandler.currTermNode.getNameValue());
        }
        //opRoot.getDataObject().setDataType(opRoot.getLeftChild().getDataType());
        ASTNode temp;
        //System.out.println("Creating Operator Root Node : "+ mathOp+ " Left Node : "+opRoot.getLeftChild().getNameValue());
    }

    public static void addTermNode(ASTNodeType leafASTNodeType, String name_value)
    {   
        SymbolTable idScope = null;
        if(leafASTNodeType == ASTNodeType.IDENTIFIER)
        {
            boolean foundRecord = false;
            idScope = SemanticDataHandler.findRecordScope(name_value, SemanticDataHandler.currentScope);
            if(idScope == null)
                {System.out.println("EXPRESSION Error : Identifier not declared!");System.exit(0);}

        }
        ASTNode litNode = new ASTNode(leafASTNodeType, name_value, idScope);
    	ASTStackHandler.currTermNode = litNode;
    }

    //In Order Traversal of tree
    public static void traverseTree(ASTNode root)
    {
        if(root.getLeftChild() != null){
            //if(root.getLeftChild().getType() != ASTNodeType.IDENTIFIER || root.getLeftChild().getType() != ASTNodeType.LITERAL)
            //System.out.print("(");
            ASTStackHandler.traverseTree(root.getLeftChild());
            //if(root.getLeftChild().getType() != ASTNodeType.IDENTIFIER || root.getLeftChild().getType() != ASTNodeType.LITERAL)
               // System.out.print(")");
        }
        //root.printNode();
        if(root.getRightChild() != null){
            //if(root.getRightChild().getType() != ASTNodeType.IDENTIFIER || root.getRightChild().getType() != ASTNodeType.LITERAL)
        //System.out.print("(");
        ASTStackHandler.traverseTree(root.getRightChild());
        //if(root.getRightChild().getType() != ASTNodeType.IDENTIFIER || root.getRightChild().getType() != ASTNodeType.LITERAL)
        //System.out.print(")");
    }
        root.generateIRCode(); 
    }


}

