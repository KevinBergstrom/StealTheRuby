package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;


public class Ruby extends Item{

	final int type = 2;
	
	public Ruby(float x, float y) {
		super(x, y, false);
		setImage(Item.SMALLRUBYIMG_RSC,32,32);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addRuby();
		mg.map.prepareGetaway();
		
		addCollectAnim(getX(),getY(),53,464,type,game);
		
		removeThis(game);
	}

}
