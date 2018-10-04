package stealTheRuby;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;

public class Tile extends Entity{

	private float sizeX;
	private float sizeY;
	
	public Tile(float x, float y, float sx, float sy, String texture) {
		super(x, y);
		sizeX = sx;
		sizeY = sy;
		Image newImage = ResourceManager.getImage(texture).getScaledCopy((int)sx, (int)sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	
}
