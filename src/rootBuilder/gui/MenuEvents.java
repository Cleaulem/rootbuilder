package rootBuilder.gui;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.swing.JComboBox;
import javax.swing.JTable;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import rootBuilder.elements.DictEntriesModel;
import rootBuilder.elements.RootJList;
import rootBuilder.elements.RootListModel;
import rootBuilder.common.Entry;
import rootBuilder.common.EntryList;
import rootBuilder.common.FilePath;
import rootBuilder.common.Root;
import rootBuilder.common.RootList;

public class MenuEvents {
	
	public static void handleMntmOpenMRLFile(
			EntryList entryList,
			JTable dictEntries,
			JComboBox<Character> firstLetters,
			RootList rootList,
			RootJList rootJList) 
					throws IOException {
		
		// open dialogue to select mrl-file
		String[] filter = {"MRL Files","mrl"};
		FilePath filePath = new FilePath(filter,"Open mrl file",false);
		
		// read mrl-file as zip file
		ZipFile mrlFile = new ZipFile(filePath.getFilePath());
		
		// get wordlist.csv from mrl-file and load content into entryList and dictEntries
		ZipEntry zipWordlist = mrlFile.getEntry("wordlist.csv");
		InputStream wordListInput = mrlFile.getInputStream(zipWordlist);
		Reader wordListReader = new InputStreamReader(wordListInput, "UTF-8");
		entryList.fillEntryList(wordListReader);
		dictEntries.setModel(new DictEntriesModel(entryList,firstLetters));
		
		// get rootlist.csv from mrl-file and load content into rootList
		ZipEntry zipRootList = mrlFile.getEntry("rootlist.csv");
		InputStream rootListInput = mrlFile.getInputStream(zipRootList);
		Reader rootListReader = new InputStreamReader(rootListInput, "UTF-8");
		rootList.fillRootList(rootListReader);
		rootJList.setModel(new RootListModel(rootList));
		
		mrlFile.close();
	}
	
	public static void handleMntmLoadWordList(
			EntryList entryList,
			JTable dictEntries,
			JComboBox<Character> firstLetters) throws IOException
	{
		String[] filter = {"CSV Files","csv"};
		FilePath filePath = new FilePath(filter,"Open Entry List",false);
		Reader reader = Files.newBufferedReader(Paths.get(filePath.getFilePath()));
		entryList.fillEntryList(reader);
		dictEntries.setModel(new DictEntriesModel(entryList,firstLetters));
	}
	
	public static void handleMntmLoadRootList(
			RootList rootList,
			RootJList rootJList) throws IOException 
	{
		String[] filter = {"CSV Files","csv"};
		FilePath filePath = new FilePath(filter, "Open Root List",false);
		Reader reader = Files.newBufferedReader(Paths.get(filePath.getFilePath()));
		rootList.fillRootList(reader);
		rootJList.setModel(new RootListModel(rootList));
	}
	
	public static void handleMntmSaveMRLFile(EntryList entryList, RootList rootList) throws IOException {
		
		// create buffer for "wordlist.csv"
		ByteArrayOutputStream wordListStream = new ByteArrayOutputStream();
		OutputStreamWriter wordListWriter = new OutputStreamWriter(wordListStream,"UTF-8");
		try (
				CSVPrinter csvWordListPrinter = new CSVPrinter(wordListWriter, CSVFormat.DEFAULT
						.withHeader("ENTRY","ROOTENTRY","ROOT","HASROOT","V_IND"));
				) {
			for (Entry entry : entryList.getEntryList()) {
				if (entry.hasRoot()) csvWordListPrinter.printRecord(
							entry.getEntry(),
							entry.getRootEntry(),
							entry.getRoot().getRoot(),
							"1",
							entry.getRoot().getVowelIndex());
				else csvWordListPrinter.printRecord(
							entry.getEntry(),
							"---",
							"0",
							"0",
							"0");
			}
		}
		byte[] wordListBuffer = wordListStream.toByteArray();
		
		// create buffer for "rootlist.csv"
		ByteArrayOutputStream rootListStream = new ByteArrayOutputStream();
		OutputStreamWriter rootListWriter = new OutputStreamWriter(rootListStream,"UTF-8");
		try (
				CSVPrinter csvRootListPrinter = new CSVPrinter(rootListWriter, CSVFormat.DEFAULT
						.withHeader("ROOT","V_IND"));
				) {
			for (Root root : rootList.getRootList()) {
				csvRootListPrinter.printRecord(root.getRoot(),root.getVowelIndex());
			}
		}
		byte[] rootListBuffer = rootListStream.toByteArray();
		
		// saving dialog that returns the file path for mrl-file
		String[] filter = {"mrl Files","mrl"};
		FilePath filePath = new FilePath(filter,"Save mrl file",true);
		
		// create mrl-file
		File outputFile = new File(filePath.getFilePath());
		ZipOutputStream output = new ZipOutputStream(new FileOutputStream(outputFile));
		
		// write wordListBuffer into mrl-file
		output.putNextEntry(new ZipEntry("wordlist.csv"));
		output.write(wordListBuffer);
		output.closeEntry();
		
		// write rootListBuffer into mrl-file
		output.putNextEntry(new ZipEntry("rootlist.csv"));
		output.write(rootListBuffer);
		output.closeEntry();
		
		output.close();	
	}
	
	public static void handleMntmSaveWordList(EntryList entryList) throws IOException {
		String[] filter = {"CSV Files","csv"};
		FilePath filePathEntryList = new FilePath(filter,"Save Entry List",true);
		try (
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePathEntryList.getFilePath()));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
						.withHeader("ENTRY","ROOTENTRY","ROOT","HASROOT","V_IND"));
				) {
			
			for (Entry entry : entryList.getEntryList()) {
				if (entry.hasRoot()) csvPrinter.printRecord(
							entry.getEntry(),
							entry.getRootEntry(),
							entry.getRoot().getRoot(),
							"1",
							entry.getRoot().getVowelIndex());
				else csvPrinter.printRecord(
							entry.getEntry(),
							"---",
							"0",
							"0",
							"0");
			}
		}
	}
	
	public static void handleMntmSaveRootList(RootList rootList) throws IOException {
		String[] filter = {"CSV Files","csv"};
		FilePath filePathRootList = new FilePath(filter,"Save Root List",true);
		try (
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePathRootList.getFilePath()));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
						.withHeader("ROOT","V_IND"));
				) {
			for (Root root : rootList.getRootList()) {
				csvPrinter.printRecord(root.getRoot(),root.getVowelIndex());
			}
		}
	}
	
}
