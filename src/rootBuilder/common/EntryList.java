package rootBuilder.common;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class EntryList {
	
	private ArrayList<Entry> entryList;
	
	public EntryList(ArrayList<Entry> entryList) {
		this.entryList = entryList;
	}

	public ArrayList<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(ArrayList<Entry> entryList) {
		this.entryList = entryList;
	}
	
	public void fillEntryList(Reader reader) throws IOException {
		
		try (
				CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT
						.withHeader()
	                    .withIgnoreHeaderCase()
	                    .withTrim());
				) {
				
			for (CSVRecord csvRecord : csvParser){
	            
				// Get the values from the columns
				String entryLabel = csvRecord.get("ENTRY");
				String rootLabel = csvRecord.get("ROOTENTRY");
				String rootRadicals = csvRecord.get("ROOT");
				boolean hasRootIndicator = Integer.parseInt(csvRecord.get("HASROOT")) == 1;
				int vowelIndicator = Integer.parseInt(csvRecord.get("V_IND"));
				
				Entry entry = new Entry(entryLabel);
				
				if (hasRootIndicator) {
					entry.setRoot(new Root(rootRadicals,vowelIndicator));
					entry.setRootEntry(rootLabel);
				}
				
				this.entryList.add(entry);
			} 
		}
	}
}
