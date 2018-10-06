package stealTheRuby;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class Lock extends Item{

	private Color color;
	
	public Lock(float x, float y, Color col) {
		super(x, y, true);
		
		color = col;
		
		Image newImage = ResourceManager.getImage(Item.LOCKIMG_RSC).getScaledCopy(32, 32);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setImageColor(col.r, col.g, col.b);
		addImageWithBoundingBox(newImage);
	}
	
	@Override
	public boolean unlock(Color c, StateBasedGame game) {
		if (c.r == color.r && c.g == color.g && c.b == color.b) {
			removeThis(game);
			System.out.println("removed it");
			return true;
		}else {
			return false;
		}
	}

}
