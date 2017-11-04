package UrlScapper;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.csvreader.CsvWriter;

public class ConvertToCSV {

	static HashMap<String,Integer> mHashMap = new HashMap<>();
	
	static String errorFile = null;

	public static void main(String[] args) {

		String outputFile = "new.csv";

		initializeMap();

		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();

		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Brand");
				csvOutput.write("Weight");
				csvOutput.write("SoC");
				csvOutput.write("CPU");
				csvOutput.write("GPU");
				csvOutput.write("RAM");
				csvOutput.write("Storage");
				csvOutput.write("Memory cards");
				csvOutput.write("Display");
				csvOutput.write("Battery");
				csvOutput.write("OS");
				csvOutput.write("Camera");
				csvOutput.write("SIM card");
				csvOutput.write("Wi-Fi");
				csvOutput.write("USB");
				csvOutput.write("Bluetooth");
				csvOutput.write("Positioning");
				csvOutput.write("priceUrl");
				csvOutput.write("imageUrl");
				csvOutput.write("name");
				csvOutput.endRecord();
			}


			File folder = new File("output/");
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					writeToBuffer("output/" + listOfFiles[i].getName(), csvOutput);
				} else if (listOfFiles[i].isDirectory()) {
					//System.out.println("Directory " + listOfFiles[i].getName());
				}
			}

			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void writeToBuffer(String file, CsvWriter csvOutput) {
		errorFile = file;
		int index = file.indexOf("/");
		int startIndex = index + 1;
		int endIndex = file.indexOf(".txt");
		String brand = file;
		try{
			brand = file.substring(startIndex, endIndex);
		}catch (StringIndexOutOfBoundsException e) {
			brand = file.substring(startIndex);
		}
		

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				if(line.trim().length() == 0)
					continue;


				if(i == 0){
					csvOutput.write(brand);
					i = i + 1;
				}

				if(line.indexOf("priceUrl=")!=-1){
					line = line.replace("priceUrl=", "priceUrl:");
				}

				if(line.indexOf("imageUrl=")!=-1){
					line = line.replace("imageUrl=", "imageUrl:");
				}

				else if(line.indexOf("name=")!=-1){
					line = line.replace("name=", "name:");
				}


				int indexOfColon = line.indexOf(":");

				if(indexOfColon == line.length() - 1){
					i = appendToBuffer(i,line.substring(0, indexOfColon) ," ",csvOutput );
				}else{
					i = appendToBuffer(i,line.substring(0,indexOfColon) ,line.substring(indexOfColon+1),csvOutput);
				}
				i++;
				if(i == 20){
					csvOutput.endRecord();
					i=0;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static int appendToBuffer(int i, String string, String result, CsvWriter csvOutput) throws Exception {

		if(mHashMap.get(string) == null){
			System.out.println(string);
			i--;
			return i;
		}

		if(mHashMap.get(string) == i){
			if(result.length()==0){
				csvOutput.write(" ");
			}else{
				csvOutput.write(result);
			}
		}else{
			while(mHashMap.get(string) != i){
				csvOutput.write(" ");
				i++;
			}
			csvOutput.write(result);
		}
		return i;
	}

	private static void initializeMap() {
		mHashMap.put("Brand", 0);
		mHashMap.put("Weight",1);
		mHashMap.put("SoC",2);
		mHashMap.put("CPU",3);
		mHashMap.put("GPU",4);
		mHashMap.put("RAM",5);
		mHashMap.put("Storage",6);
		mHashMap.put("Memory cards",7);
		mHashMap.put("Display",8);
		mHashMap.put("Battery",9);
		mHashMap.put("OS",10);
		mHashMap.put("Camera",11);
		mHashMap.put("SIM card",12);
		mHashMap.put("Wi-Fi",13);
		mHashMap.put("USB",14);
		mHashMap.put("Bluetooth",15);
		mHashMap.put("Positioning",16);
		mHashMap.put("priceUrl",17);
		mHashMap.put("imageUrl",18);
		mHashMap.put("name",19);		
	}
}
