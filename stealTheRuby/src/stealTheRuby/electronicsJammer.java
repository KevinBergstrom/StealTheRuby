package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.state.StateBasedGame;

import jig.Vector;

public class electronicsJammer extends Item{

	final int type = 3;
	
	public electronicsJammer(float x, float y, boolean sol) {
		super(x, y, sol);
		setImage(Item.JAMMERIMG_RSC,32,32);
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
		ArrayList<SecurityCamera> cameras = mg.map.getCameras();
		for(int i =0;i<cameras.size();i++) {
			cameras.get(i).incapacitate(10);
		}
		mg.player.removeItem();
	}

}
