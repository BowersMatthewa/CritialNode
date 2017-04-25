/** 
 * AdjacencyList.java
 *
 * Author: Kyle Hammerschmidt
 * Author: Matthew Bowers
 * 
 * A class to represent an adjacency list for a graph
 */

import java.util.ArrayList;
import java.util.Iterator;


public class AdjacencyList{
	
	private ArrayList<Integer>[] nodes;

	private int size;


	/**
	 * Constructor
	 *
	 * instantiates an array of v ArrayLists of length v-1. At most any node will have v-1 adjacencies.
	 *
	 * @param n - The number of nodes in the graph
	 */
	public AdjacencyList(int n){
		nodes = new ArrayList[n];
		size = n;
		for(int i = 0; i <n; i++){
			nodes[i] = new ArrayList<Integer>(n-1);
		}
	}
	
	/**
	 * getNodes
	 * 
	 * @return the array of ArrayLists
	 */
	public ArrayList<Integer>[] getNodes(){
		return nodes;
	}
	
	/**
	 * addEdge - adds and edge to the graph
	 *
	 * If the edge is directed an edge from start to finish will be added
	 * If the edge is not direced an edge will be added in both directions
	 *
	 * @param int start - the beginning of the edge
	 * @param int finish - the end of the the edge
	 * @param boolean directed - is this edge directed
	 */
	public void addEdge(int start, int finish, boolean dir){
		nodes[start].add(finish);
		if(!dir){
			nodes[finish].add(start);
		}
	}
	
	/**
	 * 
	 * @param start
	 * @return
	 * @throws IllegalArgumentException
	 */
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
	 * @param int node - the number of the node to be removed
	 */
	public void removeNode(int node) throws IllegalArgumentException{
		if(node<0 || node >= size){
			throw new IllegalArgumentException("No such node");
		}
		Iterator<Integer> adj = nodes[node].iterator();
		while(adj.hasNext()){
			// remove node from everyone else's adjacency list
			int next = (int)adj.next();
			System.out.println("Removing: " + (node + 1) +"from: " + (next + 1));
			nodes[next].remove((Integer)node);
		}
		adj = null;
		nodes[node].clear();
	}

	/**
	 * removeEdge - removes an edge from the graph 
	 *
	 * If the edge is not directed the edge will be removed in both directions
	 *
	 * @param int start - the beginning of the edge
	 * @param int finish - the end of the edge
	 * @param boolean dir - is the edge directed
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
