package num6ers.game;

import java.io.IOException;

import num6ers.basic.GameColor;
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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameView extends BasicGameState implements GameState {
	
	private StateOfGame stateOfGame = StateOfGame.getInstance();
	
	private HighScoreLabel		highscoreLabel;
	private LevelLabel			levelLabel;
	private PlayerScoreLabel	playerScoreLabel;
	private TimeLabel			timeLabel;
	private Label				wordFileNameLabel;
	private Rectangle			numberBackground;
	private Label				numberLabel;
	private String				inputString;
	private Label				inputLabel;
	private int[]				inputKeys = {
			Input.KEY_A, Input.KEY_B, Input.KEY_C, Input.KEY_D, Input.KEY_E, Input.KEY_F,
			Input.KEY_G, Input.KEY_H, Input.KEY_I, Input.KEY_J, Input.KEY_K, Input.KEY_L,
			Input.KEY_M, Input.KEY_N, Input.KEY_O, Input.KEY_P, Input.KEY_Q, Input.KEY_R,
			Input.KEY_S, Input.KEY_T, Input.KEY_U, Input.KEY_V, Input.KEY_W, Input.KEY_X,
			Input.KEY_Y, Input.KEY_Z,
			Input.KEY_APOSTROPHE, Input.KEY_GRAVE, Input.KEY_SEMICOLON
	};
	
	private Font		fontInfo;
	private Font		fontTime;
	private Font		fontNumbers;
	private Font		fontFileName;
	private Font 		fontInput;
	private Image		background;
	
	private Label		lblWrongAnswer;
	private Label		lblRightAnswer;
	private YesNoButton btContinue;
	private YesNoButton btEndGame;
	private Font		fontGameStatus;
	
	private int	answerStatus = 0; // 0: no answer, 1 : right, 2 wrong
	
	
	public GameView() {
		super();
	}
	
	
	public void startNewGame() {
		String word = stateOfGame.getCurrentWord();
		String nrWord = getNumberString(word);
		numberLabel.setText(nrWord);
		wordFileNameLabel.setText(stateOfGame.getWordFileName());
		inputString = "";
		inputLabel.setText(inputString);
		stateOfGame.startTime();
		answerStatus = 0;
	}
	
	public void nextWord() {
		try {
			stateOfGame.calculateNextWord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String word = stateOfGame.getCurrentWord();
		String nrWord = getNumberString(word);
		wordFileNameLabel.setText(stateOfGame.getWordFileName());
		numberLabel.setText(nrWord);
		inputString = "";
		stateOfGame.startTime();
	}
	
	public String getNumberString(String word) {
		char[] wordChar = word.toCharArray();
		String nrString = "";
		for(int i=0; i<wordChar.length; i++) {
			int nr = wordChar[i] - 'A' + 1;
			switch(wordChar[i]) {
			case 'Ä': nr = 27; break;
			case 'Ö': nr = 28; break;
			case 'Ü': nr = 29; break;
			case 'ß': nr = 30; break;
			default : break;
			}
			if(i > 0) {
				nrString += " ";
			}
			nrString += nr;
		}
		return nrString;
	}
	

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {

		/* fonts */
		fontInfo	= new GameFont(GameFont.FONT16);
		fontTime	= new GameFont(GameFont.FONT24);
		fontNumbers = new GameFont(GameFont.FONT24);
		fontInput	= new GameFont(GameFont.FONT32);
		fontGameStatus = new GameFont(GameFont.FONT32);
		fontFileName = new GameFont(GameFont.FONT24);
		
		/* labels */
		int leftGap			= 30;
		int numbersY		= 150;
		highscoreLabel		= new HighScoreLabel(fontInfo, leftGap);
		levelLabel			= new LevelLabel(fontInfo,
				GameGlobals.SCREEN_WIDTH - 2 * (leftGap + GameGlobals.GAME_LABEL_WIDTH));
		playerScoreLabel	= new PlayerScoreLabel(fontInfo,
				GameGlobals.SCREEN_WIDTH - leftGap - GameGlobals.GAME_LABEL_WIDTH);
		timeLabel			= new TimeLabel(fontTime, GameGlobals.SCREEN_WIDTH/2);
		wordFileNameLabel	= new Label(GameGlobals.SCREEN_WIDTH/2,
				numbersY - 10 - fontFileName.getLineHeight(),
				"",	fontFileName, Label.CENTER);
		numberBackground	= new Rectangle(0, numbersY,
				GameGlobals.SCREEN_WIDTH, fontNumbers.getLineHeight());
		numberLabel			= new Label(GameGlobals.SCREEN_WIDTH/2,	numbersY,
				"",	fontNumbers, Label.CENTER);
		inputLabel			= new Label(GameGlobals.SCREEN_WIDTH/2,
				numbersY + 10 + fontNumbers.getLineHeight(),
				"",	fontInput, Label.CENTER);
		
		/* background */
		background = new Image("res/img/background02.jpg");
		
		/* for wrong answer / no more cards */
		int buttonX = (GameGlobals.SCREEN_WIDTH - YesNoButton.WIDTH) / 2;
		int textY = GameGlobals.SCREEN_HEIGHT - (int) (fontGameStatus.getLineHeight() * 3.5);
		int buttonY = GameGlobals.SCREEN_HEIGHT - fontGameStatus.getLineHeight() * 2;
		lblWrongAnswer = new Label(GameGlobals.SCREEN_WIDTH/2, textY,
				"Falsch!", fontGameStatus, Label.CENTER);
		lblRightAnswer = new Label(GameGlobals.SCREEN_WIDTH/2, textY,
				"Richtig!", fontGameStatus, Label.CENTER);
		btContinue = new YesNoButton(buttonX, buttonY, true, fontGameStatus);
		btEndGame = new YesNoButton(buttonX, buttonY, false, fontGameStatus);
		
		
		/* error label */
//		errorLabel = new Label(GameGlobals.SCREEN_WIDTH/2, GameGlobals.SCREEN_HEIGHT - 36,
//				"Fehler", fontError, Label.CENTER);
		
		/* sound */
//		moveStone = new Sound("res/sound/moveStone.wav");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		background.draw();
		
		highscoreLabel.draw(g);
		levelLabel.draw(g);
		playerScoreLabel.draw(g);
		timeLabel.draw(g);
		
		wordFileNameLabel.draw(g);
		g.setColor(GameColor.NUMBER_BG);
		g.fill(numberBackground);
		numberLabel.draw(g);
		inputLabel.draw(g);
		
		if(answerStatus == 1) {
			g.setColor(GameColor.SCREEN_BLOCKED);
			g.fillRect(0, 0, GameGlobals.SCREEN_WIDTH, GameGlobals.SCREEN_HEIGHT);
			g.setColor(GameColor.BASIS_WRITTING);
			lblRightAnswer.draw(g);
			btContinue.draw(g);
		}
		else if(answerStatus == 2) {
			g.setColor(GameColor.SCREEN_BLOCKED);
			g.fillRect(0, 0, GameGlobals.SCREEN_WIDTH, GameGlobals.SCREEN_HEIGHT);
			g.setColor(GameColor.BASIS_WRITTING);
			lblWrongAnswer.draw(g);
			btEndGame.draw(g);
		}
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
		
		if(stateOfGame.getRemainingTime() == -1) {
			boolean right = stateOfGame.isRightWord(inputString);
			if(right) {
				answerStatus = 1;
			}
			else {
				answerStatus = 2;
			}
		}
		
		if(answerStatus == 0) {
			highscoreLabel.update(inputState, delta);
			playerScoreLabel.update(inputState, delta);
			levelLabel.update(inputState, delta);
			timeLabel.update(inputState, delta);


			for(int key : inputKeys) {
				if(input.isKeyPressed(key) && inputString.length() < GameGlobals.MAX_LETTERS) {
					switch(key) {
					case Input.KEY_APOSTROPHE:	inputString += "Ä"; break;
					case Input.KEY_GRAVE:		inputString += "Ö"; break;
					case Input.KEY_SEMICOLON:	inputString += "Ü"; break;
					default:					inputString += Input.getKeyName(key);
					}
				}
			}
			if(input.isKeyPressed(Input.KEY_BACK)){
				if(inputString.length() > 1) {
					inputString = inputString.substring(0, inputString.length()-1);
				}
				else {
					inputString = "";
				}
			}
			if(input.isKeyPressed(Input.KEY_DELETE)) {
				inputString = "";
			}
			if(input.isKeyPressed(Input.KEY_RETURN) && !inputString.isEmpty()) {
				boolean right = stateOfGame.isRightWord(inputString);
				if(right) {
					answerStatus = 1;
				}
				else {
					answerStatus = 2;
				}
			}
		}
		else if(answerStatus == 1) {
			int points = stateOfGame.calcPoints(inputString);
			lblRightAnswer.setText("Du bekommst " + points + " Punkte.");
			btContinue.update(inputState, delta);
			if(btContinue.isClicked() || input.isKeyPressed(Input.KEY_RETURN)) {
				btContinue.resetClicked();
				answerStatus = 0;
				nextWord();
			}
			catchInput(input);
		}
		else {
			lblWrongAnswer.setText("Falsch! Richtig ist " + stateOfGame.getCurrentWord() + ".");
			btEndGame.update(inputState, delta);
			if(btEndGame.isClicked() || input.isKeyPressed(Input.KEY_RETURN)) {
				btEndGame.resetClicked();
				stateBasedGame.enterState(GameWindow.OPTION_SCREEN,
						GameGlobals.transitionOut(), GameGlobals.transitionIn());
			}
			catchInput(input);
		}
		inputLabel.setText(inputString);
	}
	
	
	public void catchInput(Input input) {
		input.isKeyPressed(Input.KEY_DELETE);
		input.isKeyPressed(Input.KEY_RETURN);
		for(int key : inputKeys) {
			input.isKeyPressed(key);
		}
		Mouse.getDWheel();
	}

	
	@Override
	public int getID() {
		return GameWindow.GAME;
	}

}
