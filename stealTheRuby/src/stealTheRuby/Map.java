package stealTheRuby;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;
import jig.Vector;

public class Map {

	public static final String GRASSIMG_RSC = "stealTheRuby/resource/grassTile.png";
	public static final String STEELIMG_RSC = "stealTheRuby/resource/steelTile.png";
	
	public void loadTextures() {
		
		ResourceManager.loadImage(GRASSIMG_RSC);
		ResourceManager.loadImage(STEELIMG_RSC);
		
	}
	
	private int tilesX;
	private int tilesY;
	private int tileSizeX;
	private int tileSizeY;
	private Tile[][] geometry;
	private Item[][] items;
	private ArrayList<Guard> guards;
	private DijkstraNode[][] graph;
	
	public Map(int sx, int sy, int tsx, int tsy) {
		tilesX = sx;
		tilesY = sy;
		tileSizeX = tsx;
		tileSizeY = tsy;
		geometry = new Tile[sx][sy];
		items = new Item[sx][sy];
		guards = new ArrayList<Guard>();
		graph = new DijkstraNode[sx][sy];
		
		loadTextures();
		testLevel();
		populateDijkstraGraph();
		
	}
	
	public void testGuardFollowPath(float x, float y) {
		//TODO testing
		for(int i = 0;i<guards.size();i++) {
			ArrayList<Vector> newPath = dijkstraPath(guards.get(i).getX(),guards.get(i).getY(),x,y);
			guards.get(i).setFollowPath(newPath);
			guards.get(i).alert();
		}
	}
	
	public ArrayList<Vector> dijkstraPath(float startX, float startY, float endX, float endY){
		ArrayList<Vector> shortestPath = new ArrayList<Vector>();
		//initialize graph
		DijkstraNode.initializeGraph(graph, tilesX, tilesY);
		updateDijkstraGraph();
		//get start node
		Vector gridPos = getGridPos(startX, startY); 
		DijkstraNode startNode = graph[(int)gridPos.getX()][(int)gridPos.getY()];
		startNode.cost = 0;
		//create queue
		PriorityQueue<DijkstraNode> nodes = new PriorityQueue<DijkstraNode>();
		//add the start node
		nodes.add(startNode);
		//while unexplored nodes
		while(!nodes.isEmpty()) {
			DijkstraNode curNode = nodes.poll();
			curNode.visited = true;
			//for each neighbor
			
			for(int i = 0;i<curNode.neighbors.size();i++) {
				DijkstraNode next = curNode.neighbors.get(i);
				//if you can get to it
				if(next.passable) {
					
					if(next.cost>curNode.cost+1) {
						next.cost = curNode.cost+1;
						next.predecessor = curNode;
						nodes.add(next);
					}
				}
			}
		}
		
		//get end node
		Vector endGridPos = getGridPos(endX, endY); 
		DijkstraNode endNode = graph[(int)endGridPos.getX()][(int)endGridPos.getY()];
		
		DijkstraNode prev = endNode;
		
		ArrayList<DijkstraNode> backPath = new ArrayList<DijkstraNode>();
		//create a backwards list of the shortest path
		while(prev!=null) {
			backPath.add(prev);
			prev = prev.predecessor;
		}
		
		//reverse the backwards list
		for(int i = backPath.size()-1;i>=0;i--) {
			shortestPath.add(backPath.get(i).getPosition());
		}
		
		return shortestPath;
	}
	
