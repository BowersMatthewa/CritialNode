package com.git.bowersmatthewa.criticalnode
/**
 * 
 * Node.java 
 * A Java class to represent a node on a graph
 *
 * Author: Matthew Bowers
 * Date: 4.8.17
 */

public class Node {
	ArrayList<Node> =  edges;
	int name;

	public Node Node(int name, ArrayList<Node> edges) throws IllegalArgumentException {
		if(name.equals(""){
			throw new IllegalArgumentException("Node name may not be empty");
		}
		else{
			this.edges = edges;
			this.name = name;
		}
	}

	public void addNeighbor(Node newNeighbor) throws IllegalArgumentException { 
			edges.add(newNeighbor);
	}
	public String getName(){
		return name;
	}

	public ArrayList<Edge> getEdges(){
		return edges;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		 
		sb.append("Node: " + name + "\n" + "Edges");
		for(Node n : edges){
			sb.append(n.getName() + " ");
		}

		return (sb.toString());
	}
}

