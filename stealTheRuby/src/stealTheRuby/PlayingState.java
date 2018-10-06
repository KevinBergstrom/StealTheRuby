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
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		loadTextures();
		
		rubyCase = new Tile(52,460,74,76,false,RUBYCASEIMG_RSC);
		bigRuby = new Tile(52,467,64,64,false,SplashState.BIGRUBYIMG_RSC);
		itemGUI = new Tile(399,519,280,64,true,ITEMGUIIMG_RSC);
		timer = 0;
		seconds = 0;
		minutes = 0;
		
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
		
		Image infoGUI = ResourceManager.getImage(INFOGUIIMG_RSC);
		infoGUI.setFilter(Image.FILTER_NEAREST);
		g.drawImage(infoGUI,
				0, 499, mg.ScreenWidth, mg.ScreenHeight,0, 0,800,101 );
		
		//TODO update these
		g.drawString("Level: TESTING LEVEL", 17, 513);
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
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			mg.player.useItem(game);
		}
		
		//update everything
		mg.player.update(delta);
		mg.player.keepInBounds(0, mg.ScreenWidth, 0, mg.ScreenHeight-100);
		mg.player.collideWithItems(mg);
		mg.player.collideWithMap(mg.map);
		
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

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
