import java.util.ArrayList;

/* package com.git.bowersmatthewa.criticalnode
/**
 * 
 * Node.java 
 * A Java class to represent a node on a graph
 *
 * Author: Matthew Bowers
 * Date: 4.8.17
 */

public class Node {
	ArrayList<Node> neighbors;
	int name;
	boolean valid;

	public Node(int name, ArrayList<Node> edges) throws IllegalArgumentException {
		if(name < 0){
			throw new IllegalArgumentException("Node name may not be empty");
		}
		else{
			this.neighbors = edges;
			this.name = name;
			this.valid = true;
		}
	}

	/**
	 * addNeighbor - adds a node to the list of neighbors
	 *
	 * @param newNeighbor - the node to add as a neighbor
	 * @throws IllegalArgumenException - if attempting to add a neighbor which already exists
	 */
	public void addNeighbor(Node newNeighbor) throws IllegalArgumentException { 
			if(neighbors.contains(newNeighbor)){
				throw new IllegalArgumentException("Edge already exists");
			}
			neighbors.add(newNeighbor);
	}

	/**
	 * getNeighbors - returns the list of neighbors
	 */
	public ArrayList<Node> getNeighbors(){
		return neighbors;
	}


	public int getName(){
		return name;
	}


	public String toString(){
		StringBuilder sb = new StringBuilder();
		 
		sb.append("Node: " + name + "\n" + "Edges");
		for(Node n : neighbors){
			sb.append(n.getName() + " ");
		}

		return (sb.toString());
	}

	/**
	 * isValid - checks if the node is currently a part of the graph
	 */
	public boolean isValid(){
		return valid;
	}

	/**
	 * setValid - sets the valid bit of the node
	 *
	 * @param newVal boolean representing if the node is valid or not
	 */
	public void setValid(boolean newVal){
		valid = newVal;
	}
}

