package stealTheRuby;

import org.newdawn.slick.state.StateBasedGame;

import jig.Vector;

public class fruit extends Item{

	final int type = 3;
	
	public fruit(float x, float y, boolean sol) {
		super(x, y, sol);
		setImage(Trap.PEELIMG_RSC,32,32);
		setName("Fruit");
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addItem(this);
		
		addCollectAnim(getX(),getY(),400,520,type,game);
	
		removeThis(game);
	}
	
	@Override
	public void use(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Vector gridPos = mg.map.getGridPos(mg.player.getX(), mg.player.getY());
		fruitPeel peel = new fruitPeel(gridPos.getX()*mg.map.getTileSizeX() + mg.map.getTileSizeX()/2,gridPos.getY()*mg.map.getTileSizeY() + mg.map.getTileSizeY()/2,true);
		mg.map.setTrap((int)gridPos.getX(), (int)gridPos.getY(), peel);
		mg.player.removeItem();
	}

}
