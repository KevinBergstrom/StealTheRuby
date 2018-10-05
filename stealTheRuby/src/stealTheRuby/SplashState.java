package stealTheRuby;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class SplashState extends BasicGameState{

	public static final String BIGRUBYIMG_RSC = "stealTheRuby/resource/bigRuby.png";
	public static final String SPLASHBGIMG_RSC = "stealTheRuby/resource/splashBG.png";
	public static final String SPLASHHELIIMG_RSC = "stealTheRuby/resource/splashHeli.png";
	public static final String SPLASHRATIMG_RSC = "stealTheRuby/resource/splashRat.png";
	public static final String SPLASHTITLEIMG_RSC = "stealTheRuby/resource/splashTitle.png";
	public static final String SPLASHLIGHT_RSC = "stealTheRuby/resource/splashLight.png";
	
	
	public void loadTextures() {
		
		ResourceManager.loadImage(BIGRUBYIMG_RSC);
		ResourceManager.loadImage(SPLASHBGIMG_RSC);
		ResourceManager.loadImage(SPLASHHELIIMG_RSC);
		ResourceManager.loadImage(SPLASHRATIMG_RSC);
		ResourceManager.loadImage(SPLASHTITLEIMG_RSC);
		ResourceManager.loadImage(SPLASHLIGHT_RSC);
		
	}
	
	private boolean readyToProgress;
	
	private Tile ruby;
	private Tile rat;
	private Tile heli;
	private Tile light;
	
	private double timer;
	private float flickerDebounce;
	private float maxFlickerTimer;
	private float flickerTimer;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		//load stuff
		loadTextures();
		
		maxFlickerTimer = 3000;
		flickerTimer = maxFlickerTimer;
		flickerDebounce = 60;
		
		ruby = new Tile(198*2,185*2,128,128,false,BIGRUBYIMG_RSC);
		light = new Tile(395,183*2,163*2,158*2,true,SPLASHLIGHT_RSC);
		rat = new Tile(351*2,258*2,23*2,8*2,false,SPLASHRATIMG_RSC);
		heli = new Tile(19*2,150*2,14*2,8*2,false,SPLASHHELIIMG_RSC);
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		//container.setSoundOn(false);
		readyToProgress = false;
		timer = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		MainGame mg = (MainGame)game;
		
		Image SplashBG = ResourceManager.getImage(SPLASHBGIMG_RSC);
		SplashBG.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashBG,
				0, 95*2, mg.ScreenWidth, mg.ScreenHeight- 38*2,0, 0,400,167 );
		
		heli.render(g);
		rat.render(g);
		
		Image SplashImage = ResourceManager.getImage(SPLASHTITLEIMG_RSC);
		SplashImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashImage,
				0, 0, mg.ScreenWidth, mg.ScreenHeight,0, 0,400,300 );
		
		ruby.render(g);
		if(light.getSolid()) {
			light.render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		MainGame mg = (MainGame)game;
		
		timer += delta;
		flickerTimer -= delta;
		
		ruby.setPosition(198*2,(185*2)+(float)Math.sin(timer/500)*6);
		

		if(flickerTimer<=0) {
			if(light.getSolid()) {
				//on
				flickerTimer = flickerDebounce;
				light.setSolid(false);
			}else {
				//off
				if(Math.random()>0.65) {
					//more than one flicker
					flickerTimer = flickerDebounce;
				}else {
					flickerTimer = maxFlickerTimer + (float)Math.random()*4500;
				}
				light.setSolid(true);
			}
		}

		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				mg.enterState(MainGame.PLAYINGSTATE);
			}
		}else {
			readyToProgress = true;
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
