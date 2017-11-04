package UrlScapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrapeDeviceSpecifications {

	public static void main(String[] args) {

		String file = "input_links.txt";

		File fout = new File("output_features.txt");

		FileOutputStream fos;

		BufferedWriter bw = null;

		//get all the file names from a given folder

		File folder = new File("input/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(listOfFiles[i].getName());
				file = "input/" + listOfFiles[i].getName();
				fout = new File("output/" + listOfFiles[i].getName());
				startFetching(file, fout, bw);
			} else if (listOfFiles[i].isDirectory()) {
				//System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		
		
		/*file = "input/texet.txt";
		fout = new File("output/texet.txt");
		startFetching(file, fout, bw);*/
		


	}

	private static void startFetching(String file, File fout, BufferedWriter bw) {
		FileOutputStream fos;
		// create a output file writer
		try {
			fos = new FileOutputStream(fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;

			while ((line = br.readLine()) != null) {
				if(line.trim().length() == 0){
					break;
				}
				int a = line.indexOf("https:");
				String query = line.substring(a);
				String name = line.substring(0, a).trim();
				URL url = new URL(query);
				BufferedReader response = null;
				HttpURLConnection conn = null;

				try{
					HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
					httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
					httpcon.setRequestMethod("GET");
					response = new BufferedReader(new InputStreamReader(
							(httpcon.getInputStream())));
					parseResponse(response,bw,name);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}


		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try{
			Thread.sleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void parseResponse(BufferedReader response, BufferedWriter bw, String name) throws IOException {
		String line;
		boolean copy = false;
		boolean image_fetching = false;
		boolean price_link_fetching = false;

		StringBuilder mBuilder = new StringBuilder();
		StringBuilder imageUriBuilder = new StringBuilder();
		StringBuilder priceBuilder = new StringBuilder();

		while ((line = response.readLine()) != null) {

			if(line.contains("model-image") || image_fetching){
				image_fetching = true;
				imageUriBuilder.append(line.trim());
			}
			if(line.contains("href") && image_fetching){
				image_fetching = false;
				price_link_fetching = true;
				priceBuilder.append(line.trim());
			}

			if(price_link_fetching == true){
				priceBuilder.append(line.trim());
			}

			if(line.contains("model-brief-specifications") || copy && !line.contains("href")){
				image_fetching = false;
				price_link_fetching = false;
				copy = true;
				mBuilder.append(line.trim()).append("\n");
			}
			if(line.contains("href") && copy){
				break;
			}
		}

		String mString = mBuilder.toString();
		String[] mArray = mString.split("<br />");
		StringBuilder resultBuilder = new StringBuilder();
		for(String eachItem : mArray){
			eachItem = eachItem.replaceAll("</b>", "");
			eachItem = eachItem.replaceAll("<b>", "");
			if(!eachItem.contains("<div") && eachItem.trim().length()!=0){
				System.out.println(eachItem);
				resultBuilder.append(eachItem).append("\n");
			}
		}

		String priceUrl = getPriceUrl(priceBuilder);
		String imageUrl = getImage(imageUriBuilder);

		resultBuilder.append("priceUrl=" + priceUrl.trim()).append("\n");
		resultBuilder.append("imageUrl=" + imageUrl.trim()).append("\n");
		resultBuilder.append("name=" + name.trim() + ";").append("\n");
		bw.write(resultBuilder.toString());
		bw.newLine();
	}

	private static String getImage(StringBuilder imageUriBuilder) {
		int startingIndex = imageUriBuilder.indexOf("https");
		int endingIndex = imageUriBuilder.indexOf(")");
		if(startingIndex== -1 || endingIndex == -1){
			return " ";
		}
		return imageUriBuilder.substring(startingIndex, endingIndex).toString();
	}

	private static String getPriceUrl(StringBuilder priceBuilder) {
		int startingIndex = priceBuilder.indexOf("https");
		int endingIndex = priceBuilder.indexOf("\" style");
		if(startingIndex== -1 || endingIndex == -1){
			return " ";
		}
		return priceBuilder.substring(startingIndex, endingIndex).toString();
	}

}
