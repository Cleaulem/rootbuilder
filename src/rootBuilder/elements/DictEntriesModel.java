package rootBuilder.elements;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import rootBuilder.common.Entry;
import rootBuilder.common.EntryList;

public class DictEntriesModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private EntryList entryList;
	private JComboBox<Character> firstLetters;
	
	public DictEntriesModel(EntryList entryList, JComboBox<Character> firstLetters) {
		
		this.entryList = entryList;
		this.firstLetters = firstLetters;
		
		this.addColumn("Entry");
		this.addColumn("Root");
		
		char firstLetter = Character.toLowerCase((char) firstLetters.getSelectedItem());
		
		for (Entry entry : this.entryList.getEntryList()) {
			
			// incorporate umlauts into firstLetter
			boolean umlaut = false;
			if (entry.getFirstLetter() == 'ä' && firstLetter == 'a' ||
					entry.getFirstLetter() == 'ö' && firstLetter == 'o'||
					entry.getFirstLetter() == 'ü' && firstLetter == 'u')
				umlaut = true;
							
			if (entry.getFirstLetter() == firstLetter ||
				umlaut ||
				Character.isDigit(entry.getFirstLetter()) && firstLetter == '0') 
			{
				String token;
				if (entry.hasRoot()) {
					token = entry.getRootEntry() ;
				} else {
					token = "---";
				}
				this.addRow(new Object[] {entry.getEntry(),token});
			}
		}
		
	}

	public EntryList getEntryList() {
		return entryList;
	}

	public void setEntryList(EntryList entryList) {
		this.entryList = entryList;
	}

	public JComboBox<Character> getFirstLetters() {
		return firstLetters;
	}

	public void setFirstLetters(JComboBox<Character> firstLetters) {
		this.firstLetters = firstLetters;
	}

}