	public void populateDijkstraGraph() {
		//generate nodes
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
					graph[x][y] = new DijkstraNode(x*tileSizeX + tileSizeX/2,y*tileSizeY + tileSizeY/2);
			}
		}
		//connect to neighbors
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
					ArrayList<DijkstraNode> neighbors = new ArrayList<DijkstraNode>();
					if(x>0) {
						neighbors.add(graph[x-1][y]);
					}
					if(x<tilesX-1) {
						neighbors.add(graph[x+1][y]);
					}
					if(y>0) {
						neighbors.add(graph[x][y-1]);
					}
					if(y<tilesY-1) {
						neighbors.add(graph[x][y+1]);
					}
					graph[x][y].neighbors = neighbors;
			}
		}
	}
	
	public void updateDijkstraGraph() {
		//sweep the map and update passable nodes
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
				if(geometry[x][y].getSolid() || (items[x][y]!=null && items[x][y].getSolid())) {
					graph[x][y].passable = false;
				}else {
					graph[x][y].passable = true;
				}
			}
		}
	}
	
	public void testLevel() {
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,1,1,1,1,0,0,0,1,0,1,1,1,1,1,1,1,1,1}};
		
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
					if(tlevel[y][x]==0) {
						geometry[x][y] = new Tile(x*tileSizeX + tileSizeX/2,
								y*tileSizeY + tileSizeY/2,
								tileSizeX, tileSizeY,
								true ,STEELIMG_RSC);
					}else if(tlevel[y][x]==1) {
						geometry[x][y] = new Tile(x*tileSizeX + tileSizeX/2,
								y*tileSizeY + tileSizeY/2,
								tileSizeX, tileSizeY,
								false ,GRASSIMG_RSC);
					}
			}
		}
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,6,2,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
					if(ilevel[y][x]==0) {

					}else if(ilevel[y][x]==1) {
						items[x][y] = new Coin(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2);
					}else if(ilevel[y][x]==2) {
						items[x][y] = new Ruby(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2);
					}else if(ilevel[y][x]==3) {
						items[x][y] = new Key(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2,
								new Color(0,0,255));
					}else if(ilevel[y][x]==4) {
						items[x][y] = new Lock(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2,
								new Color(0,0,255));
					}else if(ilevel[y][x]==5) {
						items[x][y] = new Key(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2,
								new Color(0,255,0));
					}else if(ilevel[y][x]==6) {
						items[x][y] = new Lock(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2,
								new Color(0,255,0));
					}else if(ilevel[y][x]==7) {
						items[x][y] = new Key(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2,
								new Color(255,0,0));
					}else if(ilevel[y][x]==8) {
						items[x][y] = new Lock(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2,
								new Color(255,0,0));
					}
			}
		}
		
		int[][] plevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,9,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,1,0,4,0,0,0,0,5,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,2,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		int maxPathSize = tilesX*tilesY;
		Vector[] testPath = new Vector[maxPathSize];
		
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
					if(plevel[y][x]>0) {
						testPath[plevel[y][x]-1]= new Vector(x*tileSizeX + tileSizeX/2,y*tileSizeY + tileSizeY/2);
					}
			}
		}
		
		ArrayList<Vector> newPath = new ArrayList<Vector>();
		
		for(int i = 0;i<maxPathSize;i++) {
			if(testPath[i]!= null) {
				newPath.add(testPath[i]);
			}
		}
		
		
		int[][] glevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
					if(glevel[y][x]==1) {
						
						Guard newGuard = new Guard(x*tileSizeX + tileSizeX/2,y*tileSizeY + tileSizeY/2,
								tileSizeX, tileSizeY);
						newGuard.setPatrolPath(newPath);
						guards.add(newGuard);
					}
			}
		}
	}
	
	public void removeItem(int tileX, int tileY) {
		if(tileX<0 || tileX > tilesX-1) {
			return;
		}
		if(tileY<0 || tileY > tilesY-1) {
			return;
		}
		items[tileX][tileY] = null;
	}
	
	public Vector getGridPos(float x, float y) {
		int gridX = (int) Math.floor(x/(tileSizeX));
		int gridY = (int) Math.floor(y/(tileSizeY));
		return new Vector(gridX, gridY);
	}
	
	public Tile getPoint(int tileX, int tileY) {
		
		if(tileX<0 || tileX > tilesX-1) {
			return null;
		}
		if(tileY<0 || tileY > tilesY-1) {
			return null;
		}
		return geometry[tileX][tileY];
	}
	
	public Item getItemAtPoint(int tileX, int tileY) {
		
		if(tileX<0 || tileX > tilesX-1) {
			return null;
		}
		if(tileY<0 || tileY > tilesY-1) {
			return null;
		}
		return items[tileX][tileY];
	}
	
	public void updateGuards(int delta) {
		for(int i = 0;i<guards.size();i++) {
			guards.get(i).update(delta);
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
				if(geometry[x][y]!=null) {
					geometry[x][y].render(g);
				}
				if(items[x][y]!=null) {
					items[x][y].render(g);
				}
			}
		}
		for(int i = 0;i<guards.size();i++) {
			guards.get(i).render(g);
			guards.get(i).renderPath(g);
		}
		
	}
	
}
