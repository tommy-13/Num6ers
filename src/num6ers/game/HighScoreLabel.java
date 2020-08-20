package num6ers.game;

import num6ers.basic.InputState;

import org.newdawn.slick.Font;

/**
 * button in a game
 * @author tommy
 *
 */

public class HighScoreLabel extends TextNumberLabel {
	
	public HighScoreLabel(Font font, int x) {
		super(font, "Highscore", x);
	}

	@Override
	public void update(InputState inputState, int delta) {
		lblNumber.setText("" + StateOfGame.getInstance().getHighscore());
	}
	
}
