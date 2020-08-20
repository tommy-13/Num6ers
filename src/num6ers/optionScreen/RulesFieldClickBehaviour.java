package num6ers.optionScreen;

public class RulesFieldClickBehaviour implements ClickBehaviour {

	private OptionView optionView;
	
	public RulesFieldClickBehaviour(OptionView optionView) {
		this.optionView = optionView;
	}
	
	@Override
	public void rightClicked() {}

	@Override
	public void leftClicked() {
		optionView.handleRulesFieldClick();
	}

}
