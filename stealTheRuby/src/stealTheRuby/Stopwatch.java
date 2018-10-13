package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;

public class Stopwatch extends Item{

	final int type = 3;
	
	public Stopwatch(float x, float y, boolean sol) {
		super(x, y, sol);
		setImage(Item.STOPWATCHIMG_RSC,32,32);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addItem(this);
		
		addCollectAnim(getX(),getY(),400,520,type,game);
	
		removeThis(game);
	}
	
	public void use(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.map.freeze(5);
		mg.player.removeItem();
	}
		

}
