package num6ers.game;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import num6ers.basic.GameGlobals;
import num6ers.basic.OSInformation;
import num6ers.basic.WordFiles;
import num6ers.io.parseTree.TreeElement;
import num6ers.io.parseTree.XMLTreeReader;
import num6ers.io.parseTree.XMLTreeWriter;
import num6ers.io.safeLoad.ParseTreeStructureException;
import num6ers.io.safeLoad.ParseTreeXMLSettingsTranslator;

public class StateOfGame {

	private static StateOfGame uniqueStateOfGame = new StateOfGame();
	private StateOfGame() {
		highscore 	= 0;
		level		= 1;
		score		= 0;
		levelTime	= 0;
		basisTime	= 0;
		words		= new LinkedList<String>();
		currentWord = "";
		lvlWordNr	= 0;
		
		wordFiles = WordFiles.getInstance();
	}
	public static StateOfGame getInstance() {
		return uniqueStateOfGame;
	}
	
	private final String settingsFilePath = "safe" + OSInformation.fileSeparator + "settings.usf"; // user settings file
	private String wordFilePath;
	private String wordFileName;
	private int	highscore;
	private int	score;
	private int	level;
	
	private int levelTime;
	private long basisTime;
	
	private List<String> words;
	private String		 currentWord;
	private final int	 LVL_NUMBERS = 5;
	private int			 lvlWordNr;
	
	private WordFiles wordFiles;
	
	
	public boolean startNewGame() {
		score = 0;
		lvlWordNr = 0;
		return loadNextWordFile();
	}
	
	private boolean loadNextWordFile() {
		wordFiles.setNextFile();
		wordFilePath = wordFiles.getCurrentFilePath();
		wordFileName = wordFiles.getCurrentFileName();
		if(wordFilePath == null) {
			return false;
		}
		try {
			loadWords();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void loadWords() throws IOException {
		// load words from file level.txt
		FileReader fileReader = new FileReader(wordFilePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		words.clear();
		String line = "";
		while((line = bufferedReader.readLine()) != null) {
			String word = line.trim().toUpperCase();
			if(word.length() <= GameGlobals.MAX_LETTERS) {
				words.add(word);
			}
		}
		bufferedReader.close();
		fileReader.close();
		
		// shuffle words
		Collections.shuffle(words);
		currentWord = words.remove(0);
	}
	
	public int calcPoints(String word) {
		int len = word.length();
		int points = len * level;
		return points;
	}
	
	public void calculateNextWord() throws IOException {
		// update score
		addToScore(calcPoints(currentWord));
		
		if(lvlWordNr >= LVL_NUMBERS) {
			// new level
			level++;
			if(level > GameGlobals.MAX_LEVEL) {
				level = 1;
			}
			lvlWordNr = 0;
			levelTime = GameGlobals.MAX_LEVEL + 1 - level;
		}
		lvlWordNr++;
		
		if(words.isEmpty()) {
			loadNextWordFile();
		}
		
		currentWord = words.remove(0);
	}
	public String getCurrentWord() {
		return currentWord;
	}
	public void startTime() {
		levelTime = (int) (currentWord.length() * (5 - 0.2 * (level - 1)));
		basisTime = System.currentTimeMillis();
	}
	public int getRemainingTime() {
		long currentTime = System.currentTimeMillis() - basisTime;
		if (currentTime <= 0) {
			return -1;
		}
		else {
			return levelTime - ((int) (currentTime / 1000));
		}
	}
	
	private void addToScore(int points) {
		this.score += points;
		if(score > highscore) {
			highscore = score;
			safe();
		}
	}
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	
	public int getScore() {
		return score;
	}
	public int getLevel() {
		return level;
	}
	public int getHighscore() {
		return highscore;
	}
	
	public boolean isRightWord(String word) {
		return currentWord.equals(word);
	}
	
	public String getWordFileName() {
		return wordFileName;
	}
	
	
	
	// -------------------------------------------------------------------------------
	// load and safe
	// -------------------------------------------------------------------------------
	public void safe() {
		TreeElement treeRoot = ParseTreeXMLSettingsTranslator.createSettingsTree(this);
		XMLTreeWriter treeWriter = new XMLTreeWriter(settingsFilePath, treeRoot);
		try {
			treeWriter.write();
		} catch (IOException e) {}
	}
	
	public void load() {
		File loadedFile = new File(settingsFilePath);
		if(!loadedFile.exists()) {
			safe();
			return;
		}
		
		String loadPath = loadedFile.getAbsolutePath();
		
		TreeElement treeRoot = null;
		XMLTreeReader treeReader = new XMLTreeReader(loadPath);
		try {
			treeRoot = treeReader.read();
		} catch (IOException e) {}
		
		if(treeRoot == null) {
			return;
		}
		
		try {
			ParseTreeXMLSettingsTranslator.createSettings(this, treeRoot);
		} catch (ParseTreeStructureException e) {}
	}
	
}
