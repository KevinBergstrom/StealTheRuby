package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;
import stealTheRuby.PlayingState;

public class MainGame extends StateBasedGame {

	public static final int SPLASHSTATE = 0;
	public static final int PLAYINGSTATE = 1;
	public static final int LOADINGSTATE =2;
	public static final int RESULTSSTATE = 3;
	
	public static final String TESTIMG_RSC = "stealTheRuby/resource/testTile.png";
	
	Player player;
	Map map;
	
	public ArrayList<ProjectileImage> collectAnims;
	
	public final int ScreenWidth;
	public final int ScreenHeight;
	public int currentLevel;
	public int score;
	
	public MainGame(String title, int width, int height) {
		super (title);
		
		ScreenHeight = height;
		ScreenWidth = width;
		
		Entity.setCoarseGrainedCollisionBoundary(Entity.AABB);
		
	    }
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new SplashState());
		addState(new PlayingState());
		addState(new LoadingState());
		addState(new ResultsState());
	
		ResourceManager.loadImage(TESTIMG_RSC);
		
		Item.loadTextures();
		Levels.loadTextures();
		player = new Player(590,116,32,32);
		//TODO move later
		map = new Map(25, 16, 32, 32);
		
		collectAnims = new ArrayList<ProjectileImage>();
		currentLevel = 0;
		score = 0;
		
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