import java.util.Scanner;
import java.io.*;

/**
 * CritialNode.java
 *
 * A Java class to solve the critical node problem
 *
 * Author: Matthew Bowers
 * Date:
 *
 */


public class CritialNode{
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){ 
		Graph graph, ograph;
		DFS best;
		
		
		String[] line = scan.nextLine().split(" ");
		try{
			graph = new Graph(Integer.parseInt(line[0]), Integer.parseInt(line[1]), false);
			int numToRemove = Integer.parseInt(line[2]);
			buildGraph(graph, args);
			// copy the original graph for safe keeping
			ograph = graph.copyGraph(graph);
			//graph.getAdjacencyList().printList();
			DFS current = new DFS(graph);
			try{
				System.out.println(current.countPairWise());
			}catch(Exception e){e.printStackTrace();}
			System.out.println(current.printForest());
			for(int i = 0; i < numToRemove; i++){
				graph.removeRandom();
			}
			//graph.getAdjacencyList().printList();
			current = new DFS(graph);
			try{
				System.out.println(current.countPairWise());
			}catch(Exception e){e.printStackTrace();}
			System.out.println(current.printForest());
		}catch(NumberFormatException nfe){
			System.out.println("Expected 3 integers seperated by a space on first line");
			System.exit(1);
		}catch(IllegalArgumentException iae){
			System.out.println("Graph constructor expects the number of nodes and edges");
			System.exit(1);
		}
	}
	
	private static void buildGraph(Graph graph, String[] args){
		int lineNum = 1;
		while(scan.hasNextLine()){
			lineNum++;
			String[] line = scan.nextLine().split(" ");
			if(line.length != 2){
				System.out.printf("Expected 2 integers on line %d\n", lineNum);
				System.out.printf("Line %d has been skipped\n", lineNum);
				continue;
			}
			try{
				graph.addEdge(Integer.parseInt(line[0])-1, Integer.parseInt(line[1])-1);
			}catch(NumberFormatException nfe){
				System.out.printf("Error on line: %d! Skipping line", lineNum); 
				System.out.println(nfe.getMessage());
				continue;
			}catch(IllegalArgumentException iae){
				System.out.printf("Error on line: %d! Skipping line", lineNum); 
				System.out.println(iae.getMessage());
				continue;
			}
		}
	}
}	
