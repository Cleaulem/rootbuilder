package rootBuilder.elements;

import javax.swing.JList;

public class RootJList extends JList<String> {
	
	private static final long serialVersionUID = 1L;
	private int rootListCounter;

	public RootJList(int rootListCounter) {
		this.rootListCounter = rootListCounter;
	}

	public int getRootListCounter() {
		return rootListCounter;
	}

	public void setRootListCounter(int rootListCounter) {
		this.rootListCounter = rootListCounter;
	}
	
	public void updateRootListCounter() {
		this.rootListCounter++;
		if (this.rootListCounter >= 4) this.rootListCounter = 1;
	}
	
	public void decrementRootListCounter() {
		if (this.rootListCounter > 0) this.rootListCounter--;
	}
	
}
