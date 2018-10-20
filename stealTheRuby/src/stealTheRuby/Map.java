package stealTheRuby;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.Vector;

public class Map {
	
	private int tilesX;
	private int tilesY;
	private int tileSizeX;
	private int tileSizeY;
	private Tile[][] geometry;
	private Item[][] items;
	private Trap[][] traps;
	private ArrayList<Guard> guards;
	private ArrayList<SecurityCamera> cameras;
	private DijkstraNode[][] graph;
	private float alertTimer;
	private float alertSeconds;
	private String mapName;
	private float frozen;
	
	private Vehicle getaway;
	
	public Map(int sx, int sy, int tsx, int tsy) {
		tilesX = sx;
		tilesY = sy;
		tileSizeX = tsx;
		tileSizeY = tsy;
		geometry = new Tile[sx][sy];
		items = new Item[sx][sy];
		traps = new Trap[sx][sy];
		guards = new ArrayList<Guard>();
		cameras = new ArrayList<SecurityCamera>();
		graph = new DijkstraNode[sx][sy];
		alertTimer = 0;
		alertSeconds = 10;
		frozen = 0;
		getaway = null;
		mapName = "DEFAULT";
		
		populateDijkstraGraph();
		
	}
	
	public void clearLevel() {
		geometry = new Tile[tilesX][tilesY];
		items = new Item[tilesX][tilesY];
		traps = new Trap[tilesX][tilesY];
		guards.clear();
		cameras.clear();
		alertTimer = 0;
		frozen = 0;
		getaway = null;
	}
	
	public void freeze(float seconds) {
		frozen = seconds*1000;
	}
	
	public float getFrozen() {
		return frozen;
	}
	
	public void setMapName(String s) {
		mapName = s;
	}
	
	public String getMapName() {
		return mapName;
	}
	
	public int getTilesX() {
		return tilesX;
	}
	
	public int getTilesY() {
		return tilesY;
	}
	
	public int getTileSizeX() {
		return tileSizeX;
	}
	
	public int getTileSizeY() {
		return tileSizeY;
	}
	
	public void setGemoetry(int x, int y, Tile g) {
		geometry[x][y] = g;
	}
	
	public void setItem(int x, int y, Item i) {
		items[x][y] = i;
	}
	
	public void setTrap(int x, int y, Trap i) {
		traps[x][y] = i;
	}
	
	public void alert(float seconds) {
		alertTimer = 1000*seconds;
	}
	
	public float getAlertTimer() {
		return alertTimer;
	}
	
	public ArrayList<SecurityCamera> getCameras(){
		return cameras;
	}
	
	public void prepareGetaway() {
		if(getaway!=null) {
			getaway.setSolid(false);
		}
	}
	
	public void setGetaway(Item newItem) {
		getaway = (Vehicle) newItem;
	}
	
	public void addGuard(Guard g) {
		guards.add(g);
	}
	
	public void addSecurityCamera(SecurityCamera c) {
		cameras.add(c);
	}
	
	public void testGuardFollowPath(float x, float y) {
		//TODO testing
		for(int i = 0;i<guards.size();i++) {
			ArrayList<Vector> newPath = dijkstraPath(guards.get(i).getX(),guards.get(i).getY(),x,y);
			guards.get(i).setFollowPath(newPath);
			guards.get(i).chase();
		}
	}
	
	public void chasePlayer(Guard guard, Player player) {
		ArrayList<Vector> newPath = dijkstraPath(guard.getX(),guard.getY(),player.getX(),player.getY());
		guard.setFollowPath(newPath);
		guard.chase();
	}
	
	public void alertPosition(Entity e) {
		for(int i = 0;i<guards.size();i++) {
			Guard guard = guards.get(i);
			if(guard.getState()!=1 && guard.getState()!=3) {
				ArrayList<Vector> newPath = dijkstraPath(guard.getX(),guard.getY(),e.getX(),e.getY());
				guard.setFollowPath(newPath);
				guard.investigate();
			}
		}
	}
	
	public int collideWithGuards(Player p) {
		int ret = 0;
		for(int i = 0;i<guards.size();i++) {
			Guard curGuard = guards.get(i);
			if(curGuard.getFrozen()<=0) {
				if(p.collides(curGuard)!=null) {
					if(p.distanceTo(curGuard)<=20) {
						return 2;
					}
				}
				if(curGuard.collideWithVisionCone(p)) {
					chasePlayer(curGuard,p);
					alert(alertSeconds);
					ret = 1;
				}
			}
		}
		return ret;
	}
	
