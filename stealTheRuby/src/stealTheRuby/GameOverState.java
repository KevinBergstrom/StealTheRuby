package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

import jig.ResourceManager;

public class GameOverState extends BasicGameState {

	public static final String GAMEOVERTITLEIMG_RSC = "stealTheRuby/resource/gameOverTitle.png";
	public static final String GAMEOVERBARSIMG_RSC = "stealTheRuby/resource/gameOverBars.png";
	
	public static void loadTextures() {
		
		ResourceManager.loadImage(GAMEOVERTITLEIMG_RSC);
		ResourceManager.loadImage(GAMEOVERBARSIMG_RSC);
		
	}
	
	private float timer;
	private float rubyTimer;
	private int state;
	
	//private Tile ruby;
	private ArrayList<Tile> rubies;
	private Tile bars;
	private float speed;
	private float acceleration;
	private float rubySpeed;
	private int bounces;
	
	private boolean readyToProgress;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		MainGame mg = (MainGame)game;
		loadTextures();
		bars = new Tile(-234*2, 178*2 + 1, 234*2, 167*2, false, GAMEOVERBARSIMG_RSC);
		rubies = new ArrayList<Tile>();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		timer = 10000f;
		state = 0;
		speed = 0.05f;
		acceleration = 0.005f;
		rubyTimer = 0;
		rubySpeed = 0.05f;
		bars.setPosition(-234, 178*2 + 1);
		rubies.clear();
		readyToProgress = false;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		MainGame mg = (MainGame)game;
		
		Image GBG = ResourceManager.getImage(GAMEOVERTITLEIMG_RSC);
		GBG.setFilter(Image.FILTER_NEAREST);
		g.drawImage(GBG,
				0, 0, mg.ScreenWidth, mg.ScreenHeight,0, 0,400,300 );
		for(int i = 0;i<rubies.size();i++) {
			rubies.get(i).render(g);
		}
		bars.render(g);
		
		g.drawString(""+mg.score, 202*2, 277*2);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Input input = container.getInput();
		MainGame mg = (MainGame)game;
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				timer = 0;
				state = 2;
			}
		}else {
			readyToProgress = true;
		}
		
		if(state == 0) {
			//bars closing
			speed += acceleration;
			bars.setX(bars.getX()+(delta*speed));
			if(bars.getX()>200*2) {
				if(bounces>=6) {
					bars.setX(200*2);
					//spawn all of the acquired rubies
					int missingRubies = mg.currentLevel -1;
					for(int i = 0;i<missingRubies;i++) {
						rubies.add(new Tile(mg.ScreenWidth + 64 + i*164,185*2,128,128,false,SplashState.BIGRUBYIMG_RSC));
					}
					//ruby.setX(mg.ScreenWidth+128);
					state = 1;
				}else {
					bars.setX(200*2);
					bounces++;
					speed = speed * -1/3;
					acceleration = (float) (acceleration*0.75);
				}
			}
		}else if(state == 1) {
			//ruby leaving
			rubyTimer += delta;
			for(int i = 0;i<rubies.size();i++) {
				Tile curRuby = rubies.get(i);
				curRuby.setPosition(curRuby.getX()-(delta*rubySpeed),(185*2)+(float)Math.sin((rubyTimer/500)+(i*100))*6);
			}
			if(rubies.isEmpty()) {
				timer = 10000;
				state = 2;
			}else if(rubies.get(rubies.size()-1).getCoarseGrainedMaxX()<0) {
				timer = 10000;
				state = 2;
			}
		}else if(state >= 2) {
			//ruby left
			timer -= delta;
			if (timer <= 0) {
				game.enterState(MainGame.SPLASHSTATE, new EmptyTransition(), new VerticalSplitTransition() );
			}
		}
		
	}

	@Override
	public int getID() {
		return MainGame.GAMEOVERSTATE;
	}

}
