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

public abstract class TextNumberLabel extends GraphicObject {
	
	private int width;
	private int height;
	
	private 	Label	lblText;
	protected 	Label	lblNumber;
	
	
	public TextNumberLabel(Font font, String text, int x) {
		this.x = x;
		this.y = GameGlobals.GAME_LABEL_Y;
		
		int textHeight = font.getLineHeight();
		height		= 2 * textHeight;
		width		= GameGlobals.GAME_LABEL_WIDTH;
		int right	= x + width;
		lblText	= new Label(x + 5, y, text, font, Label.LEFT);
		lblNumber = new Label(right - 5, y + textHeight, "", font, Label.RIGHT);
		
		collisionMask = new Rectangle(x, y, width, height);
	}
	
	public int getWidth() {
		return width;
	}
	

	@Override
	public void draw(Graphics g) {
		g.setColor(GameColor.LABEL);
		g.fillRect(x, y, width, height);
		g.setColor(GameColor.BASIS_WRITTING);
		g.drawRect(x, y, width, height);
		
		lblText.draw(g);
		lblNumber.draw(g);
	}

	@Override
	public abstract void update(InputState inputState, int delta);
	
}
