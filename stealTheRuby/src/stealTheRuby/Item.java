package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.Vector;

public class Item extends Entity{
	
	public Item(float x, float y) {
		super(x,y);
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
	
}
