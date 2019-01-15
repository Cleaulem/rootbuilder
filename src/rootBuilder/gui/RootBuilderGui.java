package rootBuilder.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import rootBuilder.common.*;
import rootBuilder.elements.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class RootBuilderGui extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
////-- Storage Elements --//
	private EntryList entryList = new EntryList(new ArrayList<Entry>());
	private RootList  rootList  = new RootList(new ArrayList<Root>());
	private Root selectedRoot;
	private int selectedVowelIndex;
	private String radioBtnContent;
	
////-- dictPanel elements--//
	private JTable dictEntries = new JTable();
	private JComboBox<Character> firstLetters;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnSave;
	
////-- rootVowelsPanel elements --//
	private JLabel lblRoot;
	private ButtonGroup vowelCombGroup;
	
	private JRadioButton radioBtnA;
	private JRadioButton radioBtnI;
	private JRadioButton radioBtnU;
	private JRadioButton radioBtnAI;
	private JRadioButton radioBtnAU;
	private JRadioButton radioBtnAIU;
	
	private JLabel label;
	private JLabel label_1;
	private JLabel label_11;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private Component horizontalStrut_7;
	private Component horizontalStrut_8;
	private Component horizontalStrut_9;
	private Component horizontalStrut_10;
	
////-- rootListPanel elements --//
	private RootJList rootJList;
	private JLabel lblChosenRadicals;
	private JButton btnB;
	private JButton btnC;
	private JButton btnCx;
	private JButton btnD;
	private JButton btnF;
	private JButton btnG;
	private JButton btnH;
	private JButton btnJ;
	private JButton btnK;
	private JButton btnL;
	private JButton btnM;
	private JButton btnN;
	private JButton btnP;
	private JButton btnQ;
	private JButton btnR;
	private JButton btnS;
	private JButton btnSx;
	private JButton btnT;
	private JButton btnV;
	private JButton btnW;
	private JButton btnY;
	private JButton btnZ;
	private JButton btnZx;
	private JButton btnBack;
	private JButton btnReset;
	
////-- menuBar elements --//	
	private JMenuItem mntmLoadWordList;
	private JMenuItem mntmLoadRootList;
	private JMenuItem mntmSaveMRLFile;
	private JMenuItem mntmExit;
	private JMenuItem mntmOpenMRLFile;
	private JMenu mnOpen;
	private JMenu mnSave;
	private JMenuItem mntmSaveWordList;
	private JMenuItem mntmSaveRootList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RootBuilderGui frame = new RootBuilderGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RootBuilderGui() {
		setTitle("Root Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 400);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
/////////////////////////////////		
////--- dictPanel elements---////
/////////////////////////////////
		
		JPanel dictPanel = new JPanel();
		getContentPane().add(dictPanel);
		dictPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel firstLetterPanel = new JPanel();
		dictPanel.add(firstLetterPanel, BorderLayout.NORTH);
		firstLetterPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel dictionary = new JPanel();
		firstLetterPanel.add(dictionary);
		
		firstLetters = new JComboBox<Character>();
		firstLetters.addActionListener(this);
		dictionary.add(firstLetters);
		firstLetters.setEditable(true);
		
		char[] firstLettersList = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
				'O','P','Q','R','S','T','U','V','W','Z','0'};
		for (char firstLetter : firstLettersList) {
			firstLetters.addItem(firstLetter);
		}
		
		JPanel indicator = new JPanel();
		firstLetterPanel.add(indicator);
		
		JPanel entryTablePanel = new JPanel();
		entryTablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		dictPanel.add(entryTablePanel, BorderLayout.CENTER);
		
		dictEntries = new JTable();
		dictEntries.setPreferredScrollableViewportSize(new Dimension(300, 200));
		dictEntries.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Entry", "Root"
			}
		));
		dictEntries.getColumnModel().getColumn(0).setMaxWidth(300);
		dictEntries.getColumnModel().getColumn(1).setMaxWidth(250);
		dictEntries.setCellSelectionEnabled(true);
		entryTablePanel.add(new JScrollPane(dictEntries));
		dictEntries.setAlignmentY(Component.TOP_ALIGNMENT);
		dictEntries.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel editButtonsPanel = new JPanel();
		dictPanel.add(editButtonsPanel, BorderLayout.SOUTH);
		editButtonsPanel.setMaximumSize(new Dimension(32767, 50));
		editButtonsPanel.setSize(new Dimension(0, 30));
		editButtonsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnAdd = new JButton("Add");
		btnAdd.setMargin(new Insets(2, 7, 2, 7));
		btnAdd.addActionListener(this);
		editButtonsPanel.add(btnAdd);
		
		btnRemove = new JButton("Remove");
		btnRemove.setMargin(new Insets(2, 7, 2, 7));
		btnRemove.addActionListener(this);
		editButtonsPanel.add(btnRemove);
		
		btnSave = new JButton("Save");
		btnSave.setMargin(new Insets(2, 7, 2, 7));
		btnSave.addActionListener(this);
		editButtonsPanel.add(btnSave);

