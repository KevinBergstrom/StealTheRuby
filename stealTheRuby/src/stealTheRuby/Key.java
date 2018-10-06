package stealTheRuby;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class Key extends Item{

	private Color color;
	
	public Key(float x, float y, Color col) {
		super(x, y, false);
		
		color = col;
		
		Image newImage = ResourceManager.getImage(Item.KEYIMG_RSC).getScaledCopy(32, 32);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setImageColor(col.r, col.g, col.b);
		addImageWithBoundingBox(newImage);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addItem(this);
		removeThis(game);
	}

}
