package stealTheRuby;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;

public class Tile extends Entity{

	private boolean solid;
	
	public Tile(float x, float y, float sx, float sy, boolean sol, String texture) {
		super(x, y);
		solid = sol;
		Image newImage = ResourceManager.getImage(texture).getScaledCopy((int)sx, (int)sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	
	public boolean getSolid() {
		return solid;
	}
	
	public void setSolid(boolean sol) {
		solid = sol;
	}
	
}
