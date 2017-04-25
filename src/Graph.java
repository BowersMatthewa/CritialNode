import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

// package com.github.bowersmatthew.criticalnode

/**
 * Java class to represent a graph of edges and nodes
 */

public class Graph{
    // variables
    AdjacencyList list;
	Random rand = new Random();
	ArrayList<Integer> removed;
	public int nodeCount, edgeCount;
	boolean dir;

	public Graph(int nodeCount, int edgeCount, boolean dir) throws IllegalArgumentException{
		if(nodeCount<0 || edgeCount<0){
			throw new IllegalArgumentException("nodeCount and EdgeCount must be positive integers");
		}
		else{
			list = new AdjacencyList(nodeCount);
			//matric = new AdjacencyMatric(nodeCount);
			this.nodeCount = nodeCount;
			this.edgeCount = edgeCount;
			this.dir = dir;
			removed = new ArrayList<Integer>();
		}
	}

	public Graph copyGraph(Graph og){
		Graph ng = new Graph(og.nodeCount, og.edgeCount, og.getDirected());
		for(int i = 0; i < nodeCount; i++){
			for(Integer neigh : list.getNodeNeighbors(i)){
				ng.addEdge(i, neigh);
			}
		}
		return ng;
	}
	
	public void addEdge(int u, int v) throws IllegalArgumentException{
		if(u > nodeCount || v > nodeCount || u<0 || v<0){
			throw new IllegalArgumentException("attempting to add edge to one or more edges which do not exist");
		}
		
		list.addEdge(u,v,dir);
		//matrix.addEdge(u,v);
	}
	
	public AdjacencyList getAdjacencyList(){
		return list;
	}

	public void removeNode(int node)throws IllegalArgumentException{
		if(node < 0 || node > nodeCount){
			throw new IllegalArgumentException("Attempting to remove an invalid node");
		}
		try{
			list.removeNode(node);
		}catch(IllegalArgumentException iae){
			System.out.printf("Error removing node: %d: \n" + iae.getMessage(), node);
		}catch(ConcurrentModificationException cme){
			System.out.printf("Error removing node: %d: \n" + cme.getMessage(), node);
			cme.printStackTrace();
			removeRandom();
		}
	}

	/**
	 * addNode - adds a node to the graph 
	 *
	 * @param newNode - the node which is too be added
	 * @throws IllegalArgumentException - if attempting to add a node which is already 
	 * 				in the graph
	 */
/*
	public void addNode(Node newNode){
		if(nodes.contains(newNode) && newNode.isValid()){
			throw new IllegalArgumentException("Node is already in the graph");
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
*/
	/**
	 * getDirected
	 *
	 * @return boolean dir - returns the dir value of the graph indicating if it is directed or not
	 */
	public boolean getDirected(){
		return dir;
	}

	/**
	 * removeRandom - removes a random node from the graph
	 */
	public void removeRandom(){
		int node;
		do{
			node = rand.nextInt(nodeCount);
		}while(removed.contains((Integer)node));
		System.out.printf("Removing %d\n", node + 1);
		removed.add((Integer)node);
		removeNode(node);
	}
	
	/**
	 * transitive closure
	 */
	public boolean[][] doTransitiveClosure(){
		// set up an array of boolean matrices to 
		boolean[][][] matrices = new boolean[nodeCount][nodeCount][nodeCount];
		// set the first layer to be the adjacency matrix
		for(int i = 0; i < nodeCount; i++){
			ArrayList<Integer> neighbors = list.getNodeNeighbors(i);
			for(int j = 0; j < nodeCount; j++){
				if(i == j || neighbors.contains(j))
					matrices[0][i][j] = true;
				else
					matrices[0][i][j] = false;
			}
		}
		for(int k = 1; k < nodeCount; k++){
			for(int i = 0; i < nodeCount; i++){
				for(int j = 0; j <nodeCount; j++){
					matrices[k][i][j] = matrices[k-1][i][j] || (matrices[k-1][i][k] && matrices[k-1][k][j]);
				}
			}
		}
		return matrices[nodeCount-1];
	}
}
