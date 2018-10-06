package stealTheRuby;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.Vector;

public class ProjectileImage extends Entity{

	private Vector velocity;
	private float startX;
	private float startY;
	private float endX;
	private float endY;
	private float speed;
	private boolean finished;
	private int type;
	
	public ProjectileImage(float x, float y, float sx, float sy, float ex, float ey, int t, Item item) {
		super(x, y);
		
		setImage(item.getImage());
		
		type = t;
		startX = sx;
		startY = sy;
		endX = ex;
		endY = ey;
		speed = 0.7f;
		finished = false;
		
		//create a path vector
		velocity = new Vector(ex-sx,ey-sy);
		//normalize it
		velocity = new Vector(velocity.getX()/velocity.length(),velocity.getY()/velocity.length());
	}
	
	public void setImage(Image image) {
		addImageWithBoundingBox(image);
	}
	
	public int getType() {
		return type;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void update(final int delta) {
		
		translate(velocity.scale(delta*speed));
		float distTo = (float) Math.sqrt(((getX()-endX)*(getX()-endX))+((getY()-endY)*(getY()-endY)));
		if(distTo < delta*speed*2 || distTo < 3) {
			finished = true;
		}
	}
	
}
