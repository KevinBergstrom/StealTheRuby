package stealTheRuby;

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
import jig.Vector;

public class PlayingState extends BasicGameState{
	
	public static final String RUBYCASEIMG_RSC = "stealTheRuby/resource/rubyCase.png";
	public static final String INFOGUIIMG_RSC = "stealTheRuby/resource/testGUI.png";
	public static final String ITEMGUIIMG_RSC = "stealTheRuby/resource/testInv.png";
	public static final String UNSEENIMG_RSC = "stealTheRuby/resource/unseenTitle.png";
	public static final String CALMIMG_RSC = "stealTheRuby/resource/calmTitle.png";
	public static final String ALERTIMG_RSC = "stealTheRuby/resource/alertTitle.png";
	
	
	public void loadTextures() {
		
		ResourceManager.loadImage(RUBYCASEIMG_RSC);
		ResourceManager.loadImage(INFOGUIIMG_RSC);
		ResourceManager.loadImage(ITEMGUIIMG_RSC);
		ResourceManager.loadImage(UNSEENIMG_RSC);
		ResourceManager.loadImage(CALMIMG_RSC);
		ResourceManager.loadImage(ALERTIMG_RSC);
		
	}
	
	private Tile rubyCase;
	private Tile bigRuby;
	private Tile itemGUI;
	private Tile unseenTitle;
	private Tile calmTitle;
	private Tile alertTitle;
	private float timer;
	private int seconds;
	private int minutes;
	private boolean spotted;
	private int attempts;
	
	private boolean GODMODE;
	
	private boolean itemScrollDebounce;
	private boolean itemUseDebounce;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		loadTextures();
		
		rubyCase = new Tile(52,460,74,76,false,RUBYCASEIMG_RSC);
		bigRuby = new Tile(52,467,64,64,false,SplashState.BIGRUBYIMG_RSC);
		itemGUI = new Tile(399,519,280,64,true,ITEMGUIIMG_RSC);
		unseenTitle = new Tile(400,576,176,27,false,UNSEENIMG_RSC);
		calmTitle = new Tile(400,576,176,27,false,CALMIMG_RSC);
		alertTitle = new Tile(400,576,176,27,false,ALERTIMG_RSC);
		GODMODE = false;
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		bigRuby.setSolid(false);
		timer = 0;
		seconds = 0;
		minutes = 0;
		spotted = false;
		attempts = 1;
		itemScrollDebounce = false;
		itemUseDebounce = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		MainGame mg = (MainGame)game;
		
		//render everything
		mg.map.render(container, game, g);
		mg.player.render(g);
		
		//render gui
		if(bigRuby.getSolid()) {
			//ruby will only show if solid
			bigRuby.render(g);
		}
		rubyCase.render(g);
		itemGUI.render(g);
	 	renderInventory(game, g);
		
		Image infoGUI = ResourceManager.getImage(INFOGUIIMG_RSC);
		infoGUI.setFilter(Image.FILTER_NEAREST);
		g.drawImage(infoGUI,
				0, 499, mg.ScreenWidth, mg.ScreenHeight,0, 0,800,101 );
		
		if(!spotted) {
			unseenTitle.render(g);
		}else if(mg.map.getAlertTimer()>0) {
			alertTitle.render(g);
		}else {
			calmTitle.render(g);
		}
		
		Item[] items = mg.player.getSelectedItems();
		if(items[1]!=null) {
			g.drawString(""+items[1].getName(), 550, 526);
		}
		
		
		//TODO update these
		g.drawString("Level: "+mg.map.getMapName(), 17, 513);
		g.drawString("Coins: " + mg.player.getCoins(), 17, 543);
		g.drawString("Lives: " + mg.player.getLives(), 17, 574);
		
		String secondsText = "";
		String minutesText = "";
		
		if(seconds<10) {
			secondsText = "0"+seconds;
		}else {
			secondsText = ""+seconds;
		}
		if(minutes<10) {
			minutesText = "0"+minutes;
		}else {
			minutesText = ""+minutes;
		}
		
		g.drawString(minutesText+":"+secondsText, 570, 555);
		
		for(int i = 0;i<mg.collectAnims.size();i++) {
			mg.collectAnims.get(i).render(g);
		}
		
