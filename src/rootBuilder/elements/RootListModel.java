package rootBuilder.elements;

import javax.swing.DefaultListModel;
import rootBuilder.common.Root;
import rootBuilder.common.RootList;

public class RootListModel extends DefaultListModel<String> {

	private static final long serialVersionUID = 1L;
	private RootList rootList;
	
	public RootListModel(RootList rootList) {
		this.rootList = rootList;
		for (Root root : rootList.getRootList()) {
			this.addElement(root.getRoot());
		}
	}
	
	public RootList getRootList() {
		return rootList;
	}
	
	public void setRootList(RootList rootList) {
		this.rootList = rootList;
	}

}
