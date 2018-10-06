package stealTheRuby;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;
import jig.Vector;

public class Coin extends Item{

	public Coin(float x, float y) {	
		super(x, y);
		Image newImage = ResourceManager.getImage(Map.COINIMG_RSC).getScaledCopy(32, 32);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addCoins(1);
		Vector gridPos = mg.map.getGridPos(this.getX(), this.getY());
		mg.map.removeItem((int)gridPos.getX(), (int)gridPos.getY());
	}
	
	
	
	
	
}
