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
	
	public static void main(String[] args){ 
		Graph graph;
		Scanner scan = new Scanner(System.in);
		
		String[] line = scan.nextLine().split(" ");
		try{
			graph = new Graph(Integer.parseInt(line[0]), Integer.parseInt(line[1]), 0);
			int numToRemove = Integer.parseInt(line[2]);
		}catch(NumberFormatException nfe){
			System.out.println("Expected 3 integers seperated by a space on first line");
			Exit(1);
		}catch(IllegalArgumentException iae{
			System.out.println("Graph constructor expects the number of nodes and edges");
			Exit(1);
		}	
		
		int lineNum = 1;
		while(scan.hasNextLine()){
			lineNum++;
			line = scan.nextLine().split(" ");
			if(line.length != 2){
				System.out.printf("Expected 2 integers on line %d\n", lineNum);
				System.out.println("Line %d has been skipped\n", lineNum);
				continue;
			}
			try{
				graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
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
