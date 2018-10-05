package stealTheRuby;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.Vector;

public class PlayingState extends BasicGameState{

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		MainGame mg = (MainGame)game;
		
		//render everything
		mg.map.render(container, game, g);
		mg.player.render(g);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if(delta>100) {
			//ignore large deltas
			return;
		}
		
		Input input = container.getInput();
		MainGame mg = (MainGame)game;
		
		mg.player.setVelocity(new Vector(0, 0));

		//player controls
		if (input.isKeyDown(Input.KEY_W)) {
			mg.player.setVelocity(mg.player.getVelocity().add(new Vector(0.0f, -1.0f)));
		} else if (input.isKeyDown(Input.KEY_S)) {
			mg.player.setVelocity(mg.player.getVelocity().add(new Vector(0.0f, 1.0f)));
		}
		if (input.isKeyDown(Input.KEY_A)) {
			mg.player.setVelocity(mg.player.getVelocity().add(new Vector(-1.0f, 0.0f)));
		} else if (input.isKeyDown(Input.KEY_D)) {
			mg.player.setVelocity(mg.player.getVelocity().add(new Vector(1.0f, 0.0f)));
		}
		
		//update everything
		mg.player.update(delta);
		mg.player.keepInBounds(0, mg.ScreenWidth, 0, mg.ScreenHeight);
		mg.player.collideWithMap(mg.map);
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
