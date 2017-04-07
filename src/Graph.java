package com.github.bowersmatthew.criticalnode

/**
 * Java class to represent a graph of edges and nodes
 */

public class Graph{
    // variables
    AdjacencyList list;
	AdjacencyMatric matrix;
	int nodeCount, edgeCount;

	public Graph(int nodeCount, int edgeCount) throws IllegalArgumentExeption{
		if(nodeCount<0 || edgeCount<0){
			throw new IllegalArgumentException("nodeCount and EdgeCount must be positive integers");
		}
		else{
			list = new AdjacencyList(nodeCount);
			matric = new AdjacencyMatric(nodeCount);
			this.nodeCount = nodeCount;
			this.edgeCount = edgeCount;
		}
	}

	public void addEdge(int u, int v) throws IllegalArgumentException{
		if(u > nodeCount || v > nodeCount || u<0 || v<0){
			throw new IllegalArgumentException("attempting to add edge to one or more edges which do not exist");
		}
		list.addEdge(u,v);
		matrix.addEdge(u,v);
	}

	public void removeNode(int node)throws IllegalArgumentException{
		if(node < 0 || node > nodeCount || !node.getValid()){
			throw new IllegalArgumentException("Attempting to remove an invalid node");
		}
	}

}
