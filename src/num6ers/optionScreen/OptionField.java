package num6ers.optionScreen;

import num6ers.basic.GameColor;
import num6ers.basic.InputState;
import num6ers.basic.graphics.GraphicObject;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * button in a game
 * @author tommy
 *
 */

public class OptionField extends GraphicObject {
	
	private int width, height, textX, textY;
	private boolean mouseEntered = false;
	
	private Font   	font;
	private String	text;
	
	private ClickBehaviour clickBehaviour;
//	private FieldConstants fieldConstants;
	
	
	public OptionField(ClickBehaviour clickBehaviour, FieldConstants fieldConstants, Font font) {
		this.clickBehaviour = clickBehaviour;
//		this.fieldConstants = fieldConstants;
		
		this.font = font;
		this.text = fieldConstants.getDrawingText();
		
		this.width  = fieldConstants.getFieldWidth();
		this.height = fieldConstants.getFieldHeight();
		this.x = fieldConstants.getFieldX();
		this.y = fieldConstants.getFieldY();
		this.textY = y + (height - font.getLineHeight()) / 2;
		this.textX = x + (width - font.getWidth(text)) / 2;
		
		collisionMask = new Rectangle(x, y, width, height);
	}


	@Override
	public void draw(Graphics g) {
		if(mouseEntered) {
			g.setColor(GameColor.OPTIONFIELD_ENTERED);
		}
		else {
			g.setColor(GameColor.OPTIONFIELD_NORMAL);
		}

		g.fillRect(x, y, width, height);
		g.setColor(GameColor.BASIS_WRITTING);
		g.drawRect(x, y, width, height);
		
		g.setFont(font);
		g.drawString(text, textX, textY);
	}

	@Override
	public void update(InputState inputState, int delta) {
		
		int mouseX = inputState.getMouseX();
		int mouseY = inputState.getMouseY();
		
		mouseEntered = super.isCollision(mouseX, mouseY);
		boolean mouseClicked = inputState.mouseButtonLeft.isClicked();
		boolean mouseRightClicked = inputState.mouseButtonRight.isClicked();
		
		
		if(mouseEntered) {
			if(mouseClicked) {
				clickBehaviour.leftClicked();
			}
			else if(mouseRightClicked) {
				clickBehaviour.rightClicked();
			}
		}
	}

}
