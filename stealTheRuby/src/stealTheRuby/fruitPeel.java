package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;

public class fruitPeel extends Trap{

	public fruitPeel(float x, float y, boolean playerSet) {
		super(x, y, playerSet);
		setImage(Trap.PEELIMG_RSC,32,32);
	}

	@Override
	public void springTrap(StateBasedGame game, Entity e) {
		if(!getPlayerOwned()) {
			//trap not set by player
			Player p = (Player)e;
			p.incapacitate(3);
		}
		if(getPlayerOwned()) {
			//trap set by player
			Guard g = (Guard)e;
			g.incapacitate(3);
		}
		removeThis(game);
	}
	
}
