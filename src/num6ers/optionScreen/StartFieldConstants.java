package num6ers.optionScreen;

import num6ers.basic.GameGlobals;

public class StartFieldConstants implements FieldConstants {
	
	private static final int width 	= GameGlobals.OPTION_FIELD_WIDTH;
	private static final int height = GameGlobals.OPTION_FIELD_HEIGHT;
	
	public StartFieldConstants() {
	}
	
	@Override
	public int getFieldX() {
		return GameGlobals.OPTION_VIEW_SIDEGAP;
	}

	@Override
	public int getFieldY() {
		return GameGlobals.OPTION_VIEW_TOPGAP;
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
		return "Start";
	}

}
