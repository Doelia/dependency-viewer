package step3;

import java.util.ArrayList;
import java.util.Hashtable;

public class CallGraph {
	
	public ArrayList<CallGraph.Node> nodes = new ArrayList<CallGraph.Node>();
	public Hashtable<String, Node> methodNode = new Hashtable<String, Node>();
	private static CallGraph instance;
	
	
	private CallGraph() {
		
	}
	
	public static CallGraph getInstance(){
		if(instance == null){
			instance = new CallGraph();
		}
		
		return instance;
	}
	
	public void addNode(Method method){
		Node node = new Node(method);
		nodes.add(node);
		methodNode.put(method.getName(), node);
	}
	
	public Node getNode(String m){
		return methodNode.get(m);
	}
	
	public Node getNode(Method m){
		return methodNode.get(m.getName());
	}
	
	@Override
	public String toString() {
		String ret = new String();
		for(Node node : nodes)
		{
			ret += node.toString() + "\n";
		}
		return ret;
	}
	
	
	public void calculateFanIn(){
		for(Node node : nodes)
		{
			Method fanin = node.method;
			for(Method m : node.getFanOut())
			{
				getNode(m).addFanIn(fanin);
			}
		}
	}

	
	class Node{
		
		Method method;
		ArrayList<Method> fanIn = new ArrayList<Method>();
		ArrayList<Method> fanOut = new ArrayList<Method>();
		
		public Node(Method method){
			this.method = method;
		}
		
		public void addFanIn(Method method){
			fanIn.add(method);
		}
		
		public void addFanOut(Method method){
			fanOut.add(method);
		}
		
		public ArrayList<Method> getFanIn() {
			return fanIn;
		}
		
		public ArrayList<Method> getFanOut() {
			return fanOut;
		}
		
		@Override
		public String toString() {
			String ret = new String();
			
			ret += "\nNode["+method.getName()+"] \n";
			ret += "FanIn: ";
			for(Method m : fanIn)
			{
				ret += m.getName() + " ";
			}
			ret += "\nFanOut: ";
			for(Method m : fanOut)
			{
				ret += m.getName() + " ";
			}
			
			ret += "\n";
			
			return ret;
		}
		
	}
}
