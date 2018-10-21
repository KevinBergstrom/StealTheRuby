package stealTheRuby;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;

import jig.ResourceManager;
import jig.Vector;

public class WinState extends BasicGameState{

	public static final String WINIMG_RSC = "stealTheRuby/resource/winScreen.png";
	public static final String SHAKEIMG_RSC = "stealTheRuby/resource/strawberryShake.png";
	public static final String FOXIMG_RSC = "stealTheRuby/resource/fox.png";
	
	
	public void loadTextures() {
		
		ResourceManager.loadImage(WINIMG_RSC);
		ResourceManager.loadImage(SHAKEIMG_RSC);
		ResourceManager.loadImage(FOXIMG_RSC);
	}
	
	private boolean readyToProgress;
	private int state;
	private ArrayList<Tile> rubies;
	private float timer;
	private float rubyDistance;
	private float centerX;
	private float centerY;
	private float difRad;
	private int solids;
	private Tile shake;
	private ArrayList<String> credits;
	private float scrollSpeed;
	private float creditDist;
	private float creditsOffset;
	private boolean creditsFinished;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		loadTextures();
		rubies = new ArrayList<Tile>();
		credits = new ArrayList<String>();
		populateCredits();
		scrollSpeed = 0.03f;
		creditDist = 100;
		centerX = 267*2;
		centerY = 164*2;
		int levels = Levels.lastLevel;
		for(int i = 0;i<levels;i++) {
			rubies.add(new Tile(-32,-32,32,32,false,Item.SMALLRUBYIMG_RSC));
		}
		
		difRad = (float)(2*Math.PI)/levels;
		
		shake = new Tile(centerX,175*2,70*2,100*2,false,SHAKEIMG_RSC);
	}
	
	public void populateCredits() {
		credits.add("Steal The Ruby");
		credits.add("Credits");
		credits.add("Programming Lead\n-Kevin Bergstrom");
		credits.add("Art Lead\n-Kevin Bergstrom");
		credits.add("Creative Lead\n-Kevin Bergstrom");
		credits.add("Testing\n-Kevin Bergstrom");
		credits.add("Level Design\n-Kevin Bergstrom");
		credits.add("Engine\n-jig");
		credits.add("Skeleton Code\n-Scott Wallace");
		credits.add("Class\n-CS 447");
		credits.add("Thanks for playing!");
		credits.add("See you in my next game!");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		timer = 0;
		state = 0;
		rubyDistance = 120;
		creditsOffset = 0;
		creditsFinished = false;
		solids = 0;
		for(int i = 0;i<rubies.size();i++) {
			rubies.get(i).setSolid(false);
		}
		shake.setSolid(false);
		readyToProgress = false;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		MainGame mg = (MainGame)game;
		Image winBG = ResourceManager.getImage(WINIMG_RSC);
		winBG.setFilter(Image.FILTER_NEAREST);
		g.drawImage(winBG,
				0, 0, mg.ScreenWidth, mg.ScreenHeight,0, 0,400,300 );
		
		g.drawString(""+mg.score, 244*2, 264*2);
		
		for(int i = 0;i<rubies.size();i++) {
			if(rubies.get(i).getSolid()) {
				rubies.get(i).render(g);
			}
		}
		
		if(shake.getSolid()) {
			shake.render(g);
		}
		
		if(!creditsFinished) {
			for(int i = 0;i<credits.size();i++) {
				float yPosition =mg.ScreenHeight - creditsOffset + (i*creditDist);
				if(yPosition>-30 && yPosition<mg.ScreenHeight+30) {
					g.drawString(""+credits.get(i), 32, yPosition);
				}else {
					
				}
			}
		}
		
		if(!creditsFinished && mg.ScreenHeight - creditsOffset + (credits.size()*creditDist)<0) {
			creditsFinished = true;
		}
		
		if(mg.totalCoins>=Levels.totalCoins) {
			//secret (shhhhh!)
			Image foxIMG = ResourceManager.getImage(FOXIMG_RSC);
			foxIMG.setAlpha((float) (Math.sin(timer/1000)));
			foxIMG.setFilter(Image.FILTER_NEAREST);
			g.drawImage(foxIMG, 229*2, 129*2, (229*2)+128, (129*2)+128,0, 0,64,64 );
		}
	}
	
	public Vector angleToVector(float rad) {
		return new Vector((float)Math.cos(rad),(float)Math.sin(rad));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(state!=3) {
			timer+= delta;
		}
		
		if(!creditsFinished) {
			creditsOffset += scrollSpeed*delta;
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				//go to splash state
				game.enterState(MainGame.SPLASHSTATE, new EmptyTransition(), new BlobbyTransition() );
			}
		}else {
			readyToProgress = true;
		}
		
		if(state == 0) {
			for(int i = 0;i<rubies.size();i++) {
				Tile curRuby = rubies.get(i);
				Vector difPos = angleToVector(difRad*i + (timer/1000));
				curRuby.setPosition(centerX + difPos.getX()*rubyDistance, centerY + difPos.getY()*rubyDistance );
				if(!curRuby.getSolid() && difPos.getX()>0 && difPos.getX()<0.5 && difPos.getY()<0) {
					curRuby.setSolid(true);
					solids++;
				}
			}
			if(solids>=rubies.size()) {
				state = 1;
			}
		}else if(state == 1) {
			
			for(int i = 0;i<rubies.size();i++) {
				Tile curRuby = rubies.get(i);
				Vector difPos = angleToVector(difRad*i + (timer/1000));
				curRuby.setPosition(centerX + difPos.getX()*rubyDistance, centerY + difPos.getY()*rubyDistance );
				
			}
			if(creditsFinished) {
					state = 2;
			}else {
				rubyDistance = rubyDistance +(float) Math.sin(timer/500);
			}
		}else if(state == 2) {
			for(int i = 0;i<rubies.size();i++) {
				Tile curRuby = rubies.get(i);
				Vector difPos = angleToVector(difRad*i + (timer/1000));
				curRuby.setPosition(centerX + difPos.getX()*rubyDistance, centerY + difPos.getY()*rubyDistance );
				
			}
			rubyDistance = rubyDistance - 0.05f*delta;
			if(rubyDistance<0) {
				rubyDistance = 0;
				state = 3;
				shake.setSolid(true);
				for(int i = 0;i<rubies.size();i++) {
					rubies.get(i).setSolid(false);
				}
			}
		}

		
	}

	@Override
	public int getID() {
		return MainGame.WINSTATE;
	}

}
