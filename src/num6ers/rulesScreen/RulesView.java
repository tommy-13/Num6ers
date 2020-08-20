package num6ers.rulesScreen;

import num6ers.basic.GameFont;
import num6ers.basic.GameGlobals;
import num6ers.basic.GameWindow;
import num6ers.basic.InputState;
import num6ers.basic.graphics.Label;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class RulesView extends BasicGameState implements GameState {

	private Image	background;
	private Label	wordFileNameLabel;
	private Font	fontInfo;
	
	
	public RulesView() {
		super();
	}
	

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {

		/* fonts */
		fontInfo	= new GameFont(GameFont.FONT24);
		
		/* labels */
		wordFileNameLabel	= new Label(
				GameGlobals.RULES_LEFT_GAP,
				GameGlobals.RULES_TOP_GAP,
				"Es sind mehrere Ziffern zu sehen. Jede Ziffer steht\n" +
				"f�r eine Zahl, angefangen bei 1 f�r A, 2 f�r B, usw\n" +
				"bis zu 26 f�r Z, 27 f�r �, 28 f�r � und 29 f�r �.\n" +
				"Tippe das gesuchte Wort ein. Achte dabei auf die\n" +
				"Zeit. Je l�nger das Wort und je niedriger der Level,\n" +
				"desto mehr Zeit steht dir zur Verf�gung.\n" +
				"Mit der Taste 'Backspace' l�scht du den letzten ein-\n" +
				"gegebenen Buchstaben. Die Taste 'Entf' l�scht deine\n" +
				"ganze Eingabe. Mit 'Enter' wird deine Eingabe �ber-\n" +
				"pr�ft.",
				fontInfo,
				Label.LEFT);
		
		/* background */
		background = new Image("res/img/background02.jpg");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		background.draw();
		wordFileNameLabel.draw(g);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		
		Input input = gameContainer.getInput();
		InputState inputState = InputState.getInstance();
		
		inputState.setMousePosition(input.getMouseX(), input.getMouseY());
		inputState.mouseButtonLeft.setMouseState(input.isMousePressed(Input.MOUSE_LEFT_BUTTON),
				input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON));
		inputState.mouseButtonRight.setMouseState(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON),
				input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON));
		inputState.setMouseWheel(Mouse.getDWheel());
		inputState.setKeyDown(input.isKeyPressed(Input.KEY_DOWN));
		inputState.setKeyUp(input.isKeyPressed(Input.KEY_UP));
		
		
		if(input.isKeyPressed(Input.KEY_RETURN) || input.isKeyPressed(Input.KEY_SPACE) ||
				inputState.mouseButtonLeft.isClicked() ||
				inputState.mouseButtonRight.isClicked()) {
			stateBasedGame.enterState(GameWindow.OPTION_SCREEN,
					GameGlobals.transitionOut(), GameGlobals.transitionIn());
		}
	}
	
	
	
	@Override
	public int getID() {
		return GameWindow.RULES;
	}

}
