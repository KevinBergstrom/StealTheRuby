package stealTheRuby;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Item extends Entity{
	
	public static final String COINIMG_RSC = "stealTheRuby/resource/coin.png";
	public static final String SMALLRUBYIMG_RSC = "stealTheRuby/resource/smallRuby.png";
	public static final String KEYIMG_RSC = "stealTheRuby/resource/key.png";
	public static final String LOCKIMG_RSC = "stealTheRuby/resource/lock.png";
	
	public static void loadTextures() {
		
		ResourceManager.loadImage(COINIMG_RSC);
		ResourceManager.loadImage(SMALLRUBYIMG_RSC);
		ResourceManager.loadImage(KEYIMG_RSC);
		ResourceManager.loadImage(LOCKIMG_RSC);
		
	}
	
	private boolean solid;
	
	public Item(float x, float y, boolean sol) {
		super(x,y);
		solid = sol;
	}
	
	public void pickup(StateBasedGame game) {
		
	}
	
	public void use(StateBasedGame game) {
		
	}
	
	public void removeThis(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Vector gridPos = mg.map.getGridPos(this.getX(), this.getY());
		mg.map.removeItem((int)gridPos.getX(), (int)gridPos.getY());
	}
	
	public void addToInventory(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addItem(this);
	}
	
	public boolean getSolid() {
		return solid;
	}
	
	public void setSolid(boolean sol) {
		solid = sol;
	}
	
	public boolean unlock(Color c, StateBasedGame game) {
		return false;
	}
	
}
