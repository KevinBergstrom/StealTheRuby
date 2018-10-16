package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

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
	
	private int spriteDir;
	private Animation[] moveAnims;
	private Animation curAnim;
	
	public Guard(final float x, final float y, int sx, int sy) {
		super(x,y);
		sizex = sx;
		sizey = sy;
		
		moveAnims = new Animation[8];
		
		//add an invisible bounding box
		Image boundImage = ResourceManager.getImage(MainGame.TESTIMG_RSC).getScaledCopy(sx, sy);
		boundImage.setAlpha(0);
		addImageWithBoundingBox(boundImage);
		
		//walking anims
		for(int i = 0;i<4;i++) {
			Animation newAnim = new Animation(ResourceManager.getSpriteSheet(
					MainGame.GUARDSPRITESIMG_RSC, 32, 32), i, 0, i, 1, false, 300,
					true);
			newAnim.setLooping(false);
			moveAnims[i] = newAnim;
		}
		
		curAnim = moveAnims[0];
		addAnimation(curAnim);
		
		//frozen anims
		for(int i = 4;i<6;i++) {
			Animation newAnim = new Animation(ResourceManager.getSpriteSheet(
					MainGame.GUARDSPRITESIMG_RSC, 32, 32), 0, 2, 0, 2, false, 300,
					true);
			newAnim.setLooping(false);
			moveAnims[i] = newAnim;
		}
		for(int i = 6;i<8;i++) {
			Animation newAnim = new Animation(ResourceManager.getSpriteSheet(
					MainGame.GUARDSPRITESIMG_RSC, 32, 32), 2, 2, 2, 2, false, 300,
					true);
			newAnim.setLooping(false);
			moveAnims[i] = newAnim;
		}
		
		velocity = new Vector(0.0f, 0.0f);
		facing = new Vector(0.0f, 1.0f);
		
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
		spriteDir = 0;
	}
	
	public void incapacitate(float seconds) {
		frozen = seconds*1000;
	}
	
	public float getFrozen() {
		return frozen;
	}
	
	public void updatePlayerSprite(Vector v, int delta, float frozen) {
		int newDir = spriteDir;
		if(v.getY()>0) {
			newDir = 1;
		}else if(v.getY()<0) {
			newDir = 3;
		}else if(v.getX()>0) {
			newDir = 0;
		}else if(v.getX()<0) {
			newDir = 2;
		}else{
			newDir = -1;
		}
		
		//player is frozen
		if(frozen>0) {
			if(!curAnim.isStopped()) {
				removeAnimation(curAnim);
				curAnim = moveAnims[newDir+4];
				System.out.println(this.getNumAnimations());
				addAnimation(curAnim);
				curAnim.restart();
				curAnim.stop();
			}
		}else {
			//player is standing still
			if(newDir==-1) {
				if(!curAnim.isStopped()) {
					curAnim.restart();
					curAnim.stop();
				}
			}else {
				//same direction
				if(newDir == spriteDir) {
					if(curAnim.isStopped()) {
						curAnim.restart();
					}
				}else {
					//new direction
					removeAnimation(curAnim);
					curAnim = moveAnims[newDir];
					addAnimation(curAnim);
				}
				spriteDir = newDir;
			}
		}		
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
		updatePlayerSprite(facing, delta, frozen);
		if(frozen>0) {
			frozen-=delta;
		}else {
			translate(velocity.scale(delta*speed));
		}
		
	}
	
}
