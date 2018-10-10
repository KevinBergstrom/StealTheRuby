package stealTheRuby;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class ResultsState extends BasicGameState{

	public static final String RESULTSIMG_RSC = "stealTheRuby/resource/resultsScreen.png";
	
	
	public void loadTextures() {
		
		ResourceManager.loadImage(RESULTSIMG_RSC);
		
	}
	
	private float timeTaken;
	private int coinsGot;
	private boolean spotted;
	private int attempts;
	
	private boolean readyToProgress;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		loadTextures();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		MainGame mg = (MainGame)game;
		Image SplashImage = ResourceManager.getImage(RESULTSIMG_RSC);
		SplashImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashImage,
				0, 0, mg.ScreenWidth, mg.ScreenHeight,0, 0,400,300 );
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		MainGame mg = (MainGame)game;
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				if(mg.currentLevel==Levels.lastLevel) {
					//TODO completed last level
				}else {
					//there are still more levels
					game.enterState(MainGame.LOADINGSTATE );	
				}
			}
		}else {
			readyToProgress = true;
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return MainGame.RESULTSSTATE;
	}

}
