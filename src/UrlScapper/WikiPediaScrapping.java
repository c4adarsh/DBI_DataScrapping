package UrlScapper;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPediaScrapping {

	public static void main(String[] args){

		try{
			Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Acer_Inc.").get();
			Element body = doc.body();
			Elements tables = body.getElementsByTag("table");
			for (Element table : tables) {
				if (table.className().contains("infobox")==true) {
					Elements tableBodies = table.getElementsByTag("tbody");
					for (Element tableBody : tableBodies) {
						//System.out.println(tableBody); ins
					}
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
