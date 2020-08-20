package num6ers.optionScreen;

import num6ers.basic.GameGlobals;

public class EndFieldConstants implements FieldConstants {
	
	private static final int width 	= GameGlobals.OPTION_FIELD_WIDTH;
	private static final int height = GameGlobals.OPTION_FIELD_HEIGHT;
	private static final int y		= GameGlobals.OPTION_VIEW_TOPGAP + 2*height + 3*GameGlobals.OPTION_FIELD_GAP;
	
	public EndFieldConstants() {
	}
	
	@Override
	public int getFieldX() {
		return GameGlobals.OPTION_VIEW_SIDEGAP;
	}
	@Override
	public int getFieldY() {
		return y;
	}
	@Override
	public int getFieldWidth() {
		return width;
	}
	@Override
	public int getFieldHeight() {
		return height;
	}
	@Override
	public String getDrawingText() {
		return "Ende";
	}
}