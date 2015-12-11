

import java.io.*;
import java.util.*;
import java.lang.Exception.*;


class CFGHandler
{
	public static IRNode currIRNode = null;
	public static IRNode prevIRNode = null;
	public static String currTarget = null;
	public static String currFunct = null;

	public static Stack<IRNode> worklist = new Stack<IRNode>();


/*	public static void findSuccessor()
	{

	}

	public static void findPredecessor()
	{

	}
*/
	public static IRNode findLabelNode(String label)
	{
		for(IRNode node : generateIR.labelNodes)
		{
			if(node.labelTarget.equals(label))
				return node;
		}
		return null;
	}


	public static void generateCFG()
	{
		IRNode prevNode = null, nextNode = null, currNode = null;
		boolean leader_flag = false;
		
			if(leader_flag)
			{
				currNode.leader = true;
				leader_flag = false;
			}

		for(Iterator<IRNode> it = generateIR.IRCodeList.iterator(); it.hasNext() ;)
		{
			prevNode = currNode;
			currNode = it.next();
			
			if(prevNode != null && prevNode.opcode != IRNode.OPCODE.RET && prevNode.opcode != IRNode.OPCODE.JUMP)
			{
				prevNode.success.add(currNode);
				currNode.predec.add(prevNode);
			}

			if(currNode.opcode == IRNode.OPCODE.GT || currNode.opcode == IRNode.OPCODE.GE ||
				currNode.opcode == IRNode.OPCODE.LT || currNode.opcode == IRNode.OPCODE.LE || 
				currNode.opcode == IRNode.OPCODE.EQ || currNode.opcode == IRNode.OPCODE.NE ||
				currNode.opcode == IRNode.OPCODE.JUMP)
			{
				//DEBUG_CHECK : Check if method returns node
				IRNode node = CFGHandler.findLabelNode(currNode.labelTarget);
				currNode.success.add(node);
				node.predec.add(currNode);
				leader_flag = true;
			}

			else if(currNode.opcode == IRNode.OPCODE.LABEL)
				currNode.leader = true;
		}
	}

	//TRUE if LIVE-IN set of Node gets updated
	public static boolean conductNodeLiveAnalysis(IRNode node)
	{
		Set<String> prevLivein = node.live_in;
		for(IRNode suc_node : node.success)
		{
			node.live_out.addAll(suc_node.live_in);
		}	

		node.live_in.addAll(node.gen);

		for(String var : node.live_out)
		{
			if(!node.kill.contains(var))
				node.live_in.add(var);
		}

		return !prevLivein.equals(node.live_in);
	}


	public static void computeLiveness()
	{
		CFGHandler.worklist.addAll(generateIR.IRCodeList);
		IRNode node = null;
		while(CFGHandler.worklist.size() > 0)
		{	
			node = CFGHandler.worklist.pop();

			if(CFGHandler.conductNodeLiveAnalysis(node))
			{
				for(IRNode predecNode : node.predec)
				{
					CFGHandler.worklist.push(predecNode);
				}
			}
		}
	}

	public static void printLiveSets()
	{
		for(IRNode node : generateIR.IRCodeList)
		{
			node.printIR();
			node.printLiveIn();
			node.printLiveOut();
		}

	}

}
