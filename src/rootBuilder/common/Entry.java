package rootBuilder.common;

public class Entry {
	
	private String entry;
	private Root root;
	private char firstLetter;
	private boolean hasRoot;
	private String rootEntry;
	
	public Entry(String entry) {
		this.entry = entry;
		this.firstLetter = this.entry.toLowerCase().charAt(0);
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	public String getRootEntry() {
		return rootEntry;
	}
	
	public void setRootEntry(Root root) {
		String rootVowels = "";
		if(root.getVowelIndex() == 1) rootVowels = " (a)";
		if(root.getVowelIndex() == 2) rootVowels = " (i)";
		if(root.getVowelIndex() == 3) rootVowels = " (a/i)";
		if(root.getVowelIndex() == 4) rootVowels = " (u)";
		if(root.getVowelIndex() == 5) rootVowels = " (a/u)";
		if(root.getVowelIndex() == 7) rootVowels = " (a/i/u)";
		this.rootEntry = root.getRoot() + rootVowels;
	}
	
	public void setRootEntry(String root) {
		this.rootEntry = root;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
		this.hasRoot = true;
		this.setRootEntry(root);
	}
	
	public void resetRoot() {
		this.root = null;
		this.hasRoot = false;
		this.rootEntry = null;
	}

	public boolean hasRoot() {
		return hasRoot;
	}

	public void setHasRoot(boolean hasRoot) {
		this.hasRoot = hasRoot;
	}
	
	public char getFirstLetter() {
		return firstLetter;
	}
	
}