///////////////////////////////////////		
////--- rootVowelsPanel elements---////
///////////////////////////////////////
		
		JPanel rootVowelsPanel = new JPanel();
		rootVowelsPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		getContentPane().add(rootVowelsPanel);
		rootVowelsPanel.setSize(new Dimension(300, 300));
		rootVowelsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		vowelCombGroup = new ButtonGroup();
		
		label = new JLabel("");
		rootVowelsPanel.add(label);
		
		lblRoot = new JLabel("Root");
		lblRoot.setFont(new Font("Dialog", Font.BOLD, 14));
		rootVowelsPanel.add(lblRoot);
		
		label_1 = new JLabel("");
		rootVowelsPanel.add(label_1);
		
		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_5.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_5);
		
		radioBtnA = new JRadioButton("a");
		vowelCombGroup.add(radioBtnA);
		rootVowelsPanel.add(radioBtnA);
		radioBtnA.addActionListener(this);
		
		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_7.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_7);
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_4.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_4);
		
		radioBtnI = new JRadioButton("i");
		vowelCombGroup.add(radioBtnI);
		rootVowelsPanel.add(radioBtnI);
		radioBtnI.addActionListener(this);
		
		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_8.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_8);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_3.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_3);
		
		radioBtnU = new JRadioButton("u");
		vowelCombGroup.add(radioBtnU);
		rootVowelsPanel.add(radioBtnU);
		radioBtnU.addActionListener(this);
		
		horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_9.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_9);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_2.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_2);
		
		radioBtnAI = new JRadioButton("a/i");
		vowelCombGroup.add(radioBtnAI);
		rootVowelsPanel.add(radioBtnAI);
		radioBtnAI.addActionListener(this);
		
		horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalStrut_10.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_10.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(15, 0));
		horizontalStrut.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut);
		
		radioBtnAU = new JRadioButton("a/u");
		vowelCombGroup.add(radioBtnAU);
		rootVowelsPanel.add(radioBtnAU);
		radioBtnAU.addActionListener(this);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_1.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_1);
		
		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_6.setMinimumSize(new Dimension(15, 0));
		rootVowelsPanel.add(horizontalStrut_6);
		
		radioBtnAIU = new JRadioButton("a/i/u");
		vowelCombGroup.add(radioBtnAIU);
		rootVowelsPanel.add(radioBtnAIU);
		radioBtnAIU.addActionListener(this);
		
		label_11 = new JLabel("");
		rootVowelsPanel.add(label_11);
		
