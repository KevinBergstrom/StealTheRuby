package stealTheRuby;

import java.util.ArrayList;

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
	
	private ArrayList<Item> inventory;
	
	
	public Player(final float x, final float y, int sx, int sy) {
		super(x,y);
		Image newImage = ResourceManager.getImage(MainGame.TESTIMG_RSC).getScaledCopy(sx, sy);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		
		velocity = new Vector(0.0f, 0.0f);
		itemSelected = 0;
		
		sizex = sx;
		sizey = sy;
		
		//only -0.3 to 0.3 speeds are safe
		speed = 0.1f;
		
		coins = 0;
		hasRuby = false;
		lives = 3;
		
		inventory = new ArrayList<Item>();
		
	}
	
	public void setVelocity(final Vector v) {
		velocity = v;
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public void reset() {
		//TODO update when more things added
		coins = 0;
		hasRuby = false;
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
	
	public int getLives() {
		return lives;
	}
	
	public void subLives() {
		lives--;
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
		//update gui?
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
		translate(velocity.scale(delta*speed));
		
	}
}
