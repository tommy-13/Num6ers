package num6ers.optionScreen;

public class StartFieldClickBehaviour implements ClickBehaviour {

	private OptionView optionView;
	
	public StartFieldClickBehaviour(OptionView optionView) {
		this.optionView = optionView;
	}
	
	@Override
	public void rightClicked() {}
	@Override
	public void leftClicked() {
		optionView.handleStartGameButtonClick();
	}
}