		if(mg.DEBUG) {
			g.drawString("DEBUG MODE", 10, 30);
		}
		if(GODMODE) {
			g.drawString("GODMODE", 10, 50);
		}
		
	}
	
	public void renderInventory(StateBasedGame game, Graphics g) {
		MainGame mg = (MainGame)game;
		Item[] items = mg.player.getSelectedItems();
		for(int i = 0;i<items.length;i++) {
			if(items[i]!=null) {
				items[i].setPosition(315+(i*84), 519);
				items[i].render(g);
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if(delta>100) {
			//ignore large deltas
			return;
		}
		
		Input input = container.getInput();
		MainGame mg = (MainGame)game;
		
		if(mg.map.getFrozen()<=0) {
			timer += delta;
			if(timer>1000) {
				seconds++;
				timer = 0;
			}
			if(seconds>60) {
				minutes++;
				seconds = 0;
			}
			if(minutes>99) {
				minutes = 99;
				seconds = 59;
			}
		}
		
		mg.player.setVelocity(new Vector(0, 0));

		//cheats
		if (input.isKeyDown(Input.KEY_F12)) {
			mg.player.setEscaped(true);
		}
		if (input.isKeyDown(Input.KEY_F1)) {
			mg.DEBUG = true;
		}
		if (input.isKeyDown(Input.KEY_F2)) {
			GODMODE = true;
		}
		
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
		
		if (input.isKeyDown(Input.KEY_E)) {
			if(!itemScrollDebounce) {
				mg.player.itemScroll(true);
				itemScrollDebounce = true;
			}
		}else if (input.isKeyDown(Input.KEY_Q)) {
			if(!itemScrollDebounce) {
				mg.player.itemScroll(false);
				itemScrollDebounce = true;
			}
		}else{
			itemScrollDebounce = false;
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(!itemUseDebounce) {
				mg.player.useItem(game);
				itemUseDebounce = true;
			}
		}else {
			itemUseDebounce = false;
		}
		
		//update everything
		mg.player.update(delta);
		mg.player.keepInBounds(0, mg.ScreenWidth, 0, mg.ScreenHeight-100);
		mg.player.collideWithItems(mg);
		if(!GODMODE) {
			mg.player.collideWithTraps(mg);
			mg.player.collideWithMap(mg.map);
		}
		
		mg.map.updateGuards(delta, game);
		
		if(mg.map.getFrozen()<=0) {
			for(int i = 0;i<mg.collectAnims.size();i++) {
				ProjectileImage next = mg.collectAnims.get(i);
				next.update(delta);
				if(next.getFinished()) {
					if(next.getType()==2) {
						//ruby
						bigRuby.setSolid(true);
					}
					mg.collectAnims.remove(i);
					i--;
				}
			}
		}
		
		if(mg.player.getEscaped()) {
			updatePlayerScore(game);
			mg.enterState(MainGame.RESULTSSTATE);
		}
		if(!GODMODE) {
			if(mg.map.getFrozen()<=0) {
				mg.map.collideWithCameras(mg.player);
				int guardCollide = mg.map.collideWithGuards(mg.player);
				if(guardCollide==2) {
					//player got caught
					mg.player.subLives();
					
					if(mg.player.getLives()<=0) {
						//GAME OVER
						game.enterState(MainGame.GAMEOVERSTATE, new EmptyTransition() , new VerticalSplitTransition());
					}else {
						reloadLevel(game);
						mg.player.reset();
					}
				}else if(guardCollide==1) {
					spotted = true;
				}
				
			}
		}
		
		
	}
	
	public void reloadLevel(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Levels.loadLevel(mg.currentLevel, game);
		timer = 0;
		seconds = 0;
		minutes = 0;
		spotted = false;
		attempts++;
		bigRuby.setSolid(false);
		
	}
	
	public void updatePlayerScore(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.totalCoins += mg.player.getCoins();
		mg.player.minutesScore = minutes;
		mg.player.secondsScore = seconds;
		mg.player.spottedScore = spotted;
		mg.player.attemptsScore = attempts;
		
	}
	
	//public 

	@Override
	public int getID() {
		return MainGame.PLAYINGSTATE;
	}

}
