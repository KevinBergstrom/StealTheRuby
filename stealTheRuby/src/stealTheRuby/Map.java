package stealTheRuby;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
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
	
	public Map(int sx, int sy, int tsx, int tsy) {
		tilesX = sx;
		tilesY = sy;
		tileSizeX = tsx;
		tileSizeY = tsy;
		geometry = new Tile[sx][sy];
		items = new Item[sx][sy];
		loadTextures();
		testLevel();
		
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
		
		int[][] ilevel = 
			   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
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
	}
	
}
