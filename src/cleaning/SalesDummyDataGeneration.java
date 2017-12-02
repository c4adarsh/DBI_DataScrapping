package cleaning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.csvreader.CsvWriter;

public class SalesDummyDataGeneration {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';
	private static int count = 0;
	private static int global_count = 0;

	public static void main(String[] args) throws Exception {

		String outputCsvFile = "dummy_data_generated_new.csv";

		String csvFile = "dummy_data_generator.csv";

		// use FileWriter constructor that specifies open for appending
		CsvWriter csvOutput = new CsvWriter(new FileWriter(outputCsvFile, true), ',');

		commentsForProduct(csvFile,csvOutput);

		csvOutput.close();

		System.out.println(global_count);

	}

	private static void commentsForProduct(String csvFile, CsvWriter csvOutput) {
		String line_l = null;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line_l = br.readLine()) != null) {
				List<String> line = parseLine(line_l);
				writeCSV(line,csvOutput);

			}
		}catch (Exception e) {
			//			System.out.println(count);
			System.out.println(line_l);
			e.printStackTrace();
		}
	}
	
	
	public static void writeCSV(List<String> line, CsvWriter csvOutput ){
		try{
			/*csvOutput.write(line.get(0) + " " + line.get(1) );
			csvOutput.write(line.get(0));
			csvOutput.write(line.get(1));
			csvOutput.write(line.get(2));
			csvOutput.write(line.get(3));
			csvOutput.write(line.get(4));
			csvOutput.write(line.get(5));
			csvOutput.write(line.get(6));
			csvOutput.write(line.get(7));
			csvOutput.write(line.get(8));
			csvOutput.write(line.get(9));
			csvOutput.write(line.get(10));
			csvOutput.write(line.get(11));
			csvOutput.endRecord();*/
			System.out.println(line.get(0));
			if(line.get(1).equals("HTC")  || line.get(1).equals("Huwaie")  ||line.get(1).equals("Apple") 
					||line.get(1).equals("Samsung")  || line.get(1).equals("Motorola")  ||line.get(1).equals("Xiaomi")){
				csvOutput.write(line.get(0) + " 1");
				csvOutput.write(line.get(0));
				csvOutput.write(line.get(1));
				csvOutput.write(line.get(2));
				if(line.get(1).equals("Samsung")){
					csvOutput.write(String.valueOf(0.5 + Math.random() * 2));
				}else{
					csvOutput.write(String.valueOf(2 + Math.random() * 2));
				}
				if(line.get(1).equals("Samsung")){
					csvOutput.write(String.valueOf(1 + Math.random() * 2));
				}else{
					csvOutput.write(String.valueOf(2 + Math.random() * 2));
				}
				csvOutput.write(String.valueOf(2 + Math.random() * 2));

				csvOutput.endRecord();
			}else{
				csvOutput.write(line.get(0) + " 1");
				csvOutput.write(line.get(0));
				csvOutput.write(line.get(1));
				csvOutput.write(line.get(2));
				csvOutput.write(String.valueOf(Math.random() * 1));
				csvOutput.write(String.valueOf(Math.random() * 1));
				csvOutput.write(String.valueOf(Math.random() * 1));
				csvOutput.endRecord();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static List<String> parseLine(String cvsLine) {
		return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	}

	public static List<String> parseLine(String cvsLine, char separators) {
		return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	}

	public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

		List<String> result = new ArrayList<>();

		//if empty, return!
		if (cvsLine == null && cvsLine.isEmpty()) {
			return result;
		}

		if (customQuote == ' ') {
			customQuote = DEFAULT_QUOTE;
		}

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuffer curVal = new StringBuffer();
		boolean inQuotes = false;
		boolean startCollectChar = false;
		boolean doubleQuotesInColumn = false;

		char[] chars = cvsLine.toCharArray();

		for (char ch : chars) {

			if (inQuotes) {
				startCollectChar = true;
				if (ch == customQuote) {
					inQuotes = false;
					doubleQuotesInColumn = false;
				} else {

					//Fixed : allow "" in custom quote enclosed
					if (ch == '\"') {
						if (!doubleQuotesInColumn) {
							curVal.append(ch);
							doubleQuotesInColumn = true;
						}
					} else {
						curVal.append(ch);
					}

				}
			} else {
				if (ch == customQuote) {

					inQuotes = true;

					//Fixed : allow "" in empty quote enclosed
					if (chars[0] != '"' && customQuote == '\"') {
						curVal.append('"');
					}

					//double quotes in column will hit this!
					if (startCollectChar) {
						curVal.append('"');
					}

				} else if (ch == separators) {

					result.add(curVal.toString());

					curVal = new StringBuffer();
					startCollectChar = false;

				} else if (ch == '\r') {
					//ignore LF characters
					continue;
				} else if (ch == '\n') {
					//the end, break!
					break;
				} else {
					curVal.append(ch);
				}
			}

		}

		result.add(curVal.toString());

		return result;
	}

}

