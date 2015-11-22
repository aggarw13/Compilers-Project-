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

    public static Stack<ASTNode> SubTreeStack = new Stack<ASTNode>();
    public static Stack<String> subExprStack = new Stack<String>();
    public static Stack<ASTNode> structStack = new Stack<ASTNode>();
    public static Stack<ASTNode> FORincrStack = new Stack<ASTNode>();
    public static List<Function> functionList = new ArrayList<Function>(); 

    public static ASTNode currStructure = null;    
    public static ASTNode condLeft = null, condRight = null;
    public static ASTNode currFORTree = null;
    //public static List<ASTNode> currList = n;
    public static Function currFunct = null;

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

    /****************************************Function Scope and Call Handling Methods******************************/

    public static void addFunction(String func_name)
    {  
        Function func;
        func = new Function(func_name, SemanticDataHandler.currentScope);
        ASTStackHandler.functionList.add(func);
        ASTStackHandler.currFunct = func;
        ASTNode funcNode = new ASTNode(ASTNodeType.FUNC_BEGIN, func_name, null);
        ASTStackHandler.ASTStack.add(funcNode);
        ASTStackHandler.last = funcNode;
    }

    public static void updateFunctionScope(String name, SCOPE type)
    {
        if(ASTStackHandler.currFunct != null)
        {
            if(type == SCOPE.LOCAL)
                ASTStackHandler.currFunct.addLocalVar(name);
            else if(type == SCOPE.PARAM)
                ASTStackHandler.currFunct.addParamVar(name);        
        }
    }

    public static void pushFunctionCall(String funcName, ASTNodeType callType)
    {
        ASTNode funcCall = new ASTNode(callType, funcName, null);
        ASTStackHandler.ASTStack.add(funcCall);
        //ASTStackHandler.last = funcCall;
        if(callType == ASTNodeType.FUNC_CALL_END)
        {
            ASTStackHandler.currTermNode = new ASTNode(ASTNodeType.TEMP_VAR, null, null);
        }
    }

    public static void pushFuncParamNode()
    {
        if(ASTStackHandler.SubTreeStack.size() == 0)
            ASTStackHandler.ASTStack.add(ASTStackHandler.currTermNode);
        else
            ASTStackHandler.ASTStack.add(ASTStackHandler.SubTreeStack.pop());
    }

    public static void addFuncInfoNode(ASTNodeType type)
    {
        ASTNode node = new ASTNode(type, null, null);
        ASTStackHandler.last = node;
        ASTStackHandler.ASTStack.add(node);
        if(type == ASTNodeType.RETURN)
        {
            ASTStackHandler.updateCurrTree();            
        }
    }
    
    public static void setFuncRetType(String type)
    {
        if(ASTStackHandler.currFunct != null && ASTStackHandler.currFunct.returnType == VARTYPE.INV)
        {
            ASTStackHandler.currFunct.setReturnType(type);
        }
    }

    /*****************************************Expression AST Creation Methods*********************************************/

    public static void updateCurrTree()
    {
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

    /**************************FOR Loop Structure Methods ************************************/
    public static void pushFORStructure(ASTNodeType type, SymbolTable scope)
    {
        ASTNode newRoot = new ASTNode(type, null, scope);
        if(ASTStackHandler.first == null && type == ASTNodeType.FOR)
            ASTStackHandler.first = newRoot;
        boolean optimize = false;
        if(ASTStackHandler.structStack.size() > 0 && (ASTStackHandler.structStack.peek().getType() == ASTNodeType.FI
             || ASTStackHandler.structStack.peek().getType() == ASTNodeType.ROF)){
             optimize = ASTStackHandler.last.getType() == ASTNodeType.FI || ASTStackHandler.last.getType() == ASTNodeType.ROF;}
        int labelNum = optimize? ASTStackHandler.structStack.peek().getLabel() : generateIR.labelNumber++;
        if(optimize)
            ASTStackHandler.structStack.pop().setLabel(-1);
        else if(ASTStackHandler.structStack.size() > 0 && ASTStackHandler.structStack.peek().getType() != ASTNodeType.FOR_INCR)
            ASTStackHandler.structStack.pop();    
        if(type == ASTNodeType.ROF)
        { 
            ASTNode incrNode = ASTStackHandler.structStack.pop();
            incrNode.setLabel(labelNum);
            newRoot.setLabel(generateIR.labelNumber++);
            incrNode.getParent().setJumpLabel(generateIR.labelNumber - 1);
        }
        else
            newRoot.setLabel(labelNum); 

             //ASTStackHandler.structStack.pop().setLabel(-1);
        //else 
          // newRoot.setLabel(generateIR.labelNumber++);

        //System.out.println("Enters FOR node creation Sized of stack : " + ASTStackHandler.structStack.size());
        //if(type == ASTNodeType.ROF){
           //System.out.println("Stack Top Node Type on ROF encounter : "+ASTStackHandler.structStack.peek().getType().name());
           /*if(ASTStackHandler.structStack.peek().getType() == ASTNodeType.FOR){
            ASTStackHandler.structStack.peek().setJumpLabel(newRoot.getLabel());
            newRoot.setJumpLabel(ASTStackHandler.structStack.pop().getLabel());     
        }*/
        //else if(ASTStackHandler.structStack.peek().getType() == ASTNodeType.FOR_INCR)
        //{
           //newRoot.setJumpLabel(ASTStackHandler.structStack.pop().getJumpLabel());                 
        //}
       //}
            //newRoot.setJumpLabel(ASTStackHandler.structStack.pop().getLabel());
        ASTStackHandler.ASTStack.add(newRoot);
        ASTStackHandler.structStack.add(newRoot);
        //System.out.println("Stack Top Node Type on ROF/FOR encounter : "+newRoot.getType().name());
        ASTStackHandler.last = newRoot;
    }

    /*********************************** IF ELSE structure AST methods*****************************/
    public static void pushIFELSEStructureTree(ASTNodeType type, SymbolTable scope)
    {
        //System.out.println("Enters IF ELSE node generation for :"+type.name());
        ASTNode newRoot = new ASTNode(type, null, scope);
        if(ASTStackHandler.first == null && type == ASTNodeType.IF)
            ASTStackHandler.first = newRoot;
        ASTStackHandler.ASTStack.add(newRoot);
        if(type == ASTNodeType.IF)
        { 
            if(ASTStackHandler.structStack.size() > 0 && (ASTStackHandler.structStack.peek().getType() == ASTNodeType.FI 
                || ASTStackHandler.structStack.peek().getType() == ASTNodeType.ROF))
                ASTStackHandler.structStack.pop();
            //newRoot.setJumpLabel(generateIR.labelNumber++);
        }
        else if(type == ASTNodeType.FI || type == ASTNodeType.ELSE)
        {
            //Label for the else structure
            if(type == ASTNodeType.FI && (ASTStackHandler.structStack.peek().getType() == ASTNodeType.FI || ASTStackHandler.structStack.peek().getType() == ASTNodeType.ROF))
            {
                newRoot.setLabel(ASTStackHandler.structStack.peek().getLabel());
                ASTStackHandler.structStack.pop().setLabel(-1);
            }
            else 
            {
                if(ASTStackHandler.structStack.peek().getType() != ASTNodeType.IF && type == ASTNodeType.ELSE)
                    ASTStackHandler.structStack.pop();
                newRoot.setLabel(generateIR.labelNumber++);
            }
            //System.out.println("Stack Node being popped "+ ASTStackHandler.structStack.peek().getType().name());

            ASTStackHandler.structStack.pop().setJumpLabel(newRoot.getLabel());
        }
        ASTStackHandler.structStack.add(newRoot); 
        //System.out.println("Stack Node just pushed "+ ASTStackHandler.structStack.peek().getType().name());
        ASTStackHandler.last = newRoot;
        //System.out.println("Exits IF ELSE node generation");
    }

    public static void setCondExpr(String dir, String compOp)
    {   
        ASTNode expr = null;
        if(!dir.equals("c"))
        {
            if(ASTStackHandler.SubTreeStack.size() == 0)
                expr = ASTStackHandler.currTermNode;
            else 
                expr = ASTStackHandler.SubTreeStack.pop();
        }
        if(dir.equals("l"))
            ASTStackHandler.condLeft = expr;
        if(dir.equals("c"))
        {
            OPERATION oper;
            //System.out.println("Parsed compare operator : "+compOp.length());
            switch(compOp.toCharArray()[0])
            {
                case '=' : oper = OPERATION.EQ; break;
                case '!' : oper = OPERATION.NE; break;
                case '>' : if(compOp.length() > 1 /*&& compOp.toCharArray()[1] == '=')*/) {oper = OPERATION.GE;} else {oper = OPERATION.GT; }break;
                case '<' : if(compOp.length() > 1) {oper = OPERATION.LE; } else {oper = OPERATION.LT;} break;
                default : oper = OPERATION.NE;
            }
            ASTNode condRoot = new ASTNode(ASTNodeType.COMPOP, compOp, null);
            condRoot.setLeftChild(ASTStackHandler.condLeft);
            condRoot.setParentStructure(ASTStackHandler.structStack.peek());
            /*if(condRoot.getParent().getType() == ASTNodeType.FOR)
            {   
                System.out.println("Enters COND expr Stack Push");
                condRoot.setLabel(ASTStackHandler.structStack.pop().getLabel());
                ASTStackHandler.structStack.add(condRoot);
            }*/
            condRoot.setOperation(oper);

             //System.out.println("Enters FOR TEST expr tree creation");
            ASTStackHandler.ASTStack.add(condRoot);
            ASTStackHandler.last = condRoot;
        }

        if(dir.equals("r"))
            ASTStackHandler.last.setRightChild(expr);

        //System.out.println("EXITS FOR TEST expr tree creation");
    }

    /***********************************Assignment tree and Expression tree methods*********************/
    public static void pushAssignmentTree(SymbolTable treeScope)
    {
    	ASTNode newRoot = new ASTNode(ASTNodeType.ASSIGNMENT, "=", treeScope);
    	
    	// Initialize first pointer to FIFO
    	if(ASTStackHandler.first == null)
    		ASTStackHandler.first = newRoot;
    	ASTStackHandler.ASTStack.add(newRoot);
    	
        //System.out.println("Left Child "+id);
    	newRoot.setLeftChild(ASTStackHandler.currTermNode);
    	last = newRoot;
        //newRoot.setDataObject();
    }

    public static void changeNodeType(ASTNodeType newType, boolean dummy)
    {
        if(newType == ASTNodeType.FOR_INCR && !dummy)
        {
            ASTStackHandler.last.setType(newType);
            ASTStackHandler.last.setJumpLabel(ASTStackHandler.structStack.peek().getLabel());
            ASTStackHandler.last.setParentStructure(ASTStackHandler.structStack.pop());
            //System.out.println("Enters here for FOR INCR stack push" + ASTStackHandler.last.getParent().getType().name());
            ASTStackHandler.structStack.add(ASTStackHandler.last);
            //System.out.println("Enters INCR node type change!");
        }
        else if(newType == ASTNodeType.FOR_INCR)
        {
            ASTNode dummyINCR = new ASTNode(ASTNodeType.FOR_INCR, null, null);
            ASTStackHandler.ASTStack.add(dummyINCR);
            dummyINCR.setJumpLabel(ASTStackHandler.structStack.peek().getLabel());
            dummyINCR.setParentStructure(ASTStackHandler.structStack.pop());
            ASTStackHandler.structStack.push(dummyINCR);
        }
        //if(newType == ASTNodeType.FOR_INIT)
        //    ASTStackHandler.last.setLabel(ASTStackHandler.structStack.peek().getLabel());
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

        //Case when Top is Stack is Left Child of Parent Root Node  (Push Root Node to Stack after poppig Left SUb Tree)
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
                {System.out.println("EXPRESSION Error : Identifier not declared!");
                System.exit(0);}

        }
        ASTNode litNode = new ASTNode(leafASTNodeType, name_value, idScope);
    	ASTStackHandler.currTermNode = litNode;
    }

    //In Order Traversal of tree
    public static void traverseTree(ASTNode root)
    {
        if(root.getType() == ASTNodeType.COMPOP && root.getParent().getLabel() != -1)
            generateIR.jumpLabelIR(IRNode.OPCODE.LABEL, Integer.toString(root.getParent().getLabel()));

        if(root.getType() == ASTNodeType.FOR_INCR)
            generateIR.jumpLabelIR(IRNode.OPCODE.LABEL, Integer.toString(root.getLabel()));

        if(root.getLeftChild() != null){
            //if(root.getLeftChild().getType() != ASTNodeType.IDENTIFIER || root.getLeftChild().getType() != ASTNodeType.LITERAL)
            //System.out.print("(");
            //root.getLeftChild().printNode();
            ASTStackHandler.traverseTree(root.getLeftChild());
         }
         //System.out.println("Identif")
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

