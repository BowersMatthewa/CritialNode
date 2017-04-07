package com.github.bowersmatthew.criticalnode

/**
 * Java class to represent a graph of edges and nodes
 */

public class Graph{
    // variables
    ArrayList<Node> nodes;
	ArrayList<Edge> edges;
	int nodeCount, edgeCount;

	public Graph(ArrayList<Integer>, 
	/**
	 * Add a node to the graph
	 *
	 * @param newNode the node which will be added to the graph
	 * @param edges an array of Edge objects describing the edges incident to this new Node
	 * @exception throws illegal argument exception 
	 */
	private void addNode(Node newNode, Edge[] newEdges){
		if(nodeCount+1>nodes.length){
			doubleArray(nodes);
		}
	}

	private <T> void doubleArray(T[] array){
		int i;
		T[] temp = new T[array.length*2];
		for(i = 0; i<array.length; i++){
			temp[i]=array[i];
		}
	}

}
