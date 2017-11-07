package cleaning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.csvreader.CsvWriter;

public class CreateCSV {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';
	private static int count = 0;

	public static void main(String[] args) throws Exception {

		String outputCsvFile = "comments.csv";

		String csvFile = "Amazon_Unlocked_Mobile.csv";

		String inputFile = "cleaning_output/matched_product_comment.txt";
		
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputCsvFile).exists();
		
		// use FileWriter constructor that specifies open for appending
		CsvWriter csvOutput = new CsvWriter(new FileWriter(outputCsvFile, true), ',');
		
		//Things to output
		//brand,model,rating(3),review(4)
		if (!alreadyExists)
		{
			csvOutput.write("Brand");
			csvOutput.write("Model");
			csvOutput.write("Rating");
			csvOutput.write("Review");
			csvOutput.endRecord();
		}

		//commentsForProduct(csvFile);
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] arr = line.split("\\|");
				StringBuilder toSearch = new StringBuilder();
				if(arr.length > 3){
					int i = 2;
					while(i!= arr.length-1){
						toSearch.append(arr[i]);
						i++;
					}
				}else{
					toSearch.append(arr[2]);
				}
				commentsForProduct(csvFile,toSearch.toString(),arr[0],arr[1],csvOutput);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		csvOutput.close();
		
		System.out.println(count);

	}

	private static void commentsForProduct(String csvFile, String searchTerm, String brand, String model,CsvWriter csvOutput) {
		String line_l = null;
		

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			boolean matched = false;
			while ((line_l = br.readLine()) != null) {
				List<String> line = parseLine(line_l);
				if(line.get(0).contains(searchTerm)){
					matched = true;
					count++;
					csvOutput.write(brand);
					csvOutput.write(model);
					csvOutput.write(line.get(3));
					csvOutput.write(line.get(4));
					csvOutput.endRecord();
					System.out.println("Brand=" + brand + ", Model="+ model + " , Rating=" + line.get(3) + " , Review=" + line.get(4));
				}else if(matched == true){
					break;
				}	
			}
		}catch (Exception e) {
//			System.out.println(count);
			System.out.println(line_l);
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
