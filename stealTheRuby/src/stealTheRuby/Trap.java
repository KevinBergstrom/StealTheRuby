package stealTheRuby;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Trap extends Entity{

	public static final String PEELIMG_RSC = "stealTheRuby/resource/fruitPeel.png";
	
	public static void loadTextures() {
		
		ResourceManager.loadImage(PEELIMG_RSC);
		
	}
	
	private boolean playerOwned;
	private Image image;
	private String tex;
	
	public Trap(float x, float y, boolean playerSet) {
		super(x,y);
		playerOwned = playerSet;
		tex = null;
	}
	
	public boolean getPlayerOwned() {
		return playerOwned;
	}
	
	public void setImage(String texture, int sizeX,int sizeY) {
		Image newImage = ResourceManager.getImage(texture).getScaledCopy(sizeX, sizeY);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		image = newImage;
		tex = texture;
	}
	
	public void setImageWithColor(String texture, int sizeX,int sizeY, Color col) {
		
		Image newImage = ResourceManager.getImage(texture).getScaledCopy(sizeX, sizeY);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setImageColor(col.r, col.g, col.b);
		addImageWithBoundingBox(newImage);
		image = newImage;
		tex = texture;
		
	}
	
	public void springTrap(StateBasedGame game, Entity e) {
		
	}
	
	public void removeThis(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Vector gridPos = mg.map.getGridPos(this.getX(), this.getY());
		mg.map.removeTrap((int)gridPos.getX(), (int)gridPos.getY());
	}
	
}
