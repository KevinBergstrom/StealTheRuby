package stealTheRuby;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

public class Lock extends Item{

	private Color color;
	
	public Lock(float x, float y, Color col) {
		super(x, y, true);
		
		color = col;
		setImageWithColor(Item.LOCKIMG_RSC,32,32,col);
	}
	
	@Override
	public boolean unlock(Color c, StateBasedGame game) {
		if (c.r == color.r && c.g == color.g && c.b == color.b) {
			removeThis(game);
			return true;
		}else {
			return false;
		}
	}

}
