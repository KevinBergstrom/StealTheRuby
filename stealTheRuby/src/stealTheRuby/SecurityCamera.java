package stealTheRuby;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class SecurityCamera extends Entity{

	private Vector facing;
	private float startAngle;
	private float endAngle;
	private float angle;
	private float sizeX;
	private float sizeY;
	private float moveSpeed;
	private Image image;
	private int state;
	
	private Item visionCone;
	
	private float frozen;
	
	
	public SecurityCamera(float x, float y, int sx, int sy, float speed, float startA, float endA, int visionRange) {
		super(x,y);
		sizeX = sx;
		sizeY = sy;
		angle = startA;
		startAngle = startA;
		endAngle = endA;
		moveSpeed = speed;
		frozen = 0;
		state = 0;
		
		Image newImage = ResourceManager.getImage(MainGame.SECURITYCAMERAIMG_RSC).getScaledCopy(sx, sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		image = newImage;
		
		visionCone = new Item(-100,-100,false);
		visionCone.setImageWithColor(MainGame.VISIONCONEIMG_RSC, 32,32+visionRange, new Color(0,0,255));
	}
	
	public void incapacitate(float seconds) {
		frozen = seconds*1000;
	}
	
	public void updateVisionCone(Vector v){
		visionCone.setPosition(v.getX()*16 + this.getX(), v.getY()*16 + this.getY());
		visionCone.getImage().setRotation(angle);
	}
	
	public void updateCameraAngle() {
		image.setRotation(angle);
	}
	
	public Vector angleToVector(float rad) {
		return new Vector((float)Math.cos(rad),(float)Math.sin(rad));
	}
	
	public void renderCone(Graphics g) {
		visionCone.render(g);
	}
	
	public void update(final int delta) {
		if(frozen>0) {
			frozen-=delta;
		}else {
			if(state==0) {
				
				if(angle>startAngle) {
					angle-=moveSpeed*delta;
					if(angle<startAngle) {
						angle=startAngle;
						state = 1;
					}
				}else {
					angle+=moveSpeed*delta;
					if(angle>startAngle) {
						angle=startAngle;
						state = 1;
					}
				}
			}else if(state==1) {
				if(angle>endAngle) {
					angle-=moveSpeed*delta;
					if(angle<endAngle) {
						angle=endAngle;
						state = 0;
					}
				}else {
					angle+=moveSpeed*delta;
					if(angle>endAngle) {
						angle=endAngle;
						state = 0;
					}
				}
			}
			updateCameraAngle();
			facing = angleToVector((float)((angle+90)*Math.PI/180));
			updateVisionCone(facing);
		}
	}
	
}
