package num6ers.game;

import num6ers.basic.GameColor;
import num6ers.basic.GameGlobals;
import num6ers.basic.InputState;
import num6ers.basic.graphics.GraphicObject;
import num6ers.basic.graphics.Label;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * button in a game
 * @author tommy
 *
 */

public class TimeLabel extends GraphicObject {
	
	private int width;
	private int height;
	private Label lblNumber;
	private int	color = 0;
	
	
	public TimeLabel(Font font, int x) {
		this.y = GameGlobals.GAME_LABEL_Y;
		
		height		= GameGlobals.GAME_LABEL_WIDTH / 2;
		width		= height;
		this.x		= x - width / 2;
		lblNumber 	= new Label(this.x - 1 + width/2, y + 9, "0", font, Label.CENTER);
		
		collisionMask = new Rectangle(this.x, y, width, height);
	}
	
	public int getWidth() {
		return width;
	}
	

	@Override
	public void draw(Graphics g) {
		switch(color) {
		case 0: default: g.setColor(GameColor.TIME_LABEL_NORMAL); break;
		case 1: g.setColor(GameColor.TIME_LABEL_MIDDLE); break;
		case 2: g.setColor(GameColor.TIME_LABEL_LAST); break;
		}
		g.fillOval(x, y, width, height);
		g.setColor(GameColor.BASIS_WRITTING);
		g.drawOval(x, y, width, height);
		
		lblNumber.draw(g);
	}

	@Override
	public void update(InputState inputState, int delta) {
		int time = StateOfGame.getInstance().getRemainingTime();
		if(time > 10) {
			color = 0;
		}
		else if(time > 5) {
			color = 1;
		}
		else {
			color = 2;
		}
		lblNumber.setText("" + time);
	}
	
}
