package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;

public class Coin extends Item{

	final int type = 1;
	
	public Coin(float x, float y) {	
		super(x, y, false);
		setImage(Item.COINIMG_RSC,32,32);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addCoins(1);
		
		addCollectAnim(getX(),getY(),51,550,type,game);
		
		removeThis(game);
	}
	
	
	
	
	
}