//////////////////////////////////////		
////--- rootListPanel elements ---////
//////////////////////////////////////
		
		JPanel rootListPanel = new JPanel();
		getContentPane().add(rootListPanel);
		rootListPanel.setLayout(new BoxLayout(rootListPanel, BoxLayout.Y_AXIS));
		
		JScrollPane rootListPane = new JScrollPane();
		rootListPanel.add(rootListPane);
		
		rootJList = new RootJList(0);
		rootJList.setMinimumSize(new Dimension(0, 400));
		rootListPane.setViewportView(rootJList);
		
		ListSelectionListener rootJListListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				final Object selectedValue = rootJList.getSelectedValue();
				if (selectedValue != null) {
					lblRoot.setText(rootJList.getSelectedValue().toString());
					
					//Converting the JListElement to Root-Object
					for (Root root : rootList.getRootList()) {
						if (rootJList.getSelectedValue().toString() == root.getRoot()) {
							selectedRoot = root;
							break;
						}
					}
				}
				
				// update selected RadioButton to be unselected
				vowelCombGroup.clearSelection();
				
				// Update the vowelCombGroup RadioButtons after the vowelIndex
				BtnEvents.updateRadioBtn(
						radioBtnA, 
						radioBtnI, 
						radioBtnU, 
						radioBtnAI, 
						radioBtnAU, 
						radioBtnAIU, 
						selectedRoot.getVowelIndex()
						);
				
			}
		};
		rootJList.addListSelectionListener(rootJListListener);
		
		JPanel chosenRadicalsPanel = new JPanel();
		rootListPanel.add(chosenRadicalsPanel);
		
		lblChosenRadicals = new JLabel("Radicals");
		chosenRadicalsPanel.add(lblChosenRadicals);
		
		JPanel lettersPanel1 = new JPanel();
		FlowLayout fl_lettersPanel1 = (FlowLayout) lettersPanel1.getLayout();
		fl_lettersPanel1.setAlignment(FlowLayout.LEFT);
		rootListPanel.add(lettersPanel1);
		
		btnB = new JButton("B");
		btnB.setMargin(new Insets(2, 7, 2, 7));
		btnB.addActionListener(this);
		lettersPanel1.add(btnB);
		
		btnC = new JButton("C");
		btnC.setMargin(new Insets(2, 7, 2, 7));
		btnC.addActionListener(this);
		lettersPanel1.add(btnC);
		
		btnCx = new JButton("\u010C");
		btnCx.setMargin(new Insets(2, 7, 2, 7));
		btnCx.addActionListener(this);
		lettersPanel1.add(btnCx);
		
		btnD = new JButton("D");
		btnD.setMargin(new Insets(2, 7, 2, 7));
		btnD.addActionListener(this);
		lettersPanel1.add(btnD);
		
		btnF = new JButton("F");
		btnF.setMargin(new Insets(2, 7, 2, 7));
		btnF.addActionListener(this);
		lettersPanel1.add(btnF);
		
		btnG = new JButton("G");
		btnG.setMargin(new Insets(2, 7, 2, 7));
		btnG.addActionListener(this);
		lettersPanel1.add(btnG);
		
		JPanel lettersPanel2 = new JPanel();
		FlowLayout fl_lettersPanel2 = (FlowLayout) lettersPanel2.getLayout();
		fl_lettersPanel2.setAlignment(FlowLayout.LEFT);
		rootListPanel.add(lettersPanel2);
		
		btnH = new JButton("H");
		btnH.setMargin(new Insets(2, 7, 2, 7));
		btnH.addActionListener(this);
		lettersPanel2.add(btnH);
		
		btnJ = new JButton("J");
		btnJ.setMargin(new Insets(2, 7, 2, 7));
		btnJ.addActionListener(this);
		lettersPanel2.add(btnJ);
		
		btnK = new JButton("K");
		btnK.setMargin(new Insets(2, 7, 2, 7));
		btnK.addActionListener(this);
		lettersPanel2.add(btnK);
		
		btnL = new JButton("L");
		btnL.setMargin(new Insets(2, 7, 2, 7));
		btnL.addActionListener(this);
		lettersPanel2.add(btnL);
		
		btnM = new JButton("M");
		btnM.setMargin(new Insets(2, 7, 2, 7));
		btnM.addActionListener(this);
		lettersPanel2.add(btnM);
		
		btnN = new JButton("N");
		btnN.setMargin(new Insets(2, 7, 2, 7));
		btnN.addActionListener(this);
		lettersPanel2.add(btnN);
		
		JPanel lettersPanel3 = new JPanel();
		FlowLayout fl_lettersPanel3 = (FlowLayout) lettersPanel3.getLayout();
		fl_lettersPanel3.setAlignment(FlowLayout.LEFT);
		rootListPanel.add(lettersPanel3);
		
		btnP = new JButton("P");
		btnP.setMargin(new Insets(2, 7, 2, 7));
		btnP.addActionListener(this);
		lettersPanel3.add(btnP);
		
		btnQ = new JButton("Q");
		btnQ.setMargin(new Insets(2, 7, 2, 7));
		btnQ.addActionListener(this);
		lettersPanel3.add(btnQ);
		
		btnR = new JButton("R");
		btnR.setMargin(new Insets(2, 7, 2, 7));
		btnR.addActionListener(this);
		lettersPanel3.add(btnR);
		
		btnS = new JButton("S");
		btnS.setMargin(new Insets(2, 7, 2, 7));
		btnS.addActionListener(this);
		lettersPanel3.add(btnS);
		
		btnSx = new JButton("\u0160");
		btnSx.setMargin(new Insets(2, 7, 2, 7));
		btnSx.addActionListener(this);
		lettersPanel3.add(btnSx);
		
		btnT = new JButton("T");
		btnT.setMargin(new Insets(2, 7, 2, 7));
		btnT.addActionListener(this);
		lettersPanel3.add(btnT);
		
		JPanel lettersPanel4 = new JPanel();
		FlowLayout fl_lettersPanel4 = (FlowLayout) lettersPanel4.getLayout();
		fl_lettersPanel4.setAlignment(FlowLayout.LEFT);
		rootListPanel.add(lettersPanel4);
		
		btnV = new JButton("V");
		btnV.setMargin(new Insets(2, 7, 2, 7));
		btnV.addActionListener(this);
		lettersPanel4.add(btnV);
		
		btnW = new JButton("W");
		btnW.setMargin(new Insets(2, 7, 2, 7));
		btnW.addActionListener(this);
		lettersPanel4.add(btnW);
		
		btnY = new JButton("Y");
		btnY.setMargin(new Insets(2, 7, 2, 7));
		btnY.addActionListener(this);
		lettersPanel4.add(btnY);
		
		btnZ = new JButton("Z");
		btnZ.setMargin(new Insets(2, 7, 2, 7));
		btnZ.addActionListener(this);
		lettersPanel4.add(btnZ);
		
		btnZx = new JButton("\u017D");
		btnZx.setMargin(new Insets(2, 7, 2, 7));
		btnZx.addActionListener(this);
		lettersPanel4.add(btnZx);
		
		JPanel radCorrectionPanel = new JPanel();
		FlowLayout fl_radCorrectionPanel = (FlowLayout) radCorrectionPanel.getLayout();
		fl_radCorrectionPanel.setAlignment(FlowLayout.LEFT);
		rootListPanel.add(radCorrectionPanel);
		
		btnBack = new JButton("<-- Back");
		btnBack.addActionListener(this);
		radCorrectionPanel.add(btnBack);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		radCorrectionPanel.add(btnReset);
		
////////////////////////////////
////--- menuBar elements ---////
////////////////////////////////
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);
		
				mntmOpenMRLFile = new JMenuItem("Open mrl file");
				mnOpen.add(mntmOpenMRLFile);
				
				mntmLoadWordList = new JMenuItem("Open word list");
				mnOpen.add(mntmLoadWordList);
				
				mntmLoadRootList = new JMenuItem("Open root list");
				mnOpen.add(mntmLoadRootList);
				mntmLoadRootList.addActionListener(this);
				mntmLoadWordList.addActionListener(this);
				mntmOpenMRLFile.addActionListener(this);
		
		mnSave = new JMenu("Save");
		mnFile.add(mnSave);
		
		mntmSaveMRLFile = new JMenuItem("Save mrl file");
		mnSave.add(mntmSaveMRLFile);
		mntmSaveMRLFile.addActionListener(this);
		
		mntmSaveWordList = new JMenuItem("Save word list");
		mnSave.add(mntmSaveWordList);
		mntmSaveWordList.addActionListener(this);
		
		mntmSaveRootList = new JMenuItem("Save root list");
		mnSave.add(mntmSaveRootList);
		mntmSaveRootList.addActionListener(this);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAdd) {
			BtnEvents.handleBtnAdd(
					dictEntries,
					entryList,
					selectedRoot,
					selectedVowelIndex,
					radioBtnContent,
					vowelCombGroup,
					radioBtnA,
					radioBtnI,
					radioBtnU,
					radioBtnAI,
					radioBtnAU,
					radioBtnAIU);
			radioBtnContent = null;
			
			// rootJList is resetted after adding a root
			BtnEvents.handleBtnReset(
					rootList,
					rootJList,
					lblChosenRadicals,
					lblRoot,
					radioBtnA,
					radioBtnI,
					radioBtnU,
					radioBtnAI,
					radioBtnAU,
					radioBtnAIU);
			rootJList.setRootListCounter(0);
		}
		
		if (e.getSource() == btnRemove) {
			BtnEvents.handleBtnRemove(
					dictEntries,
					entryList,
					rootList,
					selectedRoot,
					radioBtnA,
					radioBtnI,
					radioBtnU,
					radioBtnAI,
					radioBtnAU,
					radioBtnAIU);
		}
		
		if (e.getSource() == btnSave) {
			try {MenuEvents.handleMntmSaveMRLFile(entryList,rootList);} 
			catch (IOException e1) {e1.printStackTrace();}
		}
		
		if (e.getSource() == mntmOpenMRLFile) {
			try {MenuEvents.handleMntmOpenMRLFile(entryList,dictEntries,firstLetters,rootList,rootJList);}
			catch (IOException e1) {e1.printStackTrace();}
		}
		
		if (e.getSource() == mntmLoadWordList)
			try {MenuEvents.handleMntmLoadWordList(entryList, dictEntries, firstLetters);}
			catch (IOException e1) {e1.printStackTrace();}
		
		if (e.getSource() == mntmLoadRootList) {
			try {MenuEvents.handleMntmLoadRootList(rootList,rootJList);}
			catch (IOException e1) {e1.printStackTrace();}
		}
			
		if (e.getSource() == mntmSaveMRLFile) {
			try {MenuEvents.handleMntmSaveMRLFile(entryList,rootList);} 
			catch (IOException e1) {e1.printStackTrace();}
		}
		
		if (e.getSource() == mntmSaveWordList) {
			try {MenuEvents.handleMntmSaveWordList(entryList);}
			catch (IOException e1) {e1.printStackTrace();}
		}
		
		if (e.getSource() == mntmSaveRootList) {
			try {MenuEvents.handleMntmSaveRootList(rootList);} 
			catch (IOException e1) {e1.printStackTrace();}
		}
			
		if (e.getSource() == mntmExit)
			System.exit(0);
		
		if (e.getSource() == firstLetters)
			dictEntries.setModel(new DictEntriesModel(entryList,firstLetters));
		
		if (e.getSource() == radioBtnA) {
			radioBtnContent = radioBtnA.getText();
			selectedVowelIndex = 1;
		}
			
		if (e.getSource() == radioBtnI) {
			radioBtnContent = radioBtnI.getText();
			selectedVowelIndex = 2;
		}
			
		if (e.getSource() == radioBtnU) {
			radioBtnContent = radioBtnU.getText();
			selectedVowelIndex = 4;
		}
			
		if (e.getSource() == radioBtnAI) {
			radioBtnContent = radioBtnAI.getText();
			selectedVowelIndex = 3;
		}
		
		if (e.getSource() == radioBtnAU) {
			radioBtnContent = radioBtnAU.getText();
			selectedVowelIndex = 5;
		}
		
		if (e.getSource() == radioBtnAIU) {
			radioBtnContent = radioBtnAIU.getText();
			selectedVowelIndex = 7;
		}
		
		if (e.getSource() == btnB) BtnEvents.handleRadicalBtn('b', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnC) BtnEvents.handleRadicalBtn('c', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnCx) BtnEvents.handleRadicalBtn('\u010D', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnD) BtnEvents.handleRadicalBtn('d', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnF) BtnEvents.handleRadicalBtn('f', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnG) BtnEvents.handleRadicalBtn('g', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnH) BtnEvents.handleRadicalBtn('h', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnJ) BtnEvents.handleRadicalBtn('j', rootList, rootJList, lblChosenRadicals);		
		if (e.getSource() == btnK) BtnEvents.handleRadicalBtn('k', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnL) BtnEvents.handleRadicalBtn('l', rootList, rootJList, lblChosenRadicals);				
		if (e.getSource() == btnM) BtnEvents.handleRadicalBtn('m', rootList, rootJList, lblChosenRadicals);					
		if (e.getSource() == btnN) BtnEvents.handleRadicalBtn('n', rootList, rootJList, lblChosenRadicals);
		if (e.getSource() == btnP) BtnEvents.handleRadicalBtn('p', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnQ) BtnEvents.handleRadicalBtn('q', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnR) BtnEvents.handleRadicalBtn('r', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnS) BtnEvents.handleRadicalBtn('s', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnSx) BtnEvents.handleRadicalBtn('\u0161', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnT) BtnEvents.handleRadicalBtn('t', rootList, rootJList, lblChosenRadicals);					
		if (e.getSource() == btnV) BtnEvents.handleRadicalBtn('v', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnW) BtnEvents.handleRadicalBtn('w', rootList, rootJList, lblChosenRadicals);						
		if (e.getSource() == btnY) BtnEvents.handleRadicalBtn('y', rootList, rootJList, lblChosenRadicals);	
		if (e.getSource() == btnZ) BtnEvents.handleRadicalBtn('z', rootList, rootJList, lblChosenRadicals);						
		if (e.getSource() == btnZx) BtnEvents.handleRadicalBtn('\u017E', rootList, rootJList, lblChosenRadicals);
		
		if (e.getSource() == btnBack) {
			BtnEvents.handleBtnBack(lblChosenRadicals, rootList, rootJList);
		}
		
		if (e.getSource() == btnReset) {
			BtnEvents.handleBtnReset(
					rootList,
					rootJList,
					lblChosenRadicals,
					lblRoot,
					radioBtnA,
					radioBtnI,
					radioBtnU,
					radioBtnAI,
					radioBtnAU,
					radioBtnAIU);
			rootJList.setRootListCounter(0);
		}
			
	}
}
