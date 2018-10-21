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
	public static final String SIXTIESIMG_RSC = "stealTheRuby/resource/60sCarpetTile.png";
	public static final String BLUEIMG_RSC = "stealTheRuby/resource/blueCarpetTile.png";
	public static final String CARPETIMG_RSC = "stealTheRuby/resource/carpetTile.png";
	public static final String CHESSIMG_RSC = "stealTheRuby/resource/chessTile.png";
	public static final String CONCRETEIMG_RSC = "stealTheRuby/resource/concreteTile.png";
	public static final String CRATEIMG_RSC = "stealTheRuby/resource/crateTile.png";
	public static final String DARKWOODIMG_RSC = "stealTheRuby/resource/mahoganyTile.png";
	public static final String PURPLEIMG_RSC = "stealTheRuby/resource/purpleTile.png";
	public static final String SOLIDIMG_RSC = "stealTheRuby/resource/solidTile.png";
	public static final String WOODWALLIMG_RSC = "stealTheRuby/resource/woodenWallTile.png";
	public static final String ROCKWALLIMG_RSC = "stealTheRuby/resource/rockTile.png";
	
	public static void loadTextures() {
		
		ResourceManager.loadImage(GRASSIMG_RSC);
		ResourceManager.loadImage(STEELIMG_RSC);
		ResourceManager.loadImage(BRICKIMG_RSC);
		ResourceManager.loadImage(METALIMG_RSC);
		ResourceManager.loadImage(WOODIMG_RSC);
		ResourceManager.loadImage(SIXTIESIMG_RSC);
		ResourceManager.loadImage(BLUEIMG_RSC);
		ResourceManager.loadImage(CARPETIMG_RSC);
		ResourceManager.loadImage(CHESSIMG_RSC);
		ResourceManager.loadImage(CONCRETEIMG_RSC);
		ResourceManager.loadImage(CRATEIMG_RSC);
		ResourceManager.loadImage(DARKWOODIMG_RSC);
		ResourceManager.loadImage(PURPLEIMG_RSC);
		ResourceManager.loadImage(SOLIDIMG_RSC);
		ResourceManager.loadImage(WOODWALLIMG_RSC);
		ResourceManager.loadImage(ROCKWALLIMG_RSC);
		
	}
	
	final public static int lastLevel = 9;//TODO update this
	final public static int totalCoins = 100;//TODO update this
	
	public static void loadLevel(int level, StateBasedGame game) {
		if(level==1) {
			levelTest(game);
		}else if(level==2) {
			level2(game);
		}else if(level==3) {
			level3(game);
		}else if(level==4) {
			level4(game);
		}else if(level==5) {
			level5(game);
		}else if(level==6) {
			level6(game);
		}else if(level==7) {
			level7(game);
		}else if(level==8) {
			level8(game);
		}else if(level==9) {
			level9(game);
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
		}else if(type==5) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,SIXTIESIMG_RSC);
		}else if(type==6) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,BLUEIMG_RSC);
		}else if(type==7) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,CARPETIMG_RSC);
		}else if(type==8) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,CHESSIMG_RSC);
		}else if(type==9) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,CONCRETEIMG_RSC);
		}else if(type==10) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,CRATEIMG_RSC);
		}else if(type==11) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,DARKWOODIMG_RSC);
		}else if(type==12) {
			return new Tile(x,y,tileSizeX, tileSizeY,false ,PURPLEIMG_RSC);
		}else if(type==13) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,SOLIDIMG_RSC);
		}else if(type==14) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,WOODWALLIMG_RSC);
		}else if(type==15) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,GRASSIMG_RSC);
			//solid grass
		}else if(type==16) {
			return new Tile(x,y,tileSizeX, tileSizeY,true ,ROCKWALLIMG_RSC);
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
			}else if(type==9) {
				return new fruit(x,y,false);
			}else if(type==10) {
				return new Stopwatch(x,y,false);
			}else if(type==11) {
				return new electronicsJammer(x,y,false);
			}else if(type==12) {
				return new Key(x, y,
						new Color(255,200,0));
			}else if(type==13) {
				return new Lock(x, y,
						new Color(255,200,0));
			}
		 	
		 	return null;
	}
	
	private static Trap trapDepot(float x, float y, int type){
		if(type==1) {
			return new fruitPeel(x, y, false);
		}
		return null;
	}
	
	private static SecurityCamera cameraDepot(float x, float y, int type, int tileSizeX, int tileSizeY) {
		if(type==1) {
			return new SecurityCamera(x-(tileSizeX/2), y+(tileSizeY/2),8,14,0.05f,180,270,16);
		}else if(type==2) {
			return new SecurityCamera(x+(tileSizeX/2), y-(tileSizeY/2),8,14,0.05f,0,90,16);
		}else if(type==3) {
			return new SecurityCamera(x+(tileSizeX/2), y+(tileSizeY/2),8,14,0.05f,90,180,16);
		}else if(type==4) {
			return new SecurityCamera(x-(tileSizeX/2), y-(tileSizeY/2),8,14,0.05f,270,360,16);
		}else if(type==5) {
			return new SecurityCamera(x, y,8,14,0.05f,0,180,16);
		}else if(type==6) {
			return new SecurityCamera(x, y,8,14,0.05f,270,90,16);
		}else if(type==7) {
			return new SecurityCamera(x, y,8,14,0.05f,0,360,16);
		}else if(type==8) {
			return new SecurityCamera(x+(tileSizeX/2), y+(tileSizeY/2),8,14,0.05f,180,90,16);
		}else if(type==9) {
			return new SecurityCamera(x+(tileSizeX/2), y+(tileSizeY/2),8,14,0.05f,360,270,16);
		}
		return null;
	}
	
	private static void setLevel(StateBasedGame game, String levelName, int[][] tlevel, 
			 int[][] ilevel, int[][] traplevel, int[][] cameralevel, ArrayList<Guard> guards) {
		
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
				
				//generate cameras
				int curCamera = cameralevel[y][x];
				if(curCamera>0) {
					SecurityCamera newCamera = cameraDepot(x*tileSizeX + tileSizeX/2, y*tileSizeY + tileSizeY/2, curCamera, tileSizeX, tileSizeY);
					mg.map.addSecurityCamera(newCamera);
				}
				
			}
		}
		
		//generate guards
		for(int i = 0;i<guards.size();i++) {
			mg.map.addGuard(guards.get(i));
		}
	}
	
	private static void levelTest(StateBasedGame game) {
		
		String levelName = "BASE LEVEL";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
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
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level9(StateBasedGame game) {
		
		String levelName = "Disco";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{0,0,0,0,0,0,0,0,0,0,9,9,9,9,0,0,0,0,0,0,0,0,0,0,0},
				{0,5,5,5,5,5,0,5,5,0,9,9,9,9,6,6,6,0,8,8,8,8,8,8,0},
				{0,5,5,0,5,5,5,5,5,5,9,9,9,9,0,6,6,6,8,8,8,8,8,8,0},
				{0,5,5,0,5,5,0,5,5,0,9,9,9,9,0,6,6,6,8,8,8,8,8,8,0},
				{0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0},
				{0,5,5,0,8,8,8,8,8,8,8,0,12,12,12,12,12,0,6,6,6,6,6,6,0},
				{0,5,5,0,8,8,8,0,8,8,8,0,12,12,12,12,12,0,6,6,6,6,6,6,0},
				{0,5,5,0,8,8,8,0,8,8,8,0,12,12,0,12,12,0,6,0,0,0,0,0,0},
				{0,5,5,5,8,8,8,0,8,8,8,0,12,12,0,12,12,0,8,8,8,8,8,8,0},
				{0,0,0,0,0,0,0,0,8,8,8,0,12,12,0,12,12,0,8,8,8,8,8,8,0},
				{0,12,12,12,12,12,12,12,8,8,8,0,12,12,0,12,12,0,8,8,8,8,8,8,0},
				{0,12,12,12,12,12,12,0,0,0,0,0,12,12,0,12,12,0,0,0,0,6,0,0,0},
				{0,12,12,12,12,12,12,0,12,0,12,12,12,12,0,12,12,6,6,6,6,6,6,6,0},
				{0,0,0,0,0,12,0,0,12,0,12,12,12,12,0,12,12,0,6,6,6,6,6,6,0},
				{0,12,12,12,12,12,12,12,12,12,12,12,12,12,0,12,12,0,6,6,6,6,6,6,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,6,0,0,4,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,0,0,0,2,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,1,0,0,1,1,0,1,1,0,0,0,0,0,0,11,0},
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
		
		int[][] cameralevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(8*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(8*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		p1.add(new Vector(8*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(10*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(10*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(18*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(18*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p2.add(new Vector(23*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p2.add(new Vector(23*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(18*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		Guard g3 = new Guard(18*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p3 = new ArrayList<Vector>();
		p3.add(new Vector(18*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2));
		p3.add(new Vector(23*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2));
		p3.add(new Vector(23*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		p3.add(new Vector(18*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		
		g3.setPatrolPath(p3);
		guards.add(g3);
		
		Guard g4 = new Guard(4*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p4 = new ArrayList<Vector>();
		p4.add(new Vector(4*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2));
		p4.add(new Vector(4*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p4.add(new Vector(6*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p4.add(new Vector(6*tileSizeX + tileSizeX/2,8*tileSizeY + tileSizeY/2));
		
		g4.setPatrolPath(p4);
		guards.add(g4);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level8(StateBasedGame game) {
		
		String levelName = "Zig Zag";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{9,9,9,9,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,1,1,1,1},
				{9,9,9,9,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,1,1,1,1},
				{9,9,9,9,9,3,0,3,3,3,0,3,3,3,0,3,3,3,0,3,0,1,1,1,1},
				{9,9,9,9,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{9,9,9,9,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{1,1,1,1,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{1,1,1,1,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{1,1,1,1,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{1,1,1,1,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{1,1,1,1,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,1,1,1,1},
				{1,1,1,1,0,3,3,3,0,3,3,3,0,3,3,3,0,3,3,3,0,1,1,1,1},
				{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
				{0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,2,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
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
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p1.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p1.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p2.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p2.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		Guard g3 = new Guard(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p3 = new ArrayList<Vector>();
		p3.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p3.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p3.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p3.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		
		g3.setPatrolPath(p3);
		guards.add(g3);
		
		Guard g4 = new Guard(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p4 = new ArrayList<Vector>();
		p4.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p4.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p4.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p4.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p4.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p4.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p4.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p4.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p4.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p4.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		
		g4.setPatrolPath(p4);
		guards.add(g4);
		
		Guard g5 = new Guard(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p5 = new ArrayList<Vector>();
		p5.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p5.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p5.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p5.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p5.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p5.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p5.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p5.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p5.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		
		g5.setPatrolPath(p5);
		guards.add(g5);
		
		Guard g6 = new Guard(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p6 = new ArrayList<Vector>();
		p6.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p6.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p6.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p6.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p6.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p6.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p6.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p6.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p6.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p6.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		
		g6.setPatrolPath(p6);
		guards.add(g6);
		
		Guard g7 = new Guard(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p7 = new ArrayList<Vector>();
		p7.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p7.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p7.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p7.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p7.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p7.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p7.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p7.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p7.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		
		g7.setPatrolPath(p7);
		guards.add(g7);
		
Guard g8 = new Guard(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p8 = new ArrayList<Vector>();
		p8.add(new Vector(19*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p8.add(new Vector(5*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p8.add(new Vector(5*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p8.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p8.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p8.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p8.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p8.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p8.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p8.add(new Vector(19*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		
		g8.setPatrolPath(p8);
		guards.add(g8);
		
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level7(StateBasedGame game) {
		
		String levelName = "Halls";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,4,4,5,5,5,5,5,0,11,11,5,11,11,0,5,5,5,5,5,4,4,0,1},
				{1,0,4,4,5,0,5,0,5,0,11,11,5,11,11,0,5,0,5,0,5,4,4,0,1},
				{1,0,4,4,5,0,5,0,5,0,11,11,5,11,11,0,5,0,5,0,5,4,4,0,1},
				{1,0,4,4,5,0,5,0,5,0,11,11,5,11,11,0,5,0,5,0,5,4,4,0,1},
				{1,0,4,4,5,0,5,0,5,0,11,11,5,11,11,0,5,0,5,0,5,4,4,0,1},
				{1,0,4,0,5,0,5,0,5,0,11,0,5,0,11,0,5,0,5,0,5,0,4,0,1},
				{1,0,4,0,5,0,5,0,5,0,11,0,5,0,11,0,5,0,5,0,5,0,4,0,1},
				{1,0,4,0,5,0,5,0,5,0,11,0,5,0,11,0,5,0,5,0,5,0,4,0,1},
				{1,0,4,0,5,0,5,0,5,0,11,0,5,0,11,0,5,0,5,0,5,0,4,0,1},
				{1,0,0,0,5,5,5,5,5,11,11,0,0,0,11,11,5,5,5,5,5,0,0,0,1},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
				{0,0,1,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,7,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,7,0,0},
				{0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
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
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(16*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(16*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p1.add(new Vector(16*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(18*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(18*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p1.add(new Vector(20*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p1.add(new Vector(20*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(18*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p1.add(new Vector(18*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(8*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(8*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p2.add(new Vector(8*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(6*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(6*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p2.add(new Vector(4*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p2.add(new Vector(4*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(6*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(6*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level6(StateBasedGame game) {
		
		String levelName = "Gallery";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},
				{1,1,0,11,11,11,11,11,11,11,11,11,0,4,4,4,4,4,4,4,0,9,9,9,9},
				{1,1,0,11,0,0,0,0,0,0,11,11,11,4,4,0,0,0,0,4,0,9,9,9,9},
				{1,1,0,11,0,0,6,6,6,6,6,6,6,6,4,0,0,0,0,4,0,9,9,9,9},
				{1,1,0,6,6,6,6,0,0,0,0,0,0,6,4,4,4,4,4,4,4,4,4,4,4},
				{1,1,0,6,0,0,0,0,6,6,6,6,0,6,6,6,6,6,6,6,6,6,6,6,6},
				{1,1,0,6,6,6,6,6,6,11,11,6,0,6,6,0,0,0,0,0,0,6,6,6,6},
				{1,1,0,6,0,0,0,0,6,6,6,6,0,6,6,6,6,6,6,6,6,6,6,6,6},
				{1,1,0,6,6,6,6,0,0,0,0,0,0,6,4,4,4,4,4,4,4,4,4,4,4},
				{1,1,0,11,0,0,6,6,6,6,6,6,6,6,4,0,0,0,0,4,0,9,9,9,9},
				{1,1,0,11,0,0,0,0,0,0,11,11,11,4,4,0,0,0,0,4,0,9,9,9,9},
				{1,1,0,11,11,11,11,11,11,11,11,11,0,4,4,4,4,4,4,4,0,9,9,9,9},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,-1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
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
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(19*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(19*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(14*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(14*tileSizeX + tileSizeX/2,2*tileSizeY + tileSizeY/2));
		p1.add(new Vector(19*tileSizeX + tileSizeX/2,2*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(19*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(19*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p2.add(new Vector(14*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p2.add(new Vector(14*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p2.add(new Vector(19*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		Guard g3 = new Guard(3*tileSizeX + tileSizeX/2,7*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p3 = new ArrayList<Vector>();
		p3.add(new Vector(3*tileSizeX + tileSizeX/2,7*tileSizeY + tileSizeY/2));
		p3.add(new Vector(3*tileSizeX + tileSizeX/2,2*tileSizeY + tileSizeY/2));
		p3.add(new Vector(10*tileSizeX + tileSizeX/2,2*tileSizeY + tileSizeY/2));
		p3.add(new Vector(10*tileSizeX + tileSizeX/2,4*tileSizeY + tileSizeY/2));
		p3.add(new Vector(6*tileSizeX + tileSizeX/2,4*tileSizeY + tileSizeY/2));
		p3.add(new Vector(6*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p3.add(new Vector(3*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p3.add(new Vector(3*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p3.add(new Vector(10*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p3.add(new Vector(10*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		p3.add(new Vector(6*tileSizeX + tileSizeX/2,10*tileSizeY + tileSizeY/2));
		p3.add(new Vector(6*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p3.add(new Vector(3*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		
		g3.setPatrolPath(p3);
		guards.add(g3);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	private static void level5(StateBasedGame game) {
		
		String levelName = "Castle";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},
				{1,1,1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,16},
				{1,1,1,16,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,16},
				{1,1,1,16,4,4,16,4,16,4,16,4,16,4,16,4,16,4,16,4,16,16,16,16,16},
				{1,1,1,16,4,4,16,4,16,4,16,4,16,4,16,4,16,4,16,4,16,4,4,4,16},
				{1,1,1,16,4,4,16,4,16,4,16,4,16,4,16,4,16,4,16,16,16,4,4,4,16},
				{1,1,1,16,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,4,4,4,16},
				{1,1,1,16,4,4,16,4,16,4,16,4,16,4,16,4,16,4,16,16,16,4,4,4,16},
				{1,1,1,16,4,4,16,4,16,4,16,4,16,4,16,4,16,4,16,4,16,4,4,4,16},
				{1,1,1,16,4,4,16,4,16,4,16,4,16,4,16,4,16,4,16,4,16,16,16,16,16},
				{1,1,1,16,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,16},
				{1,1,1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,16},
				{1,1,1,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{-1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,9,9,9,9,9,9,9,9,9,9,9,9,9,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(21*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(21*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(23*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p1.add(new Vector(23*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p1.add(new Vector(21*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(5*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(5*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(7*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(7*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(9*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(9*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(11*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(11*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(13*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(13*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(15*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p2.add(new Vector(15*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(17*tileSizeX + tileSizeX/2,11*tileSizeY + tileSizeY/2));
		p2.add(new Vector(17*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level4(StateBasedGame game) {
		
		String levelName = "Courtyard";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,14,14,14,14,14,14,14,14,14,1,14,14,14,14,14,14,14,14,14,1,1,1,1},
				{1,1,1,14,7,7,7,14,1,1,1,1,1,1,1,1,1,14,7,7,7,14,1,1,1},
				{1,1,1,14,7,7,7,14,1,1,1,1,1,1,1,1,1,14,7,7,7,14,1,1,1},
				{1,1,1,14,14,1,14,14,1,1,14,14,1,14,14,1,1,14,14,1,14,14,1,1,1},
				{1,1,1,14,1,1,1,1,1,1,14,5,5,5,14,1,1,1,1,1,1,14,1,1,1},
				{1,1,1,14,1,1,1,1,1,1,14,5,5,5,14,1,1,1,1,1,1,14,1,1,1},
				{1,1,1,14,1,1,1,1,1,1,14,5,5,5,14,1,1,1,1,1,1,14,1,1,1},
				{1,1,1,14,1,1,1,1,1,1,14,5,5,5,14,1,1,1,1,1,1,14,1,1,1},
				{1,1,1,14,14,1,14,14,1,1,14,14,14,14,14,1,1,14,14,1,14,14,1,1,1},
				{1,1,1,14,11,11,11,14,1,1,1,1,1,1,1,1,1,14,11,11,11,14,1,1,1},
				{1,1,1,14,11,11,11,14,1,1,1,1,1,1,1,1,1,14,11,11,11,14,1,1,1},
				{1,1,1,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,13,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,5,0,0,1,1,1,1,1,1,1,1,1,0,0,7,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,0,8,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(4*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(4*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2));
		p1.add(new Vector(7*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2));
		p1.add(new Vector(7*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p1.add(new Vector(4*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(17*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(17*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2));
		p2.add(new Vector(20*tileSizeX + tileSizeX/2,6*tileSizeY + tileSizeY/2));
		p2.add(new Vector(20*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p2.add(new Vector(17*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		Guard g3 = new Guard(8*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p3 = new ArrayList<Vector>();
		p3.add(new Vector(8*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		p3.add(new Vector(8*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(16*tileSizeX + tileSizeX/2,3*tileSizeY + tileSizeY/2));
		p3.add(new Vector(16*tileSizeX + tileSizeX/2,12*tileSizeY + tileSizeY/2));
		
		g3.setPatrolPath(p3);
		guards.add(g3);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level0(StateBasedGame game) {
		
		String levelName = "BASE LEVEL";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
		
		int[][] cameralevel = 
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
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level3(StateBasedGame game) {
		
		String levelName = "Storehouse";
		
		MainGame mg = (MainGame)game;
		int tileSizeX = mg.map.getTileSizeX();
		int tileSizeY = mg.map.getTileSizeY();
		
		
		int[][] tlevel = 
			   {{15,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
				{1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,1},
				{15,2,2,2,4,10,4,10,4,10,4,10,4,10,4,10,4,10,4,10,4,10,4,2,1},
				{1,2,4,2,4,10,4,10,4,10,4,10,4,10,4,10,4,10,4,10,4,10,4,2,1},
				{1,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,1},
				{1,2,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,1},
				{1,2,4,2,2,4,10,10,4,10,10,4,10,10,4,10,10,4,10,10,4,2,2,2,1},
				{1,2,4,2,2,4,10,10,4,10,10,4,10,10,4,10,10,4,10,10,4,2,2,2,1},
				{1,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,2,1},
				{1,2,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
				{1,2,13,13,13,13,13,3,10,10,10,10,3,10,10,10,10,3,13,13,13,13,3,2,1},
				{1,2,3,13,13,13,13,3,10,10,10,10,3,10,10,10,10,3,13,13,13,13,3,2,1},
				{1,2,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,2,1},
				{1,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
				{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		//-1 = getaway vehicle
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,9,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,10,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0},
				{0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		int[][] traplevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		int[][] cameralevel = 
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
		
		//guards
		ArrayList<Guard> guards = new ArrayList<Guard>();
		
		Guard g1 = new Guard(4*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p1 = new ArrayList<Vector>();
		p1.add(new Vector(4*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		p1.add(new Vector(22*tileSizeX + tileSizeX/2,1*tileSizeY + tileSizeY/2));
		
		g1.setPatrolPath(p1);
		guards.add(g1);
		
		Guard g2 = new Guard(22*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p2 = new ArrayList<Vector>();
		p2.add(new Vector(22*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		p2.add(new Vector(2*tileSizeX + tileSizeX/2,5*tileSizeY + tileSizeY/2));
		
		g2.setPatrolPath(p2);
		guards.add(g2);
		
		Guard g3 = new Guard(2*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p3 = new ArrayList<Vector>();
		p3.add(new Vector(2*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		p3.add(new Vector(22*tileSizeX + tileSizeX/2,9*tileSizeY + tileSizeY/2));
		
		g3.setPatrolPath(p3);
		guards.add(g3);
		
		Guard g4 = new Guard(22*tileSizeX + tileSizeX/2,13*tileSizeY + tileSizeY/2, tileSizeX, tileSizeY);
		
		ArrayList<Vector> p4 = new ArrayList<Vector>();
		p4.add(new Vector(22*tileSizeX + tileSizeX/2,13*tileSizeY + tileSizeY/2));
		p4.add(new Vector(2*tileSizeX + tileSizeX/2,13*tileSizeY + tileSizeY/2));
		
		g4.setPatrolPath(p4);
		guards.add(g4);
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
	
	private static void level1(StateBasedGame game) {
		String levelName = "TEST LEVEL 1";
			
			MainGame mg = (MainGame)game;
			int tileSizeX = mg.map.getTileSizeX();
			int tileSizeY = mg.map.getTileSizeY();
			
			
			int[][] tlevel = 
				   {{1,1,1,1,1,1,1,1,1,1,1,15,1,1,1,1,1,1,1,1,1,1,1,1,1},
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
					{0,0,0,0,10,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,11,1,0,0,0,0,0,1,0,5,0,1,1,0,0,1,0,0,0,0,0},
					{0,0,0,0,11,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0},
					{0,0,0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,9,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
					{0,0,0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,4,6,8,0,2,0,0,0,3,0,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
			
			//traps
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
			
			//cameras
			int[][] cameralevel = 
				   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
			
			//guards
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
			
			setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
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
		
		int[][] cameralevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		//guards
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
		
		setLevel(game, levelName, tlevel, ilevel, traplevel, cameralevel, guards);
	}
}