package num6ers.optionScreen;

import num6ers.basic.GameColor;
import num6ers.basic.GameFont;
import num6ers.basic.GameGlobals;
import num6ers.basic.GameWindow;
import num6ers.basic.InputState;
import num6ers.basic.graphics.Label;
import num6ers.game.GameView;
import num6ers.game.StateOfGame;

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

public class OptionView extends BasicGameState implements GameState {
	
	private OptionField		startLabel;
	private OptionField		rulesLabel;
	private OptionField		endLabel;
	
	/* background */
	private Image background;
	
	/* fonts */
	private Font fontInfo;
	private Font fontOptionField;
	private Font fontError;
	
	/* error */
	private Label errorLabel;
	private int   errorCounter = 0;

	
	public OptionView() {
		super();
	}

	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		
		/* background */
		background = new Image("res/img/background02.jpg");
		
		/* fonts */
		fontOptionField = new GameFont(GameFont.FONT26);
		fontError = new GameFont(GameFont.FONT24);
		
		/* fields */
		rulesLabel = new OptionField(new RulesFieldClickBehaviour(this),
				new RulesFieldConstants(), fontOptionField);
		startLabel = new OptionField(new StartFieldClickBehaviour(this),
				new StartFieldConstants(), fontOptionField);
		endLabel = new OptionField(new EndFieldClickBehaviour(this),
				new EndFieldConstants(), fontOptionField);
		
		/* error */
		errorLabel = new Label(GameGlobals.SCREEN_WIDTH/2, GameGlobals.SCREEN_HEIGHT - 36,
				"Fehler", fontError, Label.CENTER);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame,
			Graphics g) throws SlickException {
		
		/* background */
		g.setColor(GameColor.OPTIONVIEW_BACKGROUND);
		background.draw();
		
		g.setColor(GameColor.BASIS_WRITTING);

		if(fontInfo != null) {
			g.setFont(fontInfo);
		}

		startLabel.draw(g);
		rulesLabel.draw(g);
		endLabel.draw(g);

		
		if(errorCounter > 0) {
			g.setFont(fontError);
			errorLabel.draw(g);
		}
		
	}

	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame,
			int delta) throws SlickException {

		Input input = gameContainer.getInput();
		InputState inputState = InputState.getInstance();
		inputState.setMousePosition(input.getMouseX(), input.getMouseY());
		inputState.mouseButtonLeft.setMouseState(input.isMousePressed(Input.MOUSE_LEFT_BUTTON),
				input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON));
		inputState.mouseButtonRight.setMouseState(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON),
				input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON));
		
		
		/* end the game */
		if(endGame) {
			gameContainer.exit();
		}
		
		
		if(errorCounter > 0) {
			errorCounter -= delta;
		}
		

		if(startGame) {
			startGame = false;
			catchInput(input);
			((GameView) stateBasedGame.getState(GameWindow.GAME)).startNewGame();
			stateBasedGame.enterState(GameWindow.GAME, GameGlobals.transitionOut(), GameGlobals.transitionIn());
			return;
		}
		
		if(startRules) {
			startRules = false;
			catchInput(input);
			stateBasedGame.enterState(GameWindow.RULES,
					GameGlobals.transitionOut(), GameGlobals.transitionIn());
			return;
		}

		
		// !creatingNewPlayer
		startLabel.update(inputState, delta);
		rulesLabel.update(inputState, delta);
		endLabel.update(inputState, delta);

		catchInput(input);
	}
	
	
	private int[] inputKeys = {
			Input.KEY_A, Input.KEY_B, Input.KEY_C, Input.KEY_D, Input.KEY_E, Input.KEY_F,
			Input.KEY_G, Input.KEY_H, Input.KEY_I, Input.KEY_J, Input.KEY_K, Input.KEY_L,
			Input.KEY_M, Input.KEY_N, Input.KEY_O, Input.KEY_P, Input.KEY_Q, Input.KEY_R,
			Input.KEY_S, Input.KEY_T, Input.KEY_U, Input.KEY_V, Input.KEY_W, Input.KEY_X,
			Input.KEY_Y, Input.KEY_Z,
			Input.KEY_APOSTROPHE, Input.KEY_GRAVE, Input.KEY_SEMICOLON, Input.KEY_LBRACKET
	};
	public void catchInput(Input input) {
		input.isKeyPressed(Input.KEY_LEFT);
		input.isKeyPressed(Input.KEY_UP);
		input.isKeyPressed(Input.KEY_HOME);
		input.isKeyPressed(Input.KEY_PRIOR);
		input.isKeyPressed(Input.KEY_RIGHT);
		input.isKeyPressed(Input.KEY_DOWN);
		input.isKeyPressed(Input.KEY_END);
		input.isKeyPressed(Input.KEY_NEXT);
		input.isKeyPressed(Input.KEY_DELETE);
		input.isKeyPressed(Input.KEY_RETURN);
		for(int key : inputKeys) {
			input.isKeyPressed(key);
		}
		Mouse.getDWheel();
	}
	
	
	

	@Override
	public int getID() {
		return GameWindow.OPTION_SCREEN;
	}


	private boolean startGame = false;
	public void handleStartGameButtonClick() {
		startGame = StateOfGame.getInstance().startNewGame();
		if(!startGame) {
			errorCounter = GameGlobals.ERROR_TEXT_TIME;
			errorLabel.setText("Keine Wörter vorhanden.");
		}
		else {
			errorCounter = 0;
		}
	}

	private boolean startRules = false;
	public void handleRulesFieldClick() {
		startRules = true;
	}

	private boolean endGame = false;
	public void endGameButtonClick() {
		endGame = true;
	}

}
