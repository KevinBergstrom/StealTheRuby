package stealTheRuby;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import stealTheRuby.PlayingState;

public class MainGame extends StateBasedGame {

	public static final int PLAYINGSTATE = 0;
	
	public MainGame(String title, int width, int height) {
		super (title);
	    }
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new PlayingState());
	

	}
	
	public static void main(String[] args) {
	        AppGameContainer app;
	        try {
	            app = new AppGameContainer(new MainGame("Steal The Ruby", 800, 600));
	            app.setDisplayMode(800, 600, false);
	            app.setVSync(true);
	            app.start();
	        } 
	catch
	 (SlickException e) {
	            e.printStackTrace();
	        }
	    }
}