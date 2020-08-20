package num6ers.basic;

import java.io.File;

import num6ers.game.GameView;
import num6ers.game.StateOfGame;
import num6ers.optionScreen.OptionView;
import num6ers.rulesScreen.RulesView;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NumbersStarter extends StateBasedGame {
	
	private final static String gameName = "Num6ers";

	
	public NumbersStarter() throws SlickException {
		super(gameName);

		/* create folder if necessary */
		new File("safe").mkdir();
		
		GameGlobals.setConfiguration();
		StateOfGame.getInstance().load();
		
		/* add states (screens) */
		this.addState(new OptionView());
		this.addState(new GameView());
		this.addState(new RulesView());
	}
	

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		gameContainer.setShowFPS(false);
		
		/* initialise screens */
		this.getState(GameWindow.GAME).init(gameContainer, this);
		this.getState(GameWindow.OPTION_SCREEN).init(gameContainer, this);
		this.getState(GameWindow.RULES).init(gameContainer, this);
		
		/* set first screen */
		this.enterState(GameWindow.OPTION_SCREEN);
	}

	
	
	public static void main(String[] args) {
		
		/* create window */
		AppGameContainer appGameContainer;
		try {
			appGameContainer = new AppGameContainer(new NumbersStarter());
			appGameContainer.setIcon("res/spr/icon32.png");
			appGameContainer.setDisplayMode(GameGlobals.SCREEN_WIDTH, GameGlobals.SCREEN_HEIGHT, false);
			appGameContainer.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
}
