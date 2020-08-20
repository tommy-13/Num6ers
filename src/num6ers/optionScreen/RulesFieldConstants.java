package num6ers.optionScreen;

import num6ers.basic.GameGlobals;

public class RulesFieldConstants implements FieldConstants {
	
	private static final int width = GameGlobals.OPTION_FIELD_WIDTH;
	private static final int height = GameGlobals.OPTION_FIELD_HEIGHT;
	
	
	public RulesFieldConstants() {
	}
	
	
	@Override
	public int getFieldX() {
		return GameGlobals.OPTION_VIEW_SIDEGAP;
	}

	@Override
	public int getFieldY() {
		return GameGlobals.OPTION_VIEW_TOPGAP + height + GameGlobals.OPTION_FIELD_GAP;
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
		return "Regeln";
	}

}
