package rootBuilder.common;

public class Root {
	
	private char rad1;
	private char rad2;
	private char rad3;
	private String root;
	private int vowelIndex;
	
	public Root(String root, int vowelIndex) {
		this.rad1 = root.charAt(0);
		this.rad2 = root.charAt(2);
		if (root.length() > 3) this.rad3 = root.charAt(4);
		this.root = root;
		this.vowelIndex = vowelIndex;
	}

	public char getRad1() {
		return rad1;
	}

	public void setRad1(char rad1) {
		this.rad1 = rad1;
	}

	public char getRad2() {
		return rad2;
	}

	public void setRad2(char rad2) {
		this.rad2 = rad2;
	}

	public char getRad3() {
		return rad3;
	}

	public void setRad3(char rad3) {
		this.rad3 = rad3;
	}
	
	public String getRoot() {
		return root;
	}

	public int getVowelIndex() {
		return vowelIndex;
	}
	
	public void setVowelIndex(int vowelIndex) {
		this.vowelIndex = vowelIndex;
	}
	
	public void increaseVowelIndex(int increment) {
		vowelIndex += increment;
	}
	
	public void decreaseVowelIndex(int decrement) {
		if (vowelIndex >= decrement) vowelIndex -= decrement;
		else vowelIndex = 0;
	}
}
