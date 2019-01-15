package rootBuilder.common;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FilePath {
	
	private String filePath;
	private String[] filter;
	
	public FilePath(String[] filter, String dialogTitle, boolean isSaveDialog) {
		this.filter = filter;
		
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(dialogTitle);
		if(isSaveDialog)fc.setDialogType(JFileChooser.SAVE_DIALOG);
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(filter[0],filter[1]);
		fc.setFileFilter(fileNameExtensionFilter);
		int returnVal = fc.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    	this.filePath = fc.getSelectedFile().getAbsolutePath();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getFilter() {
		return filter;
	}

	public void setFilter(String[] filter) {
		this.filter = filter;
	}
	
}
