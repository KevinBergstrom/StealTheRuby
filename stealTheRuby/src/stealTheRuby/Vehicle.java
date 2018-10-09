package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;

import jig.Vector;


public class Vehicle extends Item{

	private Vector velocity;
	
	public Vehicle(float x, float y, boolean sol) {
		super(x, y, sol);
		
		setImage(Item.BIKEIMG_RSC,48,32);
		velocity = new Vector(0.0f, 0.0f);
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector vel) {
		velocity = vel;
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		
		if(!getSolid()) {
			//TODO win
			System.exit(0);
		}
		
	}

}
