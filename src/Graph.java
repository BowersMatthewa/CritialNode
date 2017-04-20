// package com.github.bowersmatthew.criticalnode

/**
 * Java class to represent a graph of edges and nodes
 */

public class Graph{
    // variables
    AdjacencyList list;
	AdjacencyMatric matrix;
	// ArrayList<Node> nodes;
	int nodeCount, edgeCount;
	boolean dir;

	public Graph(int nodeCount, int edgeCount, boolean dir) throws IllegalArgumentExeption{
		if(nodeCount<0 || edgeCount<0){
			throw new IllegalArgumentException("nodeCount and EdgeCount must be positive integers");
		}
		else{
			list = new AdjacencyList(nodeCount);
			matric = new AdjacencyMatric(nodeCount);
			this.nodeCount = nodeCount;
			this.edgeCount = edgeCount;
			this.dir = dir;
		}
	}

	public void addEdge(int u, int v) throws IllegalArgumentException{
		if(u > nodeCount || v > nodeCount || u<0 || v<0){
			throw new IllegalArgumentException("attempting to add edge to one or more edges which do not exist");
		}
		
		list.addEdge(u,v,dir);
		matrix.addEdge(u,v);
	}

	public void removeNode(int node)throws IllegalArgumentException{
		if(node < 0 || node > nodeCount){
			throw new IllegalArgumentException("Attempting to remove an invalid node");
		}
		
		for(Integer neigh : list.getNodeNeighbors(start)){
			list.removeEdge(start, neigh, 0);
		}
	}

	/**
	 * addNode - adds a node to the graph 
	 *
	 * @param newNode - the node which is too be added
	 * @throws IllegalArgumentException - if attempting to add a node which is already 
	 * 				in the graph
	 */
	public void addNode(Node newNode){
		if(nodes.contains(newNode) && newNode.isValid()){
			throw new IllegalArgumentException("Node is already in the graph")
		}else if(nodes.contains(newNode) && !newNode.isValid()){
			newNode.setValid(1);
		}else{
			nodes.add(newNode);
			// add this new nodes edges to the adjacency list
			for(Node n : newNode.getNeighbors()){
				// TODO: Finish this
			}
		}
		
	}
	
	/**
	 * getDirected
	 *
	 * @return boolean dir - returns the dir value of the graph indicating if it is directed or not
	 */
	public boolean getDirected(){
		return dir;
	}

	/**
	 * transitive closure
	 */
	public Graph doTransitiveClosure(Graph og){
		Graph tc = new Graph(nodeCount, 0, og.getDirected());


	}
}
