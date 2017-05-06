import java.util.Random;
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
	public static Random rand = new Random();
	
	public static void main(String[] args){ 
		Graph graph, ograph;
		DFS best;
		int bestCount=Integer.MAX_VALUE, currentCount=Integer.MAX_VALUE;
		
		String[] line = scan.nextLine().split(" ");
		try{
			graph = new Graph(Integer.parseInt(line[0]), Integer.parseInt(line[1]), false);
			int numToRemove = Integer.parseInt(line[2]);
			buildGraph(graph, args);
			// copy the original graph for safe keeping
			ograph = graph.copyGraph(graph);
			//graph.getAdjacencyList().printList();
			best = new DFS(graph);
			try{
				bestCount = best.countPairWise();
				System.out.printf("Starting Connectivity: %d\n", bestCount);
			}catch(Exception e){e.printStackTrace();}
			//System.out.println(current.printForest());
			
			// try searching for a subgraph which can be disconnected
			BFS bfs = new BFS(graph);
			int numToX = Integer.MAX_VALUE;
			int maxAttempts = 10, attempts = 0;
			while(attempts <= maxAttempts){
				while((numToX > numToRemove) && attempts <= maxAttempts){
					int i = 1;
					while(numToX > numToRemove && i < 5){
						numToX = bfs.getOutConnections(bfs.getXNeighbors(rand.nextInt(graph.nodeCount), i));
						System.out.printf("Random start Nodes within: %d numToX: %d\n", i, numToX);
						i++;
					}
					numToX = Integer.MAX_VALUE;
					System.out.printf("attempt: %d/%d\n", attempts++, maxAttempts);
				}
				if(bfs.getToRemove().size() > numToRemove){
					System.out.println("Algorithm failed");
					System.exit(0);
				}
				System.out.printf("Removing: %d nodes\n", bfs.getToRemove().size());
				for(Integer n: bfs.getToRemove()){
					graph.removeNode((int)n);
				}
				
				//for(i = 0; i < numToRemove; i++){
				//	graph.removeMax();
				//}
				//graph.getAdjacencyList().printList();
				DFS current = new DFS(graph);
				try{
					currentCount = current.countPairWise();
				}catch(Exception e){
					e.printStackTrace();
				}
				if(currentCount < bestCount){
					best = current;
				}
			}
			// System.out.println(best.printForest());
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
