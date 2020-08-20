package num6ers.optionScreen;

public class EndFieldClickBehaviour implements ClickBehaviour {

	private OptionView	optionView;
	
	public EndFieldClickBehaviour(OptionView optionView) {
		this.optionView		= optionView;
	}
	
	@Override
	public void rightClicked() {}

	@Override
	public void leftClicked() {
		optionView.endGameButtonClick();
	}

}
