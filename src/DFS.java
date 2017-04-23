import java.util.*

/**
 * DFS.java
 *
 * Author: Matthew Bowers
 *
 * A java class to implement DFS
 *
 * Constructs a graph suitable for a DFS and some support fuctions
 * Can return a spanning forest
 * Counts pairwise connectivity
 */

public class DFS{
	Graph[] forest;
	Stack stack;
	int nodeCount;
	int forestCount;
	ArrayList<Node> nodes;

	private class Node{
		private String name;
		private Node pred;
		private int dt;
		private int ft;

		public Node(String name) throws IllegalArgumentException{
			setName(name);
		}

		public String getName(){
			return name;
		}

		private void setName(String name)throws IllegalArgumentException{
			if(name.equals(" ") throw new IllegalArgumentException("name must not be blank");
			else this.name = name;
		}

		public Node getPred() throws NullPointerException{
			if(pred == null) throw new NullPointerException("predecessor is not set");
			else return pred;
		}

		public void setPred(Node pred)throws IllegalArgumentException{
			if(!node.contains(pred)) throw new IllegalArugmentException("No such node");
			else this.pred = pred;
		}

		public int getDiscTime()throws NullPointerException{
			if(dt == null) throw new NullPointerException("discovery time has not been set");
			else return dt;
		}

		public void setDiscTime(int time) throws IllegalArgumentException{
			if(time < 0) throw new IllegalArgumentExcpetion("discovery time must be nonnegaltive");
			else dt = time;
		}

		public int getFinTime()throws NullPointerException{
			if(dt == null) throw new NullPointerException("finish time has not been set");
			else return ft;
		}

		public void setFinTime(int time) throws IllegalArgumentException{
			if(time < 0) throw new IllegalArgumentExcpetion("finish time must be nonnegaltive");
			else ft = time;
		}
		
	}

	public DFS(Graph graph){
		forestCount = 0;
		nodeCount = graph.nodeCount;
		stack = new Stack();
		int forestCount = 0;
		for(int i = 0; i < nodeCount; i++){
			nodes[i] = new
		}
	}

	public Graph[] doDFS(
}
