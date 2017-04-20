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
	
	private ArrayList[] nodes;

	private int size;


	/**
	 * Constructor
	 *
	 * instantiates an array of v ArrayLists of length v-1. At most any node will have v-1 adjacencies.
	 *
	 * @params n - The number of nodes in the graph
	 */
	public AdjacencyList(int v){
		nodes = new ArrayList[v];
		size = v;
		for(int i = 0; i <v; i++){
			nodes[i] = new ArrayList<Integer>(v-1);
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
		if(start >= size || start / < 0){
			throw new IllegalArgumentException("no such node")l
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
		for(ArrayList list : nodes){
			for(Integer node : list){
				System.out.print(node + "  ");
			}
			System.out.println(); 
		}
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
	public void removeEdge(int start, int finish, boolean dir){
		nodes[start].remove(finish);
		if(!dir){
			nodes[finish].remove(start);
		}
	}

}
