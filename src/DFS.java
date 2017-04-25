import java.util.*;

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
	private ArrayList<ArrayList<Node>> forest = new ArrayList<ArrayList<Node>>();
	private int nodeCount;
	private int forestCount;
	private ArrayList<Node> nodes;
	public enum Color {white, black, grey};
	public int time = 0;
	private Graph graph;
	private boolean done;
	
	private class Node{
		 
		private String name;
		private Node pred;
		private int dt;
		private int ft;
		private Color color;

		public Node(String name) throws IllegalArgumentException{
			setName(name);
			setColor(Color.white);
		}
			
		private void setColor(Color color) {
			this.color = color;
		}
		
		public Color getColor(){
			return color;
		}

		public String getName(){
			return name;
		}

		private void setName(String name)throws IllegalArgumentException{
			if(name.equals(" ")) throw new IllegalArgumentException("name must not be blank");
			else this.name = name;
		}

		public Node getPred() throws NullPointerException{
			if(pred == null) throw new NullPointerException("predecessor is not set");
			else return pred;
		}

		public void setPred(Node pred)throws IllegalArgumentException{
			if(!nodes.contains(pred)) throw new IllegalArgumentException("No such node");
			else this.pred = pred;
		}

		public int getDiscTime()throws NullPointerException{
			return dt;
		}

		public void setDiscTime(int time) throws IllegalArgumentException{
			if(time < 0) throw new IllegalArgumentException("discovery time must be nonnegaltive");
			else dt = time;
		}

		public int getFinTime(){
			return ft;
		}

		public void setFinTime(int time) throws IllegalArgumentException{
			if(time < 0) throw new IllegalArgumentException("finish time must be nonnegaltive");
			else ft = time;
		}
		
	}

	public DFS(Graph graph){
		this.graph = graph;
		done = false;
		this.forestCount = 0;
		nodeCount = graph.nodeCount;
		for(int i = 0; i < nodeCount; i++){
			nodes.add(new Node(Integer.toString(i)));
		}
		doDFS();
	}
	
	/**
	 * getForest - return the forest which results from the DFS
	 * @return
	 */
	public ArrayList<ArrayList<Node>> getForest() throws Exception{
		if(!done){throw new Exception("First do the DFS");}
		else return forest;
	}
	
	/**
	 * doDFS - carry out the DFS starting with node 0
	 */
	private void doDFS(){
		for(Node n : nodes){
			if(n.getColor() == Color.white){
				forestCount++;
				ArrayList<Node> subGraph = new ArrayList<Node>();
				forest.add(subGraph);
				visit(n, subGraph);	
			}
		}
		done = true;
	}
	
	/**
	 * visit - a subroutine of the DFS to visit each Node. This enables the recursive call.
	 * @param n the Node to be visited
	 * @param subGraph the current subgraph
	 */
	private void visit(Node n, ArrayList<Node> subGraph){
		time++;
		n.setColor(Color.grey);
		n.setDiscTime(time);
			for(Integer neigh : graph.getAdjacencyList().getNodeNeighbors(Integer.parseInt(n.getName()))){
				Node current = nodes.get(neigh);
				if(current.getColor() == Color.white){
					subGraph.add(current);
					current.setPred(n);
					visit(current, subGraph);
				}
			}
		n.setColor(Color.black);
		time++;
		n.setFinTime(time);
	}
	
	/**
	 * countPairWise - only works on undirected graphs
	 * @return
	 */
	public int countPairWise() throws Exception{
		if(!done){throw new Exception("Must first complete the DFS");}
		else if(graph.getDirected()){throw new Exception("not applicable to directed graphs");}
		else{
			int count = 0;
				for(ArrayList<Node> g : forest){
					count+= g.size()*(g.size()-1)/2;
				}
			return count;
		}
	}
}
