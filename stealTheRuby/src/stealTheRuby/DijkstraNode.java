package stealTheRuby;

import java.util.ArrayList;

import jig.Entity;

public class DijkstraNode extends Entity implements Comparable<DijkstraNode>{

	public float cost;
	public DijkstraNode predecessor;
	public ArrayList<DijkstraNode> neighbors;
	public boolean visited;
	public boolean passable;
	
	public DijkstraNode(float x, float y) {
		super(x,y);
		cost = (float)Double.POSITIVE_INFINITY;
		predecessor = null;
		visited = false;
		passable = true;
		
	}
	
	public static void initializeGraph(DijkstraNode[][] graph, int sizeX, int sizeY) {
		for(int x = 0;x<sizeX;x++) {
			for(int y = 0;y<sizeY;y++) {
				graph[x][y].cost = (float)Double.POSITIVE_INFINITY;
				graph[x][y].predecessor = null;
				graph[x][y].visited = false;
			}
		}
	}

	@Override
	public int compareTo(DijkstraNode o) {
		if(cost<o.cost) {
			return 1;
		}else if(cost>o.cost) {
			return -1;
		}else {
			return 0;
		}
	}
	
}
