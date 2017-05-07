import java.util.ArrayList;
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
	public static int bestCount, currentCount, numToRemove;
	public static DFS best;
	public static ArrayList<Integer> bestRemove;
	public static Graph graph, ograph, bestGraph;
	
	public static void main(String[] args){ 
		
		bestCount=Integer.MAX_VALUE;
		currentCount=Integer.MAX_VALUE;
		bestRemove= new ArrayList<Integer>();
		
		String[] line = scan.nextLine().split(" ");
		try{
			graph = new Graph(Integer.parseInt(line[0]), Integer.parseInt(line[1]), false);
			numToRemove = Integer.parseInt(line[2]);
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
			boolean found = false;
			int numToX = Integer.MAX_VALUE;
			int maxAttempts = 1000, attempts = 0;
			while(attempts <= maxAttempts){
				while((numToX > numToRemove) && attempts <= maxAttempts){
					//System.out.printf("attempt: %d/%d\n", attempts, maxAttempts);
					int i = 1;
					int size = 0;
					do{
						BFS bfs = new BFS(graph);
						numToX = bfs.getOutConnections(bfs.getXNeighbors(rand.nextInt(graph.nodeCount), i));
						//System.out.printf("Random start Nodes within: %d numToX: %d\n", i, numToX);
						size = bfs.getInNodeSize();
						if(numToX <= numToRemove){
							found = true;
							tryThese(bfs);
						}
						i++;
						numToX = Integer.MAX_VALUE;
						//System.out.printf("i: %d InSize: %d GraphSize: %d\n",i, size, graph.nodeCount);
					}while(size < graph.nodeCount && i < 30);
					attempts++;
					
				}
				if(!found){
					System.out.println("Algorithm failed");
					graph = ograph.copyGraph(ograph);
					//System.exit(0);
				}
			}
			attempts = 0;
			System.out.println("Trying Random");
			while(attempts <= maxAttempts){
				//System.out.printf("Random Remove attempt: %d/%d\n", attempts, maxAttempts);
				randomRemove();
				attempts++;
			}
			//System.out.println(best.printForest());
			
			System.out.println("Ending Connectivity: " + bestCount);
			System.out.println("Removed Nodes: " + bestRemove.size());
			for(Integer n : bestRemove){
				System.out.println(n.toString());
			}
		}catch(NumberFormatException nfe){
			System.out.println("Expected 3 integers seperated by a space on first line");
			System.exit(1);
		}catch(IllegalArgumentException iae){
			System.out.println("Graph constructor expects the number of nodes and edges");
			System.exit(1);
		}
		try{
			PrintWriter pw = new PrintWriter("cnp.out", "UTF-8");
			pw.println(bestCount);
			for(Integer n : bestRemove){
				pw.print(n + " ");
			}
			pw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void randomRemove(){
		for (int i = 0; i < numToRemove; i++){
			graph.removeRandom();
		}
		DFS current = new DFS(graph);
		try{
			currentCount = current.countPairWise();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(currentCount <= bestCount){
			System.out.println("Found Better!");
			bestGraph = graph;
			bestCount = currentCount;
			best = current;
			bestRemove = graph.removed;
		}
		graph = ograph.copyGraph(ograph);
	}
	private static void tryThese(BFS bfs){
		//System.out.printf("Removing: %d nodes\n", bfs.getToRemove().size());
		for(Integer n: bfs.getToRemove()){
			graph.removeNode((int)n);
		}
		// random remove up to limit
		while(graph.removed.size() < numToRemove){
			graph.removeRandom();
		}
		DFS current = new DFS(graph);
		try{
			currentCount = current.countPairWise();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(currentCount <= bestCount){
			System.out.println("Found better");
			bestGraph = graph;
			best = current;
			bestCount = currentCount;
			bestRemove = graph.removed;
		}
		graph = ograph.copyGraph(ograph);
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
