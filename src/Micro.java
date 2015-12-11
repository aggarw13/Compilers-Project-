import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

class Micro 
{
	public static void main (String[] args) throws Exception
	{
		ANTLRFileStream data = null;
		try
		{
			data = new ANTLRFileStream(args[0]);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Please enter the filename to be scanned!");
		}
		catch(FileNotFoundException ex) 
		{
         	System.err.println("Invalid micro file!");
		}	        
		MicroLexer lexer = new MicroLexer(data);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		MicroParser parser = new MicroParser(tokenStream);
		parser.setErrorHandler(new MicroErrorStrategy());
		try
		{
			parser.program();
			ASTNode root;
			short func_index = -1;
			short nested_funcCall = 0;
			Stack<ASTNode> funcCallStack = new Stack<ASTNode>();
			//SemanticDataHandler.printSemanticStack(SemanticDataHandler.globalScope);
			//System.out.println("Parsed Functions detected "+ ASTStackHandler.functionList.size());
			for(Iterator<ASTNode> it = ASTStackHandler.getASTFIFO().iterator() ; it.hasNext();)
			{	
				root = it.next();
		        if(root.getType() == ASTNodeType.FOR_INCR)
		        {
		            ASTStackHandler.FORincrStack.add(root);
		        	continue;
		        }
		        else if(root.getType() == ASTNodeType.ROF && ASTStackHandler.FORincrStack.size() > 0)
		        {
		        	//System.out.println("Enters here for priting ROF! size : "+ASTStackHandler.FORincrStack.size());
		            ASTStackHandler.traverseTree(ASTStackHandler.FORincrStack.pop());
		        }
		        else if(root.getType() == ASTNodeType.FUNC_BEGIN)
		        {
		        	func_index++;
		        	generateIR.tempNumber = 0;
		        	ASTStackHandler.currFunct = ASTStackHandler.functionList.get(func_index);
		        	//ASTStackHandler.currFunct = ASTStackHandler.functionList.get(ASTStackHandler.functionList.indexOf(root.getNameValue()));
		        }
		      /*  else if(root.getType() == ASTNodeType.RETURN)
		        {
		      		ASTNode next = it.next();
		      		ASTStackHandler.traverseTree(root);
		      		ASTStackHandler.traverseTree(next);
		        	root = null;
		        }*/
		        //TODO : Needs to be changed to accommodate Subexpression Function Calls
		        else if((root.getType() == ASTNodeType.ASSIGNMENT || root.getType() == ASTNodeType.RETURN) && root.getRightChild().getType() == ASTNodeType.TEMP_VAR)
		        {
		        	ASTNode assignNode = root;
		        	root = it.next();
		        	ASTNode paramNode;
			       	
			        /*if(root.getType() == ASTNodeType.FUNC_CALL_BEGIN)
			        {

			        	ASTNode paramNode = it.next();
			        	if(paramNode.getType() == ASTNodeType.FUNC_CALL_BEGIN)
			        		nested_funcCall++;
			        	while(paramNode.getType() != ASTNodeType.FUNC_CALL_END)
			    		{
			    			ASTStackHandler.traverseTree(paramNode);
			    			root.addParam(paramNode.getDataObject().getDest());
			    			paramNode = it.next();
			    		}
			        }*/
			        if(root.getType() == ASTNodeType.FUNC_CALL_BEGIN)
			        {
			        	System.out.println("Reached here for Function Call stack");
			       		funcCallStack.push(root);
			        	paramNode = it.next();
			        	while(!funcCallStack.empty())
			        	{
			        		System.out.println("Generated call to "+root.getLabel());
			        		root = funcCallStack.peek();
			        		paramNode = it.next();
			        		while(paramNode.getType() != ASTNodeType.FUNC_CALL_BEGIN)
				        	{
				        		root = paramNode;
				       			paramNode = it.next();
				        		funcCallStack.push(root);
				        		nested_funcCall++;
				        	}
				        	while(paramNode.getType() != ASTNodeType.FUNC_CALL_END)
				    		{
				    			if(paramNode.getType() == ASTNodeType.FUNC_CALL_BEGIN)
				    				break;
				    			ASTStackHandler.traverseTree(paramNode);
				    			root.addParam(paramNode.getDataObject().getDest());
				    			paramNode = it.next();
				    		}
				    		funcCallStack.pop();
				    		ASTStackHandler.traverseTree(root);
			        	}
			        }
			        //ASTStackHandler.traverseTree(root);
			       	root = assignNode;
			       	paramNode = it.next();
			       	if(assignNode.getType() == ASTNodeType.RETURN && paramNode.getType() != ASTNodeType.FUNC_END)
		      			generateIR.funcScope(ASTNodeType.FUNC_END);
		      		root = paramNode;
			    }
			    if(root != null)
					ASTStackHandler.traverseTree(root);
			}		

			CFGHandler.generateCFG();

			CFGHandler.computeLiveness();

			List<Integer> tinyFuncTrack = new LinkedList<Integer>();
			boolean newFuncScope = false;
			int prevRegCount = 0;
			//System.out.println("Number of IR instructions : "+generateIR.IRCodeList.size());
			System.out.println(";IR Code");
			generateTinyCode.allocateMemory();
			generateTinyCode.mainJumpCode();
			func_index = -1;
			generateTinyCode.emptyRegisters(null);

			for(IRNode instr : generateIR.IRCodeList)
			{
				if(instr.opcode == IRNode.OPCODE.LABEL && instr.labelTarget.length() > 3 
					&& !generateTinyCode.checkifInt(instr.labelTarget))
						System.out.println();
				System.out.print(";");
				instr.printIR();

				//instr.printLiveIn();

				//instr.printLiveOut();
				if(instr.leader)
					generateTinyCode.emptyRegisters(instr);

				if(instr.opcode == IRNode.OPCODE.LINK)// && !generateTinyCode.checkifInt(instr.labelTarget))
				{
					ASTStackHandler.currFunct = ASTStackHandler.functionList.get(++func_index);
					generateTinyCode.IRTempMap = new HashMap<String,String>();
					prevRegCount = generateTinyCode.regNumber;
					tinyFuncTrack.add(generateTinyCode.CodeList.size());
					generateTinyCode.regNumber = 0;
					newFuncScope = true;
				}
				generateTinyCode.createCode(instr);

				if(newFuncScope)
				{
					newFuncScope = false;	
					if(tinyFuncTrack.size() > 1)
					{	
						TinyCode node = generateTinyCode.CodeList.get(tinyFuncTrack.get(tinyFuncTrack.size() - 2));
						if(node.instr_type == TinyCode.INSTR_TYPE.STACK && node.op_stack == TinyCode.OPCODE_STACK.LINK)
							node.setLinkCount(prevRegCount);
					}
				}
			}

			System.out.println("Number of Tiny Instructions "+generateTinyCode.CodeList.size());
			int tinyCount = 0;
			//System.out.println(";Tiny Code");
			for(TinyCode instr : generateTinyCode.CodeList)
			{
				System.out.print(tinyCount+++". ");
				if(instr != null)
					instr.printInstr();
				else
					System.out.println();				
			}
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
