package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;
import jig.Vector;

public class Levels {

	public static final String GRASSIMG_RSC = "stealTheRuby/resource/grassTile.png";
	public static final String STEELIMG_RSC = "stealTheRuby/resource/steelTile.png";
	public static final String BRICKIMG_RSC = "stealTheRuby/resource/brickTile.png";
	public static final String METALIMG_RSC = "stealTheRuby/resource/metalTile.png";
	public static final String WOODIMG_RSC = "stealTheRuby/resource/woodTile.png";
	
	public static void loadTextures() {
		
		ResourceManager.loadImage(GRASSIMG_RSC);
		ResourceManager.loadImage(STEELIMG_RSC);
		ResourceManager.loadImage(BRICKIMG_RSC);
		ResourceManager.loadImage(METALIMG_RSC);
		ResourceManager.loadImage(WOODIMG_RSC);
		
	}
	
	final public static int lastLevel = 2;
	
	public static void loadLevel(int level, StateBasedGame game) {
		if(level==1) {
			level1(game);
		}else if(level==2) {
			level2(game);
		}
	}
	
	private static Tile tileDepot(float x, float y, int tileSizeX, int tileSizeY, int type) {
		if(type==0) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,STEELIMG_RSC);
		}else if(type==1) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,GRASSIMG_RSC);
		}else if(type==2) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,BRICKIMG_RSC);
		}else if(type==3) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,METALIMG_RSC);
		}else if(type==4) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,WOODIMG_RSC);
		}
		
		return null;
	}
	
	private static Item itemDepot(float x, float y, int type) {
			if(type == -1) {
				return new Vehicle(x, y, true);
			}else if(type==1) {
				return new Coin(x, y);
			}else if(type==2) {
				return new Ruby(x, y);
			}else if(type==3) {
				return new Key(x, y,
						new Color(0,0,255));
			}else if(type==4) {
				return new Lock(x, y,
						new Color(0,0,255));
			}else if(type==5) {
				return new Key(x, y,
						new Color(0,255,0));
			}else if(type==6) {
				return new Lock(x, y,
						new Color(0,255,0));
			}else if(type==7) {
				return new Key(x, y,
						new Color(255,0,0));
			}else if(type==8) {
				return new Lock(x, y,
						new Color(255,0,0));
			}
		 	
		 	return null;
	}
	
	private static Trap trapDepot(float x, float y, int type){
		if(type==1) {
			return new fruitPeel(x, y, false);
		}
		return null;
	}
	
	private static void setLevel(StateBasedGame game, String levelName, int[][] tlevel, 
			 int[][] ilevel, int[][] traplevel, ArrayList<Guard> guards) {
		
		MainGame mg = (MainGame)game;
		mg.map.clearLevel();
		mg.map.setMapName(levelName);
		int tilesX = mg.map.getTilesX();
		int tilesY = mg.map.getTilesY();
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		for(int x = 0;x<tilesX;x++) {
			for(int y = 0;y<tilesY;y++) {
				//generate tiles
				int curTile = tlevel[y][x];
				if(curTile>=0) {
					Tile newTile = tileDepot(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2, tileSizeX,tileSizeY, curTile);
					mg.map.setGemoetry(x,y,newTile);
				}
				
				//generate items
				int curItem = ilevel[y][x];
				if(curItem>0) {
					Item newItem = itemDepot(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2, curItem);
					mg.map.setItem(x,y,newItem);
				}else if(curItem==-1) {
					Item newItem = itemDepot(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2, curItem);
					mg.map.setItem(x,y,newItem);
					mg.map.setGetaway(newItem);
					mg.player.setPosition(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2);
				}
				
				//generate traps
				int curTrap = traplevel[y][x];
				if(curTrap>0) {
					Trap newTrap = trapDepot(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2, curTrap);
					mg.map.setTrap(x,y,newTrap);
				}
			}
		}
		
		//generate guards
		for(int i = 0;i<guards.size();i++) {
			mg.map.addGuard(guards.get(i));
		}
	}
	
	private static void level1(StateBasedGame game) {
		String levelName = "TEST LEVEL 1";
			
			MainGame mg = (MainGame)game;
			int tileSizeX = mg.map.getTileSizeX();
			int tileSizeY = mg.map.getTileSizeY();
			
			
			int[][] tlevel = 
				   {{1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,2,2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,2,2,2,1,1,1},
					{1,1,1,2,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,1,1,1},
					{1,1,1,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,1,1,1},
					{1,1,1,2,4,2,2,4,4,4,4,2,3,3,3,2,2,4,4,2,4,2,1,1,1},
					{1,1,1,2,4,4,4,4,2,2,4,4,3,3,3,4,4,4,4,4,4,2,1,1,1},
					{1,1,1,2,4,2,2,4,4,4,4,2,3,3,3,2,2,4,4,2,4,2,1,1,1},
					{1,1,1,2,4,4,2,4,2,2,4,2,2,2,2,2,2,2,2,2,4,2,1,1,1},
					{1,1,1,2,4,4,2,4,4,4,4,4,4,4,4,4,4,4,2,4,4,2,1,1,1},
					{1,1,1,2,4,2,2,2,2,2,2,2,4,4,2,4,2,4,2,2,4,2,1,1,1},
					{1,1,1,2,4,4,4,4,4,4,4,4,4,4,2,4,2,4,4,4,4,2,1,1,1},
					{1,1,1,2,4,2,4,4,2,2,2,2,2,2,2,4,4,4,2,2,4,2,1,1,1},
					{1,1,1,2,4,2,2,2,3,3,3,2,3,3,3,2,2,2,2,2,4,2,1,1,1},
					{1,1,1,2,4,3,3,3,3,3,3,2,3,3,3,3,3,3,3,3,4,2,1,1,1},
					{1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
			
			//-1 = getaway vehicle
			
			int[][] ilevel = 
				   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,1,0,5,0,1,1,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,4,6,8,0,2,0,0,0,3,0,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
			
			int[][] traplevel = 
				   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
			
			ArrayList<Guard> guards = new ArrayList<Guard>();
			
			Guard g1 = new Guard(7*tileSizeX + tileSizeX/2,4*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
			
			ArrayList<Vector> p1 = new ArrayList<Vector>();
			p1.add(new Vector(7*tileSizeX + tileSizeX/2,4*tileSizeY + tileSizeY/2));
			p1.add(new Vector(7*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2));
			p1.add(new Vector(10*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2));
			p1.add(new Vector(10*tileSizeX + tileSizeX/2,4*tileSizeY + tileSizeY/2));
			
			g1.setPatrolPath(p1);
			guards.add(g1);
			
			Guard g2 = new Guard(20*tileSizeX + tileSizeX/2,2*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
			
			ArrayList<Vector> p2 = new ArrayList<Vector>();
			p2.add(new Vector(20*tileSizeX + tileSizeX/2,2*tileSizeY + tileSizeY/2));
			p2.add(new Vector(20*tileSizeX + tileSizeX/2,13*tileSizeY + tileSizeY/2));
			
			g2.setPatrolPath(p2);
			guards.add(g2);
			
			setLevel(game, levelName, tlevel, ilevel, traplevel, guards);
		}
		
	
	private static void level2(StateBasedGame game) {
		
		String levelName = "TESTING LEVEL 2";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,1,1,1,1,0,0,0,1,0,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,5,0,0,0,0,0,-1,0,0,0,0,0,0},
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
		
		int[][] traplevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(3*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(8*tileSizeX + tileSizeX/2,7*tileSizeY + tileSizeY/2));
		p1.add(new Vector(8*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		p1.add(new Vector(10*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		p1.add(new Vector(10*tileSizeX + tileSizeX/2,7*tileSizeY + tileSizeY/2));
		p1.add(new Vector(15*tileSizeX + tileSizeX/2,7*tileSizeY + tileSizeY/2));
		p1.add(new Vector(15*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(9*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(9*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2));
		p1.add(new Vector(8*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, guards);
	}
}