package rootBuilder.gui;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import rootBuilder.common.Entry;
import rootBuilder.common.EntryList;
import rootBuilder.elements.RootJList;
import rootBuilder.elements.RootListModel;
import rootBuilder.common.Root;
import rootBuilder.common.RootList;

public class BtnEvents {
	
	public static void handleBtnAdd(
			JTable dictEntries,
			EntryList entryList,
			Root selectedRoot,
			int selectedVowelIndex,
			String radioBtnContent,
			ButtonGroup vowelCombGroup,
			JRadioButton radioBtnA,
			JRadioButton radioBtnI,
			JRadioButton radioBtnU,
			JRadioButton radioBtnAI,
			JRadioButton radioBtnAU,
			JRadioButton radioBtnAIU)
	{
		int row = dictEntries.getSelectedRow();
		int column = dictEntries.getSelectedColumn();
		String entryInDict = dictEntries.getValueAt(row, column).toString();
		
		// check if all requirements for function are met
		if (column == 1 && selectedRoot != null &&
				radioBtnContent != null && entryInDict == "---") {
			
			// write the root and the vowel combination into the selected table cell
			dictEntries.setValueAt(selectedRoot.getRoot() + " (" + radioBtnContent + ")", row, column);
			
			//update the resp. entry in entryList
			for (Entry entry : entryList.getEntryList()) {
				if(dictEntries.getValueAt(row, 0) == entry.getEntry()) {
					entry.setRoot(new Root(selectedRoot.getRoot(),selectedVowelIndex));
					break;
				}
			}
			
			// update the vowelIndex in the root's entry in the rootList
			selectedRoot.increaseVowelIndex(selectedVowelIndex);
			
			// update selected RadioButton to be unselected
			vowelCombGroup.clearSelection();
			
			// update radioButtons to be unselectable
			updateRadioBtn(
					radioBtnA, 
					radioBtnI, 
					radioBtnU, 
					radioBtnAI, 
					radioBtnAU, 
					radioBtnAIU, 
					selectedRoot.getVowelIndex()
					);
		}
		
	}
	
	public static void handleBtnRemove(
			JTable dictEntries,
			EntryList entryList,
			RootList rootList,
			Root selectedRoot,
			JRadioButton radioBtnA,
			JRadioButton radioBtnI,
			JRadioButton radioBtnU,
			JRadioButton radioBtnAI,
			JRadioButton radioBtnAU,
			JRadioButton radioBtnAIU) {
		
		int row = dictEntries.getSelectedRow();
		int column = dictEntries.getSelectedColumn();
		
		if (column == 1 && dictEntries.getValueAt(row, column).toString() != "---") {
			
			Entry selectedEntry = new Entry("entry");
			Root entryRoot;
			
			// reset the value of the Root-table cell
			dictEntries.setValueAt("---", row, column);
			
			// get the entry in the entryList
			for (Entry entry : entryList.getEntryList()) {
				if (dictEntries.getValueAt(row, column - 1).toString() == entry.getEntry()) {
					selectedEntry = entry;
					break;
				}
			}
			
			//get the root in selectedEntry
			entryRoot = selectedEntry.getRoot();
			
			// update the root in rootList by decreasing the vowelIndex
			for (Root root : rootList.getRootList()) {
				
				if (entryRoot.getRoot().equals(root.getRoot())) {
					root.decreaseVowelIndex(entryRoot.getVowelIndex());
					
					// update the radioButtons in case the removed root is selected in RootJList
					if (entryRoot.getRoot().equals(selectedRoot.getRoot()))
						updateRadioBtn(
							radioBtnA,
							radioBtnI,
							radioBtnU,
							radioBtnAI,
							radioBtnAU,
							radioBtnAIU,
							root.getVowelIndex());
					break;
				}
			}
			
			// reset the Root in selectedEntry
			selectedEntry.resetRoot();
		}
	}
	
	public static void handleRadicalBtn(
			char character,
			RootList rootList,
			RootJList rootJList,
			JLabel lblChosenRadicals)
	{
		
		// creating new RootList for refreshing which Roots to be displayed in rootJList
		RootList updatedRootList = new RootList(new ArrayList<Root>());
		
		// handling the first radical when button is pressed the first time
		if (rootJList.getRootListCounter() == 0 || rootJList.getRootListCounter() == 3) {
			for (Root root : rootList.getRootList()) {
				if (root.getRad1() == character)
					updatedRootList.getRootList().add(root);
			}
			
			// updating lblChosenRadicals
			lblChosenRadicals.setText(Character.toString(character));
		}

		// handling the first radical when button is pressed the second and third  time
		else {
			
			// creating new ArrayList to get the items already stored in rootJList
			ArrayList<String> rootJListEntries = new ArrayList<String>();
			for(int i = 0; i < rootJList.getModel().getSize(); i++) {
				rootJListEntries.add(rootJList.getModel().getElementAt(i));
		    }
			
			// going through rootJListEntries and sorting out the entries
			// which don't fit to the button input
			for (int i = 0; i < rootJListEntries.size(); i++) {
				
				if (rootJList.getRootListCounter() == 1) {
					if (rootJListEntries.get(i).charAt(2) != character) {
						rootJListEntries.remove(i);
						i--;
					}
				} else {
					
					if (rootJListEntries.get(i).length() <= 3) rootJListEntries.remove(i);
					
					if (rootJListEntries.get(i).charAt(4) != character) {
						rootJListEntries.remove(i);
						i--;
					}
				}
				
			}
			
			// filling updatedRootList with the remaining entries from rootJListEntries
			for (String entry : rootJListEntries) {
				Root root = new Root(entry, 0);
				updatedRootList.getRootList().add(root);
			}
			
			// updating lblChosenRadicals
			lblChosenRadicals.setText(lblChosenRadicals.getText() + "-" + Character.toString(character));
			
		}
		
		rootJList.updateRootListCounter();
		rootJList.setModel(new RootListModel(updatedRootList));
		

		// first entry in rootJList is automatically selected
		rootJList.setSelectedIndex(0);
	}
	
	public static void handleBtnBack(
			JLabel lblChosenRadicals,
			RootList rootList,
			RootJList rootJList)
	{
		// Resetting the Model of rootJList if rootListCounter == 1
		if (rootJList.getRootListCounter() == 1) {
			rootJList.setModel(new RootListModel(rootList));
			lblChosenRadicals.setText("Radicals");
		}
		
		// Removing the characters from lblChosenRadicals
		if (rootJList.getRootListCounter() > 1) 
			lblChosenRadicals.setText(
					lblChosenRadicals.getText().
					substring(0, lblChosenRadicals.getText().length() - 2));

		// creating new ArrayList for refreshing which Roots to be displayed in rootJList
		RootList updatedRootList = new RootList(new ArrayList<Root>());
		
		// keeping the Roots containing the first selected radical if rootListCounter == 2
		if (rootJList.getRootListCounter() == 2) {
			for (Root root : rootList.getRootList()) {
				if (root.getRad1() == lblChosenRadicals.getText().charAt(0)) {
					updatedRootList.getRootList().add(root);
				}
			}
			rootJList.setModel(new RootListModel(updatedRootList));
		}

		// keeping the Roots containing the first and the second
		// selected radical if rootListCounter == 3
		if (rootJList.getRootListCounter() == 3) {
			for (Root root : rootList.getRootList()) {
				if (root.getRad1() == lblChosenRadicals.getText().charAt(0) &&
					root.getRad2() == lblChosenRadicals.getText().charAt(2)) {
					updatedRootList.getRootList().add(root);
				}
			}
			rootJList.setModel(new RootListModel(updatedRootList));
		}

		// decrementing rootListCounter
		rootJList.decrementRootListCounter();
		
		// first entry in rootJList is automatically selected
		rootJList.setSelectedIndex(0);
		
	}
	
	public static void handleBtnReset(
			RootList rootList,
			RootJList rootJList,
			JLabel lblChosenRadicals,
			JLabel lblRoot,
			JRadioButton radioBtnA,
			JRadioButton radioBtnI,
			JRadioButton radioBtnU,
			JRadioButton radioBtnAI,
			JRadioButton radioBtnAU,
			JRadioButton radioBtnAIU)
	{
		rootJList.setModel(new RootListModel(rootList));
		lblChosenRadicals.setText("Radicals");
		lblRoot.setText("Root");
		
		// reset all radioBtns to enabled
		radioBtnA.setEnabled(true);
		radioBtnI.setEnabled(true);
		radioBtnU.setEnabled(true);
		radioBtnAI.setEnabled(true);
		radioBtnAU.setEnabled(true);
		radioBtnAIU.setEnabled(true);
	}
	
	public static void updateRadioBtn(
			JRadioButton radioBtnA,
			JRadioButton radioBtnI,
			JRadioButton radioBtnU,
			JRadioButton radioBtnAI,
			JRadioButton radioBtnAU,
			JRadioButton radioBtnAIU,
			int vowelIndex)
	{
		if(vowelIndex == 0) {
			radioBtnA.setEnabled(true);
			radioBtnI.setEnabled(true);
			radioBtnU.setEnabled(true);
			radioBtnAI.setEnabled(true);
			radioBtnAU.setEnabled(true);
			radioBtnAIU.setEnabled(true);
		}
		
		if(vowelIndex == 1) {
			radioBtnA.setEnabled(false);
			radioBtnI.setEnabled(true);
			radioBtnU.setEnabled(true);
			radioBtnAI.setEnabled(false);
			radioBtnAU.setEnabled(false);
			radioBtnAIU.setEnabled(false);
		}
		
		if(vowelIndex == 2) {
			radioBtnA.setEnabled(true);
			radioBtnI.setEnabled(false);
			radioBtnU.setEnabled(true);
			radioBtnAI.setEnabled(false);
			radioBtnAU.setEnabled(true);
			radioBtnAIU.setEnabled(false);
		}
		
		if(vowelIndex == 3) {
			radioBtnA.setEnabled(false);
			radioBtnI.setEnabled(false);
			radioBtnU.setEnabled(true);
			radioBtnAI.setEnabled(false);
			radioBtnAU.setEnabled(false);
			radioBtnAIU.setEnabled(false);
		}
		
		if(vowelIndex == 4) {
			radioBtnA.setEnabled(true);
			radioBtnI.setEnabled(true);
			radioBtnU.setEnabled(false);
			radioBtnAI.setEnabled(true);
			radioBtnAU.setEnabled(false);
			radioBtnAIU.setEnabled(false);
		}
		
		if(vowelIndex == 5) {
			radioBtnA.setEnabled(false);
			radioBtnI.setEnabled(true);
			radioBtnU.setEnabled(false);
			radioBtnAI.setEnabled(false);
			radioBtnAU.setEnabled(false);
			radioBtnAIU.setEnabled(false);
		}
		
		if(vowelIndex == 6) {
			radioBtnA.setEnabled(true);
			radioBtnI.setEnabled(false);
			radioBtnU.setEnabled(false);
			radioBtnAI.setEnabled(false);
			radioBtnAU.setEnabled(false);
			radioBtnAIU.setEnabled(false);
		}
		
		if(vowelIndex == 7) {
			radioBtnA.setEnabled(false);
			radioBtnI.setEnabled(false);
			radioBtnU.setEnabled(false);
			radioBtnAI.setEnabled(false);
			radioBtnAU.setEnabled(false);
			radioBtnAIU.setEnabled(false);
		}
	}
	
}
