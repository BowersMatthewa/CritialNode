/** 
 * AdjacencyList.java
 *
 * Author: Kyle Hammerschmidt
 * Author: Matthew Bowers
 * 
 * A class to represent an adjacency list for a graph
 */

import java.util.ArrayList;


public class AdjacencyList{
	
	private ArrayList<Integer>[] nodes;

	private int size;


	/**
	 * Constructor
	 *
	 * instantiates an array of v ArrayLists of length v-1. At most any node will have v-1 adjacencies.
	 *
	 * @params n - The number of nodes in the graph
	 */
	public AdjacencyList(int n){
		nodes = new ArrayList[n];
		size = n;
		for(int i = 0; i <n; i++){
			nodes[i] = new ArrayList<Integer>(n-1);
		}
	}
	
	/**
	 * addEdge - adds and edge to the graph
	 *
	 * If the edge is directed an edge from start to finish will be added
	 * If the edge is not direced an edge will be added in both directions
	 *
	 * @params int start - the beginning of the edge
	 * @params int finish - the end of the the edge
	 * @params boolean directed - is this edge directed
	 */
	public void addEdge(int start, int finish, boolean dir){
		nodes[start].add(finish);
		if(!dir){
			nodes[finish].add(start);
		}
	}
	
	public ArrayList<Integer> getNodeNeighbors(int start) throws IllegalArgumentException {
		if(start >= size || start < 0){
			throw new IllegalArgumentException("no such node");
		}
		else
			return nodes[start];
	}

	/**
	 * printList - 
	 *
	 * iterates through the entire array list and prints it out to stdout
	 */
	public void printList(){
		int i = 1;
		for(ArrayList<Integer> neighs : nodes){
			System.out.printf("Node %d neighbors: \n", i);
			for(Integer node : neighs){
				System.out.print(node +1 + "  ");
			}
			System.out.println(); 
			i++;
		}
	}

	/**
	 * removeNode - removes incoming and outgoing edges of a node
	 *
	 * @params int node - the number of the node to be removed
	 */
	public void removeNode(int node) throws IllegalArgumentException{
		if(node<0 || node >= size){
			throw new IllegalArgumentException("No such node");
		}
		for(Integer neigh : nodes[node]){
			// remove node from everyone elses adjacency list
			nodes[neigh].remove((Integer)node);
		}
		nodes[node].clear();
	}

	/**
	 * removeEdge - removes an edge from the graph 
	 *
	 * If the edge is not directed the edge will be removed in both directions
	 *
	 * @params int start - the beginning of the edge
	 * @params int finsih - the end of the edge
	 * @params boolean dir - is the edge directed
	 */
	public void removeEdge(int start, int finish, boolean dir)throws IllegalArgumentException{
		if(!nodes[start].remove((Integer)finish)){
			throw new IllegalArgumentException("no such edge");
		}
		if(!dir){
			if(!nodes[finish].remove((Integer)start)){
				throw new IllegalArgumentException("no such edge");
			}
		}
	}

}
