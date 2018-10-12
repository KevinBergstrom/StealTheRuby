package stealTheRuby;

import java.util.ArrayList;

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
	
	private int coinScore;
	
	private int minutesTaken;
	private int secondsTaken;
	private int coinsGot;
	private boolean spotted;
	private int attempts;
	private int score;
	
	private String timeStr;
	private String spottedStr;
	private String attemptsStr;
	
	private int state;
	private float debounce;
	private ArrayList<Tile> images;
	private int ivalue;
	
	private boolean readyToProgress;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		loadTextures();
		images = new ArrayList<Tile>();
		coinScore = 1000;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		MainGame mg =(MainGame)game;
		state = 0;
		debounce = 500;
		ivalue = 0;
		score = 0;
		images.clear();
		
		timeStr = "";
		spottedStr = "";
		
		minutesTaken = mg.player.minutesScore;
		secondsTaken = mg.player.secondsScore;
		coinsGot = mg.player.getCoins();
		spotted = mg.player.spottedScore;
		attempts = mg.player.attemptsScore;
		attempts = 1;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		MainGame mg = (MainGame)game;
		Image SplashImage = ResourceManager.getImage(RESULTSIMG_RSC);
		SplashImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashImage,
				0, 0, mg.ScreenWidth, mg.ScreenHeight,0, 0,400,300 );

		g.drawString(timeStr, 82*2, 82*2);
		g.drawString(spottedStr, 110*2, 146*2);
		g.drawString(""+score, 87*2, 265*2);
		
		for(int i = 0;i<images.size();i++) {
			images.get(i).render(g);
		}
		
	}
	
	public void scoreTime() {
		String secondsText = "";
		String minutesText = "";
		
		if(secondsTaken<10) {
			secondsText = "0"+secondsTaken;
		}else {
			secondsText = ""+secondsTaken;
		}
		if(minutesTaken<10) {
			minutesText = "0"+minutesTaken;
		}else {
			minutesText = ""+minutesTaken;
		}
		
		timeStr = ""+minutesText+":"+secondsText;
		
		if(minutesTaken == 0) {
			score += 20000;
		}else if(minutesTaken < 2) {
			score += 7500;
		}else if(minutesTaken < 5) {
			score += 5000;
		}
		
	}
	
	public void scoreCoin() {
		score += coinScore;
		images.add(new Tile(88*2 + ivalue*10,118*2,64,64,false,Item.COINIMG_RSC));
		coinsGot--;
		ivalue++;
	}
	
	public void scoreSpotted() {
		if(!spotted) {
			score += 20000;
			spottedStr = "No";
		}else {
			spottedStr = "Yes";
		}
	}
	
	public void scoreAttempt() {
		score += 5000;
		//TODO update bike image color so its doesn't look black
		images.add(new Tile(147*2 + ivalue*96,186*2,96,64,false,Item.BIKEIMG_RSC));
		attempts--;
		ivalue++;
	}
	
	public void scoreRuby() {
		score += 5000;
		images.add(new Tile(97*2,220*2,64,64,false,Item.SMALLRUBYIMG_RSC));
	}
	
	public void skipToEnd() {
		if(state < 1) {
			scoreTime();
		}
		if(state < 2) {
			while(coinsGot>0) {
				scoreCoin();
			}
		}
		if(state < 3) {
			scoreSpotted();
		}
		if(state < 4) {
			ivalue = 0;
			while(attempts>0) {
				scoreAttempt();
			}
		}
		if(state < 5) {
			scoreRuby();
		}
		
		state = 5;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		MainGame mg = (MainGame)game;
		
		if(debounce <=0) {
			if(state == 0) {
				debounce = 500;
				scoreTime();
				state = 1;
			}else if(state == 1) {
				debounce = 150;
				if(coinsGot>0) {
					scoreCoin();
				}else {
					debounce = 500;
					state = 2;
					ivalue=0;
				}
			}else if(state == 2){
				debounce = 500;
				scoreSpotted();
				state = 3;
			}else if(state == 3) {
				debounce = 300;
				if(attempts>0) {
					scoreAttempt();
				}else {
					debounce = 500;
					state = 4;
					ivalue=0;
				}
			}else if(state == 4) {
				scoreRuby();
				state = 5;
			}
		}else {
			debounce -=delta;
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(state<5) {
				skipToEnd();
				readyToProgress = false;
			}else if(readyToProgress) {
				//TODO fast forward with correct scoring
				if(mg.currentLevel==Levels.lastLevel) {
					//TODO completed last level
					mg.score += score;
				}else {
					//there are still more levels
					mg.score += score;
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
