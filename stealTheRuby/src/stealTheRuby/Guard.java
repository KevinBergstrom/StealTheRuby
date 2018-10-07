package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Guard extends Entity{

	private Vector velocity;
	private float speed;
	private int sizex;
	private int sizey;
	
	private int patrolPoint;
	private ArrayList<Vector> patrolPath;
	
	public Guard(final float x, final float y, int sx, int sy) {
		super(x,y);
		Image newImage = ResourceManager.getImage(MainGame.TESTIMG_RSC).getScaledCopy(sx, sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setImageColor(0, 0, 255);
		addImageWithBoundingBox(newImage);
		
		velocity = new Vector(0.0f, 0.0f);
		
		sizex = sx;
		sizey = sy;
		
		speed = 0.1f;
		
		patrolPoint = 0;
		patrolPath = new ArrayList<Vector>();
	}
	
	public void setPatrolPath(ArrayList<Vector> points) {
		patrolPath = points;
	}
	
	public void setVelocity(Vector vel) {
		velocity = vel;
	}
	
	public void followPatrolPath() {
		float leeway = 3;
		Vector velVec = new Vector(0,0);
		Vector nextPoint = patrolPath.get(patrolPoint);
		Vector dif = new Vector(nextPoint.getX()-getX(),nextPoint.getY()-getY());
		
		if(dif.length()<=leeway) {
			patrolPoint++;
			if(patrolPoint>=patrolPath.size()) {
				patrolPoint = 0;
			}
			setPosition(nextPoint.getX(),nextPoint.getY());
		}else {
			velVec = new Vector(dif.getX()/dif.length(),dif.getY()/dif.length());
		}
		setVelocity(velVec);
		//System.out.println(velVec);
		//setVelocity();
	}
	
	public void update(final int delta) {
		followPatrolPath();
		translate(velocity.scale(delta*speed));
		
	}
	
}
