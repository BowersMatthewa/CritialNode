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
	
	private AdjacencyList[] nodes;

	private int size;

	public AdjacencyList(int n){
		nodes = new ArrayList[n];
		size = n;
		for(int i = 0; i <n; i++){
			nodes[i] = new ArrayList<Integer>();
		}
	}

	public void addEdge(int start, int finish){
		nodes[start].add(finish);
		nodes[finish].add(start);
	}

	public void printList(){
		for(ArrayList list : nodes){
			for(Integer node : list){
				System.out.print(node + "  ");
			}
			System.out.println(); 
		}
	}

	public void removeEdge(int start, int finish){
		nodes[start].remove(finish);
		nodes[finish].remove(start);
	}
}
