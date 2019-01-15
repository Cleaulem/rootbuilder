package rootBuilder.common;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class RootList {
	
	private ArrayList<Root> rootList;
	
	public RootList (ArrayList<Root> rootList) {
		this.rootList = rootList;
	}

	public ArrayList<Root> getRootList() {
		return rootList;
	}

	public void setRootList(ArrayList<Root> rootList) {
		this.rootList = rootList;
	}
	
	public void fillRootList(Reader reader) throws IOException {
		
		try (
				CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT
						.withHeader()
	                    .withIgnoreHeaderCase()
	                    .withTrim());
				) {
				
			for (CSVRecord csvRecord : csvParser) {
				
				//Get the values from the columns
				String rootEntry = csvRecord.get("ROOT");
				int vowelIndicator = Integer.parseInt(csvRecord.get("V_IND"));
				this.rootList.add(new Root(rootEntry, vowelIndicator));
			}
		}
	}
}
