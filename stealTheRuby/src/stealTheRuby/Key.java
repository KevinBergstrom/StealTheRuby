package stealTheRuby;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;
import jig.Vector;

public class Key extends Item{

	private Color color;
	
	public Key(float x, float y, Color col) {
		super(x, y, false);
		
		color = col;
		
		Image newImage = ResourceManager.getImage(Item.KEYIMG_RSC).getScaledCopy(32, 32);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setImageColor(col.r, col.g, col.b);
		addImageWithBoundingBox(newImage);
	}
	
	@Override
	public void pickup(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		mg.player.addItem(this);
		removeThis(game);
	}
	
	@Override
	public void use(StateBasedGame game) {
		MainGame mg = (MainGame)game;
		Vector gridPos = mg.map.getGridPos(mg.player.getX(), mg.player.getY());
		Item p1 = mg.map.getItemAtPoint((int)gridPos.getX(),(int)gridPos.getY()-1);
		Item p2 = mg.map.getItemAtPoint((int)gridPos.getX()-1,(int)gridPos.getY());
		Item p3 = mg.map.getItemAtPoint((int)gridPos.getX()+1,(int)gridPos.getY());
		Item p4 = mg.map.getItemAtPoint((int)gridPos.getX(),(int)gridPos.getY()+1);
		
		if(p1!=null && p1.unlock(color, game)) {
			mg.player.removeItem();
			return;
		}else if(p2!=null && p2.unlock(color, game)) {
			mg.player.removeItem();
			return;
		}else if(p3!=null && p3.unlock(color, game)) {
			mg.player.removeItem();
			return;
		}else if(p4!=null && p4.unlock(color, game)) {
			mg.player.removeItem();
			return;
		}
	}

}
