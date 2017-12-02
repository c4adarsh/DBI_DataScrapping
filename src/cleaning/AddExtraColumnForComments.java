package cleaning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.csvreader.CsvWriter;

public class AddExtraColumnForComments {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';
	private static int count = 0;
	private static int global_count = 0;
	public static String[] states = {"CALIFORNIA","FLORIDA", "TEXAS", "ARIZONA", "NEW YORK"};

	public static void main(String[] args) throws Exception {

		String outputCsvFile = "comments_with_loc.csv";

		String csvFile = "comments_added_column_new_1.csv";

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
			
//			csvOutput.write(line.get(0) + " " + line.get(1) + " comment " + count );
//			csvOutput.write(line.get(0) + " " + line.get(1) );
//			csvOutput.write(line.get(0));
//			csvOutput.write(line.get(1));
//			csvOutput.write(line.get(2));
//			csvOutput.write(line.get(3));
			
			csvOutput.write(line.get(0));
			csvOutput.write(line.get(1));
			csvOutput.write(line.get(2));
			csvOutput.write(line.get(3));
			csvOutput.write(line.get(4));
			csvOutput.write(line.get(5));
			csvOutput.write(states[(int)(Math.random() * 4)]);
			csvOutput.endRecord();
			System.out.println(states.length);
			System.out.println(states[(int)(Math.random() * 4)]);
			count++;
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