	public int collideWithCameras(Player p) {
		int ret = 0;
		for(int i = 0;i<cameras.size();i++) {
			SecurityCamera curCamera = cameras.get(i);
			if(curCamera.getFrozen()<=0) {
				if(curCamera.collideWithVisionCone(p)) {
					alertPosition(p);
					alert(alertSeconds);
					ret = 1;
				}
			}
		}
		return ret;
	}
	
	public void sendGuardsBackToPatrol() {
		for(int i = 0;i<guards.size();i++) {
			Guard curGuard = guards.get(i);
			ArrayList<Vector> path = curGuard.getPatrolPath();
			if(!path.isEmpty()) {
				int point = curGuard.getPatrolPoint();
				ArrayList<Vector> newPath = dijkstraPath(curGuard.getX(),curGuard.getY(),path.get(point).getX(),path.get(point).getY());
				curGuard.setFollowPath(newPath);
			}
			curGuard.returnToPatrolPath();
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
	
	
	public void removeItem(int tileX, int tileY) {
		if(tileX<0 || tileX > tilesX-1) {
			return;
		}
		if(tileY<0 || tileY > tilesY-1) {
			return;
		}
		items[tileX][tileY] = null;
	}
	
	public void removeTrap(int tileX, int tileY) {
		if(tileX<0 || tileX > tilesX-1) {
			return;
		}
		if(tileY<0 || tileY > tilesY-1) {
			return;
		}
		traps[tileX][tileY] = null;
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
	
	public Trap getTrapAtPoint(int tileX, int tileY) {
		
		if(tileX<0 || tileX > tilesX-1) {
			return null;
		}
		if(tileY<0 || tileY > tilesY-1) {
			return null;
		}
		return traps[tileX][tileY];
	}
	
	public void updateGuards(int delta, StateBasedGame game) {
		MainGame mg = (MainGame)game;
		
		if(frozen>0) {
			frozen-=delta;
		}else {
			if(alertTimer>0) {
				alertTimer -= delta;
				if(alertTimer==0) {
					alertTimer=-1;
				}
			}else if(alertTimer<0) {
				alertTimer = 0;
				sendGuardsBackToPatrol();
			}
			
			for(int i = 0;i<guards.size();i++) {
				Guard curGuard = guards.get(i);
				curGuard.update(delta);
				if(curGuard.getState()==3) {
					ArrayList<Vector> newPath = dijkstraPath(curGuard.getX(),curGuard.getY(),mg.player.getX(),mg.player.getY());
					curGuard.setFollowPath(newPath);
					curGuard.chase();
				}
				
				if(curGuard.getState()==4) {
					ArrayList<Vector> path = curGuard.getPatrolPath();
					if(!path.isEmpty()) {
						int point = curGuard.getPatrolPoint();
						ArrayList<Vector> newPath = dijkstraPath(curGuard.getX(),curGuard.getY(),path.get(point).getX(),path.get(point).getY());
						curGuard.setFollowPath(newPath);
					}
					curGuard.returnToPatrolPath();
				}
				
				Vector gpos = getGridPos(curGuard.getX(),curGuard.getY());
				Trap gridTrap = getTrapAtPoint((int)gpos.getX(),(int)gpos.getY());
				if(gridTrap!=null && gridTrap.getPlayerOwned() && curGuard.collides(gridTrap)!=null) {
					gridTrap.springTrap(game, curGuard);
				}
				
			}

			for(int i = 0;i<cameras.size();i++) {
				SecurityCamera curCamera = cameras.get(i);
				curCamera.update(delta);
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		MainGame mg = (MainGame)game;
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
				if(geometry[x][y]!=null) {
					geometry[x][y].render(g);
				}
				if(items[x][y]!=null) {
					items[x][y].render(g);
				}
				if(traps[x][y]!=null) {
					traps[x][y].render(g);
				}
			}
		}
		for(int i = 0;i<guards.size();i++) {
			Guard curGuard = guards.get(i);
			curGuard.render(g);
			if(curGuard.getFrozen()<=0) {
				curGuard.renderCone(g);
			}
			//TODO DEBUG MODE
			if(mg.DEBUG) {
				curGuard.renderPath(g);
			}
		}
		for(int i = 0;i<cameras.size();i++) {
			SecurityCamera curCamera = cameras.get(i);
			if(curCamera.getFrozen()<=0) {
				curCamera.renderCone(g);
			}
			curCamera.render(g);
		}
		
	}
	
}
