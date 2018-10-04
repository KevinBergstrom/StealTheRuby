package stealTheRuby;

import org.newdawn.slick.Image;


import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Player extends Entity{
	
	private Vector velocity;
	private float speed;
	private int sizex;
	private int sizey;
	
	public Player(final float x, final float y, int sx, int sy) {
		super(x,y);
		Image newImage = ResourceManager.getImage(MainGame.TESTIMG_RSC).getScaledCopy(sx, sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		
		velocity = new Vector(0.0f, 0.0f);
		
		sizex = sx;
		sizey = sy;
		
		speed = 0.1f;
	}
	
	public void setVelocity(final Vector v) {
		velocity = v;
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public void keepInBounds(float x1,float x2,float y1,float y2) {
		
		if(this.getCoarseGrainedMinX()<x1) {
			this.setPosition(x1+(sizex/2), this.getY());
		}else if(this.getCoarseGrainedMaxX()>x2){
			this.setPosition(x2-(sizex/2), this.getY());
		}
		
		if(this.getCoarseGrainedMinY()<y1) {
			this.setPosition(this.getX(), y1+(sizey/2));
		}else if(this.getCoarseGrainedMaxY()>y2){
			this.setPosition(this.getX(), y2-(sizey/2));
		}
	}
	
	public void update(final int delta) {
		translate(velocity.scale(delta*speed));
		
	}
}
