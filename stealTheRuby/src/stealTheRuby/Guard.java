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
	
	private int state;
	
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
		
		speed = 0.08f;
		
		state = 0;
		//0 = patroling
		//1 = chasing
		//2 = returning to patrol
		//3 = chasing and ready for path update
		
		patrolPoint = 0;
		patrolPath = new ArrayList<Vector>();
		
		followPoint = 0;
		followPath = new ArrayList<Vector>();
	}
	
	public void patrol() {
		state = 0;
	}
	
	public void chase() {
		state = 1;
	}
	
	public void returnToPatrolPath() {
		if(state == 1 || state == 3) {
			state = 2;
		}
	}
	
	public ArrayList<Vector> getPatrolPath() {
		return patrolPath;
	}
	
	public int getPatrolPoint() {
		return patrolPoint;
	}
	
	public int getState() {
		return state;
	}
	
	public void setPatrolPath(ArrayList<Vector> points) {
		patrolPoint = 0;
		patrolPath = points;
	}
	
	public void setFollowPath(ArrayList<Vector> points) {
		if(points.size()>1) {
			followPoint = 1;
		}else {
			followPoint = 0;
		}
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
			//reached a new point
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
			//reached a new point
			followPoint++;
			if(followPoint>=followPath.size()) {
				followPoint = 0;
				if(state == 2) {
					//found way back to patrol
					state = 0;
					//patrolPoint = 0;
				}
			}
			setPosition(nextPoint.getX(),nextPoint.getY());
			if(state == 1) {
				//ready to receive new path
				state = 3;
			}
		}else {
			velVec = new Vector(dif.getX()/dif.length(),dif.getY()/dif.length());
		}
		setVelocity(velVec);
	}
	
	public void renderPath(Graphics g) {
		//for testing purposes
		if(state>0) {
			for(int i = 0;i<followPath.size();i++) {
				Vector point = followPath.get(i);
				Image pimg = ResourceManager.getImage(MainGame.TESTIMG_RSC);
				pimg.setFilter(Image.FILTER_NEAREST);
				g.drawImage(pimg,
						point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10,0, 0,20,20 );
			}
		}else {
			for(int i = 0;i<patrolPath.size();i++) {
				Vector point = patrolPath.get(i);
				Image pimg = ResourceManager.getImage(MainGame.TESTIMG_RSC);
				pimg.setFilter(Image.FILTER_NEAREST);
				g.drawImage(pimg,
						point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10,0, 0,20,20 );
			}
		}
	}
	
	public void update(final int delta) {
		if(state == 0) {
			followPatrolPath();
		}else if(state == 1){
			followPath();
		}else if(state == 2) {
			followPath();
		}
		translate(velocity.scale(delta*speed));
		
	}
	
}
