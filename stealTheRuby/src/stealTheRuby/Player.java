package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Player extends Entity{
	
	private Vector velocity;
	private float speed;
	private int sizex;
	private int sizey;
	
	private int coins;
	private boolean hasRuby;
	private int itemSelected;
	private int lives;
	
	public int minutesScore;
	public int secondsScore;
	public boolean spottedScore;
	public int attemptsScore;
	
	public float frozen;
	
	private boolean escaped;
	
	private ArrayList<Item> inventory;
	
	private int spriteDir;
	
	private Animation[] moveAnims;
	private Animation curAnim;
	
	
	
	public Player(final float x, final float y, int sx, int sy) {
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
					MainGame.PLAYERSPRITESIMG_RSC, 32, 32), i, 0, i, 1, false, 300,
					true);
			newAnim.setLooping(false);
			moveAnims[i] = newAnim;
		}
		
		curAnim = moveAnims[0];
		addAnimation(curAnim);
		
		//frozen anims
		for(int i = 4;i<6;i++) {
			Animation newAnim = new Animation(ResourceManager.getSpriteSheet(
					MainGame.PLAYERSPRITESIMG_RSC, 32, 32), 0, 2, 0, 2, false, 300,
					true);
			newAnim.setLooping(false);
			moveAnims[i] = newAnim;
		}
		for(int i = 6;i<8;i++) {
			Animation newAnim = new Animation(ResourceManager.getSpriteSheet(
					MainGame.PLAYERSPRITESIMG_RSC, 32, 32), 2, 2, 2, 2, false, 300,
					true);
			newAnim.setLooping(false);
			moveAnims[i] = newAnim;
		}
		
		
		velocity = new Vector(0.0f, 0.0f);
		itemSelected = 0;
		
		//only -0.3 to 0.3 speeds are safe
		speed = 0.1f;
		
		coins = 0;
		hasRuby = false;
		lives = 3;
		escaped = false;
		frozen = 0;
		spriteDir = 0;
		
		inventory = new ArrayList<Item>();
		
	}
	
	public void setEscaped(boolean b) {
		escaped = b;
	}
	
	public boolean getEscaped() {
		return escaped;
	}
	
	public void setVelocity(final Vector v) {
		velocity = v;
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public void incapacitate(float seconds) {
		frozen = seconds*1000;
	}
	
	public void reset() {
		//TODO update when more things added
		coins = 0;
		hasRuby = false;
		itemSelected = 0;
		escaped = false;
		inventory.clear();
		frozen = 0;
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
	
	public int getLives() {
		return lives;
	}
	
	public void subLives() {
		lives--;
	}
	
	public void setLives(int l) {
		lives = l;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public void addCoins(int n) {
		coins = coins + n;
	}
	
	public void addRuby() {
		hasRuby = true;
	}
	
	public boolean hasRuby() {
		return hasRuby;
	}
	
	public void addItem(Item t) {
		inventory.add(t);
		//update gui?
	}
	
	public Item[] getSelectedItems() {
		Item[] ret = {null,null,null};
		
		for(int i = 0;i<3;i++) {
			if(i-1+itemSelected>=0 && i-1+itemSelected<inventory.size()) {
				ret[i]=inventory.get(i-1+itemSelected);
			}
		}
		
		return ret;
		
	}
	
	public void useItem(StateBasedGame game) {
		if(itemSelected>inventory.size()) {
			itemSelected = 0;
		}
		if(itemSelected<inventory.size()) {
			if(inventory.get(itemSelected) != null) {
				inventory.get(itemSelected).use(game);
				if(itemSelected>=inventory.size()&&inventory.size()>0) {
					itemSelected = inventory.size()-1;
				}
			}
		}
	}
	
	public void removeItem() {
		inventory.remove(itemSelected);
	}
	
	public float distanceTo(Entity e) {
		Vector dist = new Vector(getX()-e.getX(),getY()-e.getY());
		return dist.length();
	}
	
	public void itemScroll(boolean forward) {
		if(forward) {
			itemSelected++;
			if(itemSelected>=inventory.size()) {
				itemSelected = inventory.size()-1;
			}
		}else {
			itemSelected--;
			if(itemSelected<0) {
				itemSelected = 0;
			}
		}
	}
	
	public void collideWithItems(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Vector gridPos = mg.map.getGridPos(this.getX(), this.getY());
		Item p = mg.map.getItemAtPoint((int)gridPos.getX(),(int)gridPos.getY());
		
		if(p!=null && collides(p) != null) {
			p.pickup(mg);
		}
		solidItemCollide(mg.map);
	}
	
	public void collideWithTraps(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Vector gridPos = mg.map.getGridPos(this.getX(), this.getY());
		Trap p = mg.map.getTrapAtPoint((int)gridPos.getX(),(int)gridPos.getY());

		if(p!=null && !p.getPlayerOwned() && collides(p) != null) {
			p.springTrap(game, this);
		}

	}
	
	public void solidItemCollide(Map map) {
		float leeway = speed*30;
		
		Vector gridPos = map.getGridPos(this.getX(), this.getY());
		
		Item p1 = map.getItemAtPoint((int)gridPos.getX()-1,(int)gridPos.getY());
		Item p2 = map.getItemAtPoint((int)gridPos.getX()+1,(int)gridPos.getY());
		Item p3 = map.getItemAtPoint((int)gridPos.getX(),(int)gridPos.getY()-1);
		Item p4 = map.getItemAtPoint((int)gridPos.getX(),(int)gridPos.getY()+1);
		Item p5 = map.getItemAtPoint((int)gridPos.getX()-1,(int)gridPos.getY()-1);
		Item p6 = map.getItemAtPoint((int)gridPos.getX()+1,(int)gridPos.getY()+1);
		Item p7 = map.getItemAtPoint((int)gridPos.getX()-1,(int)gridPos.getY()+1);
		Item p8 = map.getItemAtPoint((int)gridPos.getX()+1,(int)gridPos.getY()-1);
		
		
		if(p1!=null) {
			if(p1.getSolid() && collides(p1) != null) {
				this.setX(p1.getCoarseGrainedMaxX() + this.sizex/2);
			}
		}
		if(p2!=null) {
			if(p2.getSolid() && collides(p2) != null) {
				this.setX(p2.getCoarseGrainedMinX() - this.sizex/2);
			}
		}
		if(p3!=null) {
			if(p3.getSolid() && collides(p3) != null) {
				this.setY(p3.getCoarseGrainedMaxY() + this.sizey/2);
			}
		}
		if(p4!=null) {
			if(p4.getSolid() && collides(p4) != null) {
				this.setY(p4.getCoarseGrainedMinY() - this.sizey/2);
			}
		}
		if(p5!=null) {
			if(p5.getSolid() && collides(p5) != null) {
				if(p5.getCoarseGrainedMaxY()-getCoarseGrainedMinY()>leeway) {
					this.setX(p5.getCoarseGrainedMaxX() + this.sizex/2);
				}
				if(p5.getCoarseGrainedMaxX()-getCoarseGrainedMinX()>leeway) {
					this.setY(p5.getCoarseGrainedMaxY() + this.sizey/2);
				}
			}
		}
		if(p6!=null) {
			if(p6.getSolid() && collides(p6) != null) {
				if(getCoarseGrainedMaxY()-p6.getCoarseGrainedMinY()>leeway) {
					this.setX(p6.getCoarseGrainedMinX() - this.sizex/2);
				}
				if(getCoarseGrainedMaxX()-p6.getCoarseGrainedMinX()>leeway) {
					this.setY(p6.getCoarseGrainedMinY() - this.sizey/2);
				}
			}
		}
		if(p7!=null) {
			if(p7.getSolid() && collides(p7) != null) {
				if(getCoarseGrainedMaxY()-p7.getCoarseGrainedMinY()>leeway) {
					this.setX(p7.getCoarseGrainedMaxX() + this.sizex/2);
				}
				if(p7.getCoarseGrainedMaxX()-getCoarseGrainedMinX()>leeway) {
					this.setY(p7.getCoarseGrainedMinY() - this.sizey/2);
				}
			}
		}
		if(p8!=null) {
			if(p8.getSolid() && collides(p8) != null) {
				if(p8.getCoarseGrainedMaxY()-getCoarseGrainedMinY()>leeway) {
					this.setX(p8.getCoarseGrainedMinX() - this.sizex/2);
				}
				if(getCoarseGrainedMaxX()-p8.getCoarseGrainedMinX()>leeway) {
					this.setY(p8.getCoarseGrainedMaxY() + this.sizey/2);
				}
			}
		}
	}
	
	public void collideWithMap(Map map) {
		
		float leeway = speed*30;
		
		Vector gridPos = map.getGridPos(this.getX(), this.getY());
		
		Tile p1 = map.getPoint((int)gridPos.getX()-1,(int)gridPos.getY());
		Tile p2 = map.getPoint((int)gridPos.getX()+1,(int)gridPos.getY());
		Tile p3 = map.getPoint((int)gridPos.getX(),(int)gridPos.getY()-1);
		Tile p4 = map.getPoint((int)gridPos.getX(),(int)gridPos.getY()+1);
		Tile p5 = map.getPoint((int)gridPos.getX()-1,(int)gridPos.getY()-1);
		Tile p6 = map.getPoint((int)gridPos.getX()+1,(int)gridPos.getY()+1);
		Tile p7 = map.getPoint((int)gridPos.getX()-1,(int)gridPos.getY()+1);
		Tile p8 = map.getPoint((int)gridPos.getX()+1,(int)gridPos.getY()-1);
		
		
		if(p1!=null) {
			if(p1.getSolid() && collides(p1) != null) {
				this.setX(p1.getCoarseGrainedMaxX() + this.sizex/2);
			}
		}
		if(p2!=null) {
			if(p2.getSolid() && collides(p2) != null) {
				this.setX(p2.getCoarseGrainedMinX() - this.sizex/2);
			}
		}
		if(p3!=null) {
			if(p3.getSolid() && collides(p3) != null) {
				this.setY(p3.getCoarseGrainedMaxY() + this.sizey/2);
			}
		}
		if(p4!=null) {
			if(p4.getSolid() && collides(p4) != null) {
				this.setY(p4.getCoarseGrainedMinY() - this.sizey/2);
			}
		}
		if(p5!=null) {
			if(p5.getSolid() && collides(p5) != null) {
				if(p5.getCoarseGrainedMaxY()-getCoarseGrainedMinY()>leeway) {
					this.setX(p5.getCoarseGrainedMaxX() + this.sizex/2);
				}
				if(p5.getCoarseGrainedMaxX()-getCoarseGrainedMinX()>leeway) {
					this.setY(p5.getCoarseGrainedMaxY() + this.sizey/2);
				}
			}
		}
		if(p6!=null) {
			if(p6.getSolid() && collides(p6) != null) {
				if(getCoarseGrainedMaxY()-p6.getCoarseGrainedMinY()>leeway) {
					this.setX(p6.getCoarseGrainedMinX() - this.sizex/2);
				}
				if(getCoarseGrainedMaxX()-p6.getCoarseGrainedMinX()>leeway) {
					this.setY(p6.getCoarseGrainedMinY() - this.sizey/2);
				}
			}
		}
		if(p7!=null) {
			if(p7.getSolid() && collides(p7) != null) {
				if(getCoarseGrainedMaxY()-p7.getCoarseGrainedMinY()>leeway) {
					this.setX(p7.getCoarseGrainedMaxX() + this.sizex/2);
				}
				if(p7.getCoarseGrainedMaxX()-getCoarseGrainedMinX()>leeway) {
					this.setY(p7.getCoarseGrainedMinY() - this.sizey/2);
				}
			}
		}
		if(p8!=null) {
			if(p8.getSolid() && collides(p8) != null) {
				if(p8.getCoarseGrainedMaxY()-getCoarseGrainedMinY()>leeway) {
					this.setX(p8.getCoarseGrainedMinX() - this.sizex/2);
				}
				if(getCoarseGrainedMaxX()-p8.getCoarseGrainedMinX()>leeway) {
					this.setY(p8.getCoarseGrainedMaxY() + this.sizey/2);
				}
			}
		}
	}
	
	public void update(final int delta) {
		updatePlayerSprite(velocity, delta, frozen);
		if(frozen>0) {
			frozen-=delta;
		}else {
			translate(velocity.scale(delta*speed));
		}
		
	}
}
