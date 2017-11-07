package cleaning;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Mapping {


	static int count = 0;

	public static void main(String[] args) {
		
		File fout = new File("cleaning_output/matched_product_comment.txt");
		
		FileOutputStream fos;
		
		BufferedWriter bw = null;
		
		try {
			fos = new FileOutputStream(fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try{
			Scanner scanner = new Scanner(new File("cleaning_input/input_models.txt"));
			while (scanner.hasNextLine()) {
				countWord(scanner.nextLine(),new File("cleaning_input/input_amazon_product.txt"),bw);
			}
			scanner.close();
			System.out.println(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static int countWord(String word, File file, BufferedWriter bw) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String words[] = word.split(";");
		while (scanner.hasNextLine()) {
			String nextToken = scanner.nextLine();
			if (nextToken.contains(words[0]) && nextToken.contains(words[1])){
				if(words[1].length() == 1){
					if(nextToken.contains(words[0] + " " + words[1]) || nextToken.contains(words[0] + words[1])){
						//System.out.println(word + ":" + nextToken);
						writeToFile(nextToken, words[0], words[1],bw);
						count++;
					}
				}
				else if(nextToken.contains("plus") || nextToken.contains("Plus")){
					if(words[1].contains("plus") || words[1].contains("Plus")){
						//System.out.println(word + ":" + nextToken);
						//System.out.println(words[1]);
						writeToFile(nextToken, words[0], words[1],bw);
						count++;
					}
				}else if(nextToken.contains("Duos")){
					if(words[1].contains("Duos")){
						//System.out.println(word + ":" + nextToken);
						//System.out.println(words[1]);
						writeToFile(nextToken, words[0], words[1],bw);
						count++;
					}
				}
				else{
					checkForTheConditions(nextToken, words[0],words[1],bw);
					//System.out.println(words[1]);
					//System.out.println(word + ":" + nextToken);
					count++;
				}
			}

		}
		scanner.close();
		return count;
	}

	private static void checkForTheConditions(String nextToken, String company, String model, BufferedWriter bw) {
		if(model.equalsIgnoreCase("Blade")){
			if(!(nextToken.toLowerCase().contains("Blade G".toLowerCase()) ||  nextToken.contains("Blade G Lux".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.equalsIgnoreCase("Blade G")){
			if(!(nextToken.toLowerCase().contains("Blade G Lux".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.equalsIgnoreCase("C3")){
			if(!nextToken.toLowerCase().contains("C3520".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.equalsIgnoreCase("C5")){
			if(!nextToken.toLowerCase().contains("C5-03".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.equals("Dash")){
			if(!(nextToken.toLowerCase().contains("Dash 3.5".toLowerCase()) || 
					nextToken.toLowerCase().contains("Dash 3G".toLowerCase()) || 
					nextToken.toLowerCase().contains("Dash 4.0".toLowerCase()) || 
					nextToken.toLowerCase().contains("Dash 5.0".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash C Music".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash JR".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash L".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash M".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash Music".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash Music JR".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash X".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash X LTE".toLowerCase())|| 
					nextToken.toLowerCase().contains("Dash X Plus".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.equalsIgnoreCase("Dash M")){
			if(!(nextToken.toLowerCase().contains("Dash Music".toLowerCase()) ||  nextToken.toLowerCase().contains("Dash Music JR".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.equalsIgnoreCase("Dash Music".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Dash Music JR".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Dash X".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Dash X LTE") ||  nextToken.toLowerCase().contains("Dash X Plus".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Deco".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Deco Pro".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Desire".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Desire 610".toLowerCase()) || 
					nextToken.toLowerCase().contains("Desire 625".toLowerCase()) || 
					nextToken.toLowerCase().contains("Desire 626".toLowerCase()) || 
					nextToken.toLowerCase().contains("Desire 626G+".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire 626s".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire 630".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire 816".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire 820".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire C".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire Eye".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire HD".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire S".toLowerCase())|| 
					nextToken.toLowerCase().contains("Desire U".toLowerCase())||
					nextToken.toLowerCase().contains("Desire X".toLowerCase())
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Desire 626".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Desire 626G+".toLowerCase()) ||  nextToken.toLowerCase().contains("Desire 626s".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("E5".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("E51".toLowerCase()) ||  nextToken.toLowerCase().contains("E52".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("E6".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("E63".toLowerCase()) ||
					nextToken.toLowerCase().contains("E71".toLowerCase()) || 
					nextToken.toLowerCase().contains("E66".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("E7".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("E71".toLowerCase()) ||
					nextToken.toLowerCase().contains("E72".toLowerCase()) || 
					nextToken.toLowerCase().contains("E75".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Evo 4G".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Evo 4G LTE".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("G Flex".toLowerCase())){
			if(!nextToken.toLowerCase().contains("G Flex2".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("G Vista".toLowerCase())){
			if(!nextToken.toLowerCase().contains("G Vista 2".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("G3".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("G3 S".toLowerCase()) ||
					nextToken.toLowerCase().contains("G3 Stylus".toLowerCase()) ||
					nextToken.toLowerCase().contains("G360".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("G3 S".toLowerCase())){
			if(!nextToken.toLowerCase().contains("G3 Stylus".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("G4".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("G4 Beat".toLowerCase()) || 
					nextToken.toLowerCase().contains("G4 Dual".toLowerCase()) ||
					nextToken.toLowerCase().contains("G4 Stylus".toLowerCase())||
					nextToken.toLowerCase().contains("G4c".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("G6".toLowerCase())){
			if(!nextToken.toLowerCase().contains("G600".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Galaxy A".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Galaxy A3".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy A3 (2016)".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy A5".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy A5 (2016)".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy A7".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy A7 (2016)".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy A7 Duos".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy A8".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy A8 Duos".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy Ace 3".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy Ace 4".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy Alpha".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy Appeal I827".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Galaxy J".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Galaxy J1".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy J1 (2016)".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy J1 Ace".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy J2).toLowerCase()")|| 
					nextToken.toLowerCase().contains("Galaxy J3 (2016)".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy J5".toLowerCase())|| 
					nextToken.toLowerCase().contains("Galaxy J7".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Galaxy J1".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Galaxy J1 (2016)".toLowerCase()) || 
					nextToken.toLowerCase().contains("Galaxy J1 Ace".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Galaxy S5".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Galaxy S5 Active".toLowerCase()) ||  
					nextToken.toLowerCase().contains("Galaxy S5 Neo".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Gear S".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Gear S2".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Jenny".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Jenny TV".toLowerCase()) 
					||  nextToken.toLowerCase().contains("Jenny TV 2.8".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Jenny TV".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Jenny TV 2.8".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("K3".toLowerCase())){
			if(!nextToken.toLowerCase().contains("K3 Note".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("K5".toLowerCase())){
			if(!nextToken.toLowerCase().contains("K550".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("K7".toLowerCase())){
			if(!nextToken.toLowerCase().contains("K790".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("L6".toLowerCase())){
			if(!nextToken.toLowerCase().contains("L600".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("Life 8".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Life 8 XL".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Life One".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Life One X".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Life Play".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Life Play Mini".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Life Pure".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Life Pure XL".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Liquid".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Liquid Jade".toLowerCase())||
					nextToken.toLowerCase().contains("Liquid Jade Z".toLowerCase())||
					nextToken.toLowerCase().contains("Liquid M220".toLowerCase())||
					nextToken.toLowerCase().contains("Liquid Z2".toLowerCase())||
					nextToken.toLowerCase().contains("Liquid Z200".toLowerCase())||
					nextToken.toLowerCase().contains("Liquid Z4".toLowerCase())||
					nextToken.toLowerCase().contains("Liquid Z410".toLowerCase())
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Liquid Jade".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Liquid Jade Z".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Liquid Z2".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Liquid Z200".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Liquid Z4".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Liquid Z410".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Lumia 640 XL".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Lumia 640 XL LTE".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Lumia 950".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Lumia 950 XL".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("M1".toLowerCase())){
			if(!nextToken.toLowerCase().contains("M1 Note".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("MX".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("MX4".toLowerCase()) ||  nextToken.toLowerCase().contains("MX4 Pro".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("MX4".toLowerCase())){
			if(!nextToken.toLowerCase().contains("MX4 Pro".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Marauder".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Marquee LS855".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Might".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Might Plus".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Moto G".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Moto G4".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Moto X".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Moto X Force".toLowerCase()) ||
					nextToken.toLowerCase().contains("Moto X Play".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("N8".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("N81".toLowerCase()) ||
					nextToken.toLowerCase().contains("N82".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("N9".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("N90".toLowerCase()) ||
					nextToken.toLowerCase().contains("N900".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("N90".toLowerCase())){
			if(!nextToken.toLowerCase().contains("N900".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("N95".toLowerCase())){
			if(!nextToken.toLowerCase().contains("N95 8GB".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Neo".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Neo X".toLowerCase()) ||
					nextToken.toLowerCase().contains("Neo X Plus".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Neo X".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Neo X Plus".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Nexus 5".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Nexus 5X".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Nexus 6".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Nexus 6P".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("one".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("One (M8)".toLowerCase()) || 
					nextToken.toLowerCase().contains("One A9".toLowerCase()) || 
					nextToken.toLowerCase().contains("One E9".toLowerCase()) || 
					nextToken.toLowerCase().contains("One M9".toLowerCase()) || 
					nextToken.toLowerCase().contains("One ME".toLowerCase()) || 
					nextToken.toLowerCase().contains("One Max".toLowerCase()) || 
					nextToken.toLowerCase().contains("One S".toLowerCase()) || 
					nextToken.toLowerCase().contains("One V".toLowerCase()) || 
					nextToken.toLowerCase().contains("One VX".toLowerCase()) || 
					nextToken.toLowerCase().contains("One X".toLowerCase()) || 
					nextToken.toLowerCase().contains("One X+".toLowerCase()) || 
					nextToken.toLowerCase().contains("One X9".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Optimus F3".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Optimus F3Q".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("P1".toLowerCase())){
			if(!nextToken.toLowerCase().contains("P100".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Pixel".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Pixel XL".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Pre".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Pre 2".toLowerCase())||
					nextToken.toLowerCase().contains("Pre Plus".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Pure".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Pure XL".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Pursuit".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Pursuit II".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Quattro".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Quattro 4.5".toLowerCase())||
					nextToken.toLowerCase().contains("Quattro 4.5 HD".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Quattro 4.5".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Quattro 4.5 HD".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Redmi".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Redmi Note".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Samba".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Samba TV".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Sensation".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Sensation 4G".toLowerCase())||
					nextToken.toLowerCase().contains("Sensation XE".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Sidekick".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Sidekick LX".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio 5.0".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Studio 5.0 C".toLowerCase())||
					nextToken.toLowerCase().contains("Studio 5.0 C HD".toLowerCase())||
					nextToken.toLowerCase().contains("Studio 5.0 HD LTE".toLowerCase())||
					nextToken.toLowerCase().contains("Studio 5.0 II".toLowerCase())||
					nextToken.toLowerCase().contains("Studio 5.0 LTE".toLowerCase())||
					nextToken.toLowerCase().contains("Studio 5.0 S".toLowerCase())||
					nextToken.toLowerCase().contains("Studio 5.0 S II".toLowerCase())
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio 5.0 S".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Studio 5.0 S II".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio 5.3".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Studio 5.3 S".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio 5.5".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Studio 5.5 HD".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio 7.0".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Studio 7.0 II".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio 5.5".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Studio 5.5 HD".toLowerCase()) ||
					nextToken.toLowerCase().contains("Studio 5.5C".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio C".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Studio C 8+8".toLowerCase()) ||
					nextToken.toLowerCase().contains("Studio C Super Camera".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio G".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Studio G HD".toLowerCase()) || 
					nextToken.toLowerCase().contains("Studio G LTE".toLowerCase()) 
					|| nextToken.toLowerCase().contains("Studio G Plus".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Studio X".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Studio X8 HD".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Sync".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Sync 5.0".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Tank".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Tank 4.5".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Touch".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Touch 3G".toLowerCase()) ||
					nextToken.toLowerCase().contains("Touch Diamond".toLowerCase()) 
					|| nextToken.toLowerCase().contains("Touch Dual".toLowerCase()) ||
					nextToken.toLowerCase().contains("Touch Pro".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Trigger".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Trigger Plus".toLowerCase()) || 
					nextToken.toLowerCase().contains("Trigger Pro".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Veer".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Veer 4G".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("Vibe K5".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Vibe K5 Plus".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Vivo Air".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Vivo Air LTE".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("W8".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("W810".toLowerCase()) || 
					nextToken.toLowerCase().contains("W880".toLowerCase()) ||
					nextToken.toLowerCase().contains("W890".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Watch".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Watch 38mm".toLowerCase()) || 
					nextToken.toLowerCase().contains("Watch 42mm".toLowerCase()) ||
					nextToken.toLowerCase().contains("Watch Phone".toLowerCase()) ||
					nextToken.toLowerCase().contains("Watch Sport 38mm".toLowerCase()) ||
					nextToken.toLowerCase().contains("Watch Sport 42mm".toLowerCase())
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Wildfire".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Wildfire S".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("X3".toLowerCase())){
			if(!nextToken.toLowerCase().contains("X3-02 Touch and Type".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia E".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia E1".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia M".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Xperia M2".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia M2 Aqua".toLowerCase()) 
					|| nextToken.toLowerCase().contains("Xperia M4 Aqua".toLowerCase()) 
					|| nextToken.toLowerCase().contains("Xperia M5".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia M2".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia M2 Aqua".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia P".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia PLAY".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia T".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia T2 Ultra".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia X".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Xperia X Compact".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia X Performance".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia XA".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia XA Dual".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia XA Ultra".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia XZ".toLowerCase())
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia XA".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Xperia XA Dual".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia XA Ultra".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia Z".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("Xperia Z Ultra".toLowerCase()) ||
					nextToken.toLowerCase().toLowerCase().contains("Xperia Z1".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z1 Compact".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z2".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z2a".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z3".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z3 Compact".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z3+".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z3v".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z5".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z5 Compact".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z5 Dual".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z5 Premium".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia Z5 Premium Dual".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia ZL".toLowerCase()) ||
					nextToken.toLowerCase().contains("Xperia ZR".toLowerCase())
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia Z1".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia Z1 Compact".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia Z2".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia Z2a".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia Z3".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia Z3 Compact".toLowerCase())||
					nextToken.toLowerCase().contains("Xperia Z3+".toLowerCase())|| 
					nextToken.toLowerCase().contains("Xperia Z3v".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Xperia Z5".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Xperia Z5 Compact".toLowerCase())||
					nextToken.toLowerCase().contains("Xperia Z5 Dual".toLowerCase())|| 
					nextToken.toLowerCase().contains("Xperia Z5 Premium".toLowerCase())|| 
					nextToken.toLowerCase().contains("Xperia Z5 Premium Dual".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("Z3".toLowerCase())){
			if(!nextToken.toLowerCase().contains("Z30".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("iPhone".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("iPhone 3G".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 3GS".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 4".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 4s".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 5".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 5c".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 5s".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 6".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 6 Plus".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 6s".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 6s Plus".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 7".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 7 Plus".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone SE".toLowerCase()) 
					)){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("iPhone 3G".toLowerCase())){
			if(!nextToken.toLowerCase().contains("iPhone 3GS".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("iPhone 4".toLowerCase())){
			if(!nextToken.toLowerCase().contains("iPhone 4s".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}
		else if(model.toLowerCase().equals("iPhone 5".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("iPhone 5c".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 5s".toLowerCase()) )){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("iPhone 6".toLowerCase())){
			if(!(nextToken.toLowerCase().contains("iPhone 6 Plus".toLowerCase()) ||
					nextToken.toLowerCase().contains("iPhone 6s".toLowerCase()) || 
				 nextToken.toLowerCase().contains("iPhone 6s Plus".toLowerCase()))){
				writeToFile(nextToken,company,model,bw);
			}
		}else if(model.toLowerCase().equals("s4010".toLowerCase())){
			if(!nextToken.toLowerCase().contains("s4010 Gazelle".toLowerCase())){
				writeToFile(nextToken,company,model,bw);
			}
		}else{
			writeToFile(nextToken,company,model,bw);
		}

	}

	private static void writeToFile(String nextToken, String company, String model,BufferedWriter bw){
		String output = company + "|" + model + "|" + nextToken;
		System.out.println(output);
		try{
			bw.write(output);
			bw.newLine();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
