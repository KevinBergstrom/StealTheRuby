package stealTheRuby;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class Ruby extends Item{

	public Ruby(float x, float y) {
		super(x, y, false);
		Image newImage = ResourceManager.getImage(Item.SMALLRUBYIMG_RSC).getScaledCopy(32, 32);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addRuby();
		removeThis(game);
	}

}
