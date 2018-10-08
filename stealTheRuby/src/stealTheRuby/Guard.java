package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Guard extends Entity{

	private Vector velocity;
	private float speed;
	private int sizex;
	private int sizey;
	private boolean alerted;
	private boolean patroling;
	
	private int patrolPoint;
	private ArrayList<Vector> patrolPath;
	
	private int followPoint;
	private ArrayList<Vector> followPath;
	
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
		alerted = false;
		patroling = true;
		
		patrolPoint = 0;
		patrolPath = new ArrayList<Vector>();
		
		followPoint = 0;
		followPath = new ArrayList<Vector>();
	}
	
	public void patrol() {
		//TODO need a way to walk back to the patrol route
		alerted = false;
		patroling = true;
	}
	
	public void alert() {
		alerted = true;
		patroling = false;
	}
	
	public void setPatrolPath(ArrayList<Vector> points) {
		patrolPoint = 0;
		patrolPath = points;
	}
	
	public void setFollowPath(ArrayList<Vector> points) {
		followPoint = 0;
		followPath = points;
	}
	
	public void setVelocity(Vector vel) {
		velocity = vel;
	}
	
	public void followPatrolPath() {
		float leeway = 3;
		Vector velVec = new Vector(0,0);
		if(patrolPath.isEmpty()) {
			return;
		}
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
	}
	
	public void followPath() {
		float leeway = 3;
		Vector velVec = new Vector(0,0);
		if(followPath.isEmpty()) {
			return;
		}
		Vector nextPoint = followPath.get(followPoint);
		Vector dif = new Vector(nextPoint.getX()-getX(),nextPoint.getY()-getY());
		
		if(dif.length()<=leeway) {
			followPoint++;
			if(followPoint>=followPath.size()) {
				followPoint = 0;
			}
			setPosition(nextPoint.getX(),nextPoint.getY());
		}else {
			velVec = new Vector(dif.getX()/dif.length(),dif.getY()/dif.length());
		}
		setVelocity(velVec);
	}
	
	public void renderPath(Graphics g) {
		//for testing purposes
		for(int i = 0;i<followPath.size();i++) {
			Vector point = followPath.get(i);
			Image pimg = ResourceManager.getImage(MainGame.TESTIMG_RSC);
			pimg.setFilter(Image.FILTER_NEAREST);
			g.drawImage(pimg,
					point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10,0, 0,20,20 );
		}
	}
	
	public void update(final int delta) {
		if(alerted) {
			followPath();
		}else if(patroling){
			followPatrolPath();
		}
		translate(velocity.scale(delta*speed));
		
	}
	
}
