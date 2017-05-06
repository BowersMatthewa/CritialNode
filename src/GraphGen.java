import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GraphGen {

	public static void main(String[] args) {
		Random rand = new Random();
		int node = rand.nextInt(30)+600;
		int edges = rand.nextInt(300)+7000;
		int remove = rand.nextInt(10)+100;
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		
		System.out.println(node + " " + edges + " " + remove);
		for(int i = 0; i < edges; i++){
			int start, finish;
			Edge newEdge;
			
			
			
			start = rand.nextInt(node) +1;
			finish = rand.nextInt(node)+1;
			newEdge = new Edge(start, finish);
			if(start != finish)
				if(!edgeList.contains(newEdge)){
					edgeList.add(newEdge);
				}
				else{
				//System.out.println("Edge Aleady Exists");
					i--;
				}
			else{
				i--;
			}
		
				
			
			//do{
				//start = rand.nextInt(node)+1;
				//finish = rand.nextInt(node)+1;
				//newEdge = new Edge(start, finish);
			//}while(start == finish);
			//if(!edgeList.contains(newEdge))
			//	edgeList.add(newEdge);
		}
		Collections.sort(edgeList);
		for(Edge e : edgeList){
			System.out.println(e);
		}
	}
	
	private static class Edge implements Comparable<Edge>{
		int start;
		int finish;
		
		public Edge(int start, int finish){
			this.start = (start<finish) ? start : finish;
			this.finish = (start<finish) ? finish : start;
		}
		public int compareTo(Edge other) {
			if((start == other.start && finish == other.finish))
				return 0;
			else if(start < other.start)
				return -1;
			else if(start > other.start)
				return 1;
			else if(start == other.start && finish < other.finish)
				return -1;
			else 
				return 1;
		}
		
		public boolean equals(Object other){
			if(this.compareTo((Edge)other) == 0)
				return true;
			else
				return false;
		}
		
		public String toString(){
			return Integer.toString(start) + " " + Integer.toString(finish);
		}
		
		
	}

}
