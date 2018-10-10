package stealTheRuby;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;
import jig.Vector;

public class PlayingState extends BasicGameState{
	
	public static final String RUBYCASEIMG_RSC = "stealTheRuby/resource/rubyCase.png";
	public static final String INFOGUIIMG_RSC = "stealTheRuby/resource/testGUI.png";
	public static final String ITEMGUIIMG_RSC = "stealTheRuby/resource/testInv.png";
	
	
	public void loadTextures() {
		
		ResourceManager.loadImage(RUBYCASEIMG_RSC);
		ResourceManager.loadImage(INFOGUIIMG_RSC);
		ResourceManager.loadImage(ITEMGUIIMG_RSC);
		
	}
	
	private Tile rubyCase;
	private Tile bigRuby;
	private Tile itemGUI;
	private float timer;
	private int seconds;
	private int minutes;
	private float scrollCooldown;
	private float scrollTimer;
	private boolean spotted;
	private int attempts;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		loadTextures();
		
		rubyCase = new Tile(52,460,74,76,false,RUBYCASEIMG_RSC);
		bigRuby = new Tile(52,467,64,64,false,SplashState.BIGRUBYIMG_RSC);
		itemGUI = new Tile(399,519,280,64,true,ITEMGUIIMG_RSC);
		timer = 0;
		seconds = 0;
		minutes = 0;
		scrollCooldown = 300;
		scrollTimer = 0;
		spotted = false;
		attempts = 1;
		
		//TODO testing
		Levels.loadLevel(1, game);
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		bigRuby.setSolid(false);
		spotted = false;
		attempts = 1;
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
		
		if(scrollTimer>0) {
			scrollTimer-=delta;
		}
		
		mg.player.setVelocity(new Vector(0, 0));

		//cheats
		if (input.isKeyDown(Input.KEY_F12)) {
			mg.player.setEscaped(true);
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
		
		if(scrollTimer<=0) {
			if (input.isKeyDown(Input.KEY_E)) {
				mg.player.itemScroll(true);
				scrollTimer = scrollCooldown;
			}else if (input.isKeyDown(Input.KEY_Q)) {
				mg.player.itemScroll(false);
				scrollTimer = scrollCooldown;
			}
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			mg.player.useItem(game);
		}
		
		if (input.isKeyDown(Input.KEY_ENTER)) {
			//TODO testing
			mg.map.testGuardFollowPath(mg.player.getX(), mg.player.getY());
			mg.map.alert(10);
		}
		
		//update everything
		mg.player.update(delta);
		mg.player.keepInBounds(0, mg.ScreenWidth, 0, mg.ScreenHeight-100);
		mg.player.collideWithItems(mg);
		mg.player.collideWithMap(mg.map);
		
		mg.map.updateGuards(delta, game);
		
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
		
		if(mg.player.getEscaped()) {
			updatePlayerScore(game);
			mg.enterState(MainGame.RESULTSSTATE);
		}
		
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
