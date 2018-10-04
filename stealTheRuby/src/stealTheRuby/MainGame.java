package stealTheRuby;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;
import stealTheRuby.PlayingState;

public class MainGame extends StateBasedGame {

	public static final int PLAYINGSTATE = 0;
	
	public static final String TESTIMG_RSC = "stealTheRuby/resource/testTile.png";
	
	Player player;
	Map map;
	
	public final int ScreenWidth;
	public final int ScreenHeight;
	
	public MainGame(String title, int width, int height) {
		super (title);
		
		ScreenHeight = height;
		ScreenWidth = width;
		
		Entity.setCoarseGrainedCollisionBoundary(Entity.AABB);
		
	    }
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new PlayingState());
	
		ResourceManager.loadImage(TESTIMG_RSC);
		
		player = new Player(100,100,32,32);
		
		map = new Map(25, 19, 32, 32);
		
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