package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Guard extends Entity{

	private Vector velocity;
	private Vector facing;
	private float speed;
	private int sizex;
	private int sizey;
	
	private int state;
	private float patrolSpeed;
	private float chaseSpeed;
	
	private int patrolPoint;
	private ArrayList<Vector> patrolPath;
	
	private int followPoint;
	private ArrayList<Vector> followPath;
	
	private Item visionCone;
	private float frozen;
	
	public Guard(final float x, final float y, int sx, int sy) {
		super(x,y);
		Image newImage = ResourceManager.getImage(MainGame.TESTIMG_RSC).getScaledCopy(sx, sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setImageColor(0, 0, 255);
		addImageWithBoundingBox(newImage);
		
		velocity = new Vector(0.0f, 0.0f);
		facing = new Vector(0.0f, 1.0f);
		
		sizex = sx;
		sizey = sy;
		
		speed = 0.08f;
		patrolSpeed = 0.08f;
		chaseSpeed = 0.09f;
		
		state = 0;
		//0 = patroling
		//1 = chasing
		//2 = returning to patrol
		//3 = chasing and ready for path update
		
		patrolPoint = 0;
		patrolPath = new ArrayList<Vector>();
		
		followPoint = 0;
		followPath = new ArrayList<Vector>();
		
		visionCone = new Item(-100,-100,false);
		visionCone.setImageWithColor(MainGame.VISIONCONEIMG_RSC, 32,64, new Color(0,0,255));
		frozen = 0;
	}
	
	public void incapacitate(float seconds) {
		frozen = seconds*1000;
	}
	
	public float getFrozen() {
		return frozen;
	}
	
	public void updateVisionCone(Vector v){
		visionCone.setPosition(v.getX()*16 + this.getX(), v.getY()*16 + this.getY());
		visionCone.getImage().setRotation((float) (Math.atan2(v.getY(), v.getX())*180/Math.PI)-90);
	}
	
	public boolean collideWithVisionCone(Entity a) {
		if(a.collides(visionCone)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void patrol() {
		state = 0;
		visionCone.getImage().setImageColor(0, 0, 255);
		speed = patrolSpeed;
	}
	
	public void chase() {
		state = 1;
		visionCone.getImage().setImageColor(255, 0, 0);
		speed = chaseSpeed;
	}
	
	public void investigate() {
		state = -1;
		visionCone.getImage().setImageColor(255, 0, 0);
		speed = chaseSpeed;
	}
	
	public void returnToPatrolPath() {
		if(state == 1 || state == 3 || state == -1 || state == 4) {
			state = 2;
			visionCone.getImage().setImageColor(0, 0, 255);
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
					patrol();
					//patrolPoint = 0;
				}else if(state == -1) {
					state = 4;
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
	
	public void renderCone(Graphics g) {
		visionCone.render(g);
	}
	
	public void update(final int delta) {
		if(state == -1) {
			followPath();
		}else if(state == 0) {
			followPatrolPath();
		}else if(state == 1){
			followPath();
		}else if(state == 2) {
			followPath();
		}
		if(velocity.getX()!=0 || velocity.getY()!=0) {
			facing = velocity;
		}
		updateVisionCone(facing);
		if(frozen>0) {
			frozen-=delta;
		}else {
			translate(velocity.scale(delta*speed));
		}
		
	}
	
}
