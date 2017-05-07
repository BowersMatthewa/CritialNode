import java.util.ArrayList;
import java.util.LinkedList;

/**
 * BFS.java
 * 
 * A support class for graph which will execute a BFS and return the result
 * @author gelderlin
 *
 */
public class BFS {
	private Graph graph;
	private ArrayList<ArrayList<Node>> forest;
	private LinkedList<Node> q, stepQ;
	private Node[] nodes, stepNodes;
	private int currentDistance;
	private ArrayList<Node> toRemove, inNodes;
	
	private class Node{
		int name;
		Node pred;
		int dist;
		boolean visited;
		
		private Node(int name){
			setName(name);
			dist = Integer.MAX_VALUE;
			visited = false;
		}
		
		private boolean getVisited(){
			return visited;
		}
		
		private void setVisited(boolean value){
			visited = value;
		}
		
		private int getName(){
			return name;
		}
		
		private void setName(int name){
			this.name = name;
		}
		
		private Node getPred(){
			return pred;
		}
		
		private void setPred(Node pred){
			this.pred = pred;
		}
		
		private ArrayList<Integer> getNeighbors(){
			return graph.getAdjacencyList().getNodeNeighbors(name);
		}
		
		private int getDist(){
			return dist;
		}
		
		private void setDist(int d){
			dist = d;
		}
		
		@Override
		public String toString(){
			return "Node: " +Integer.toString(name) + "Predecesor: " + Integer.toString(pred.getName()) + "Distance: " + Integer.toString(dist); 
		}
	}
	
	public BFS(Graph graph){
		this.graph = graph;
		forest = new ArrayList<ArrayList<Node>>();
		setupNodes(graph.nodeCount);
	}
	
	/**
	 * setupNodes - makes an array of Nodes to match the size of the graph
	 * @param n
	 */
	public void setupNodes(int n){
		nodes = new Node[n];
		stepNodes = new Node[n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
			stepNodes[i] = new Node(i);
		}
	}
	// add caller method to set up the root then pass it to this method
	public void doBFS(){
		for(Node n : nodes){
			if(n.getDist() == Integer.MAX_VALUE){
				ArrayList<Node> tree = new ArrayList<Node>();
				forest.add(tree);
				tree.add(n);
				q.add(n);
				n.setDist(0);
				n.setPred(n);
				while(!q.isEmpty()){
					Node current = q.poll();
					visit(current, tree);
				}
			}
		}
	}
	
	private void visit(Node node, ArrayList<Node> tree){
		for(Integer neigh : node.getNeighbors()){
			if(nodes[(int)neigh].getDist() == Integer.MAX_VALUE){
				tree.add(nodes[neigh]);
				nodes[(int)neigh].setDist(node.getDist() + 1);
				nodes[(int)neigh].setPred(node);
				q.add(nodes[(int)neigh]);
			}
		}
	}
	
	public void printBFS(){
		for(Node n : nodes){
			System.out.printf("Node: %d Predecesor: %d\n", n.getName(), n.getPred().getName());
		}
	}
	
	public ArrayList<Node> getXNeighbors(int node, int n){
		Node root = stepNodes[node];
		stepQ = new LinkedList<Node>();
		inNodes = new ArrayList<Node>();
		inNodes.add(root);
		root.setPred(root);
		root.setDist(0);
		stepQ.add(root);
		int currentDistance = 0;
		while(currentDistance <= n && !stepQ.isEmpty()){
			Node current = stepQ.poll();
			for(Integer neigh : current.getNeighbors()){
				Node next = stepNodes[(int)neigh];
				if(next.getDist() == Integer.MAX_VALUE){
					next.setPred(current);
					next.setDist(current.dist+1);
					currentDistance = next.getDist();
					inNodes.add(next);
					stepQ.add(next);
				}
			}
		}
		//System.out.println(inNodes);
		return inNodes;
	}
	
	
	// This must be run after getXNeighbors
	// pass the output of getXNeighbors here to count how many out connections it has
	public int getOutConnections(ArrayList<Node> inList){
		int count = 0;
		ArrayList<Node> counted = new ArrayList<Node>();
		toRemove = new ArrayList<Node>();
		for(Node n : inList){
			for(Integer neigh : n.getNeighbors()){
				Node next = stepNodes[(int)neigh];
				if(!inList.contains(next) && !toRemove.contains(next)){
					count++;
					toRemove.add(next);
				}
			}
		}
		return count;
	}
	
	public int getInNodeSize(){
		return inNodes.size() + toRemove.size();
	}
	
	// Only after getOutConnections
	public ArrayList<Integer> getToRemove(){
		ArrayList<Integer> dead = new ArrayList<Integer>();
		for(Node n : toRemove){
			dead.add((Integer)n.getName());
		}
		return dead;
	}
}
