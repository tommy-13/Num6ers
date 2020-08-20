package num6ers.io.safeLoad;

import num6ers.game.StateOfGame;
import num6ers.io.parseTree.Leaf;
import num6ers.io.parseTree.Node;
import num6ers.io.parseTree.TreeElement;


public class ParseTreeXMLSettingsTranslator {
	
	private static final String LabelSettings 			= "Settings";
	private static final String LabelHighscore			= "Highscore";
	
	
	public static TreeElement createSettingsTree(StateOfGame settings) {
		Node root = new Node(LabelSettings);
		Leaf leaf = new Leaf(LabelHighscore);
		leaf.setText("" + settings.getHighscore());
		root.addChild(leaf);
		return root;
	}
	
	
	public static void createSettings(StateOfGame settings, TreeElement root) throws ParseTreeStructureException {
		if(!root.getName().equals(LabelSettings) || root.isLeaf()) {
			throw new ParseTreeStructureException("Settings.Root");
		}
		
		Node nSettings = (Node) root;
		if(!nSettings.hasChild(LabelHighscore)) {
			throw new ParseTreeStructureException("Settings.Highscore");
		}
		
		TreeElement nHighscore = nSettings.getChildren(LabelHighscore).get(0);
		if(!nHighscore.isLeaf()) {
			throw new ParseTreeStructureException("Settings.Highscore");
		}
		Leaf lHighscore = (Leaf) nHighscore;
		
		int highscore = 0;
		try {
			highscore = Integer.parseInt(lHighscore.getText());
		} catch(NumberFormatException e) {
			throw new ParseTreeStructureException("Settings.HighscoreFormat");
		}
		settings.setHighscore(highscore);
	}
	
}
