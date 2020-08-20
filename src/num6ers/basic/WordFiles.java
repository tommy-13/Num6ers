package num6ers.basic;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordFiles {

	private static WordFiles uniqueWordFiles = new WordFiles();
	private WordFiles() {
		filePointer = 0;
		wordFiles = new ArrayList<String>();
		
		File folder = new File("words");
		if(folder.isDirectory()) {
			File[] files = folder.listFiles(new FilenameFilter() {	
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});
			
			for(File f : files) {
				wordFiles.add(f.getAbsolutePath());
			}
			Collections.shuffle(wordFiles);
		}
	}
	public static WordFiles getInstance() {
		return uniqueWordFiles;
	}
	
	
	private List<String>	wordFiles;
	private String			currentFilePath;
	private String			currentFileName;
	private int				filePointer;
	
	public boolean hasFiles() {
		return getNumberOfFiles() > 0;
	}
	public int getNumberOfFiles() {
		return wordFiles.size();
	}
	
	private void setCurrentFile(int nr) {
		if(hasFiles()) {
			currentFilePath = wordFiles.get(nr);
			currentFileName = currentFilePath.substring(currentFilePath.lastIndexOf(File.separator)+1);
			currentFileName = currentFileName.substring(0, currentFileName.indexOf("."));
			if(currentFileName.length() > 32) {
				currentFileName = currentFileName.substring(0, 32);
			}
		}
		else {
			currentFilePath = null;
			currentFileName = null;
		}
	}
	public void setNextFile() {
		setCurrentFile(filePointer);
		filePointer++;
		if(filePointer >= getNumberOfFiles()) {
			filePointer = 0;
		}
	}
	public String getCurrentFilePath() {
		return currentFilePath;
	}
	public String getCurrentFileName() {
		if(currentFileName == null) {
			return "keine Kartensätze vorhanden";
		}
		return currentFileName;
	}
	
}
