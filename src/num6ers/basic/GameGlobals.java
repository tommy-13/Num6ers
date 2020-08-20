package num6ers.basic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

public class GameGlobals {
	
	public static final int SCREEN_WIDTH		= 800;
	public static final int SCREEN_HEIGHT		= 400;
	
	public static final int OPTION_FIELD_HEIGHT = 40;
	public static final int OPTION_FIELD_WIDTH	= 300;
	public static final int OPTION_VIEW_TOPGAP	= 100;
	public static final int OPTION_VIEW_SIDEGAP	= (SCREEN_WIDTH - OPTION_FIELD_WIDTH) / 2;
	public static final int OPTION_FIELD_GAP 	= 20;

	public static final int GAME_LABEL_Y		= 30;
	public static final int GAME_LABEL_WIDTH	= 100; 
	
	public static final int	MAX_LEVEL 			= 25;
	public static final int	MAX_LETTERS 		= 18;
	
	public static final int RULES_LEFT_GAP		= 30;
	public static final int RULES_TOP_GAP		= 30;
	
	public static int ERROR_TEXT_TIME = 10000;
	
	public static Transition transitionOut() {
		return new FadeOutTransition(Color.black, 500);
	}
	public static Transition transitionIn() {
		return new FadeInTransition(Color.black, 500);
	}
	
	
	public static void setConfiguration() {
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("safe/config.properties"));
			properties.load(stream);
			stream.close();
		} catch (IOException e) {e.printStackTrace();}
		
		try {
			ERROR_TEXT_TIME = Integer.parseInt(properties.getProperty("ERRORTEXTTIME"));
		} catch(NumberFormatException e) {
			ERROR_TEXT_TIME = 8000;
		}
	}
	
}
