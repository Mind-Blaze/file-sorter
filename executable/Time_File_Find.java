package executable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class Time_File_Find {
	
	public static String Time_finder(String directory,String type) throws IOException {
		String test = "";
		
        String creation_filename = directory;

        File creation_my_file = new File(creation_filename);

        Path creation_path = creation_my_file.toPath();

        BasicFileAttributes creation_file_att = Files.readAttributes(creation_path, BasicFileAttributes.class);

        SimpleDateFormat creation_sd = new SimpleDateFormat(type);

        String creation_date = creation_sd.format(creation_file_att.creationTime().toMillis());
        
        
        String Modification_filename = directory;

        File Modification_my_file = new File(Modification_filename);

        Path Modification_path = Modification_my_file.toPath();

        BasicFileAttributes Modification_file_att = Files.readAttributes(Modification_path, BasicFileAttributes.class);

        SimpleDateFormat Modification_sd = new SimpleDateFormat(type);

        String Modification_date = Modification_sd.format(Modification_file_att.lastModifiedTime().toMillis());
        
        if(Integer.parseInt(creation_date)<=Integer.parseInt(Modification_date)) {
        	test = creation_date;
        }
        else {
        	test = Modification_date;
        }  
        	
		return test;
	}
	public static String Milisecond_finder(String directory) throws IOException {
		String filename = directory;

        File my_file = new File(filename);

        Path path = my_file.toPath();

        BasicFileAttributes file_att = Files.readAttributes(path, BasicFileAttributes.class);

        SimpleDateFormat sd = new SimpleDateFormat("SSS");

        String date = sd.format(file_att.creationTime().toMillis());
		
		return date;
	}
	public static String Complet_date(String directory) throws IOException {
		
		String sec = Time_File_Find.Time_finder(directory,"ss");
		String min = Time_File_Find.Time_finder(directory,"mm");
		String hour = Time_File_Find.Time_finder(directory,"HH");
		String day = Time_File_Find.Time_finder(directory,"dd");
		String month = Time_File_Find.Time_finder(directory,"MM");
		String year = Time_File_Find.Time_finder(directory,"yyyy");
		
		// dd-MM-yyyy  HHh mmmin sssec
		// 05-08-2022  15h 32min 06sec
		
		String complet = day+"-"+month+"-"+year+"  "+hour+"h "+min+"min "+sec+"sec" ;	 	
		
		return complet;
	}
	
	public static String converter(String num_month) {
		String letter_month = "";


		
		if(num_month.contains("01")) {
			letter_month = "Janvier";
		}
		else if(num_month.contains("02")) {
			letter_month = "Février";
		}
		else if(num_month.contains("03")) {
			letter_month = "Mars";
		}
		else if(num_month.contains("04")) {
			letter_month = "Avril";
		}
		else if(num_month.contains("05")) {
			letter_month = "Mai";
		}
		else if(num_month.contains("06")) {
			letter_month = "Juin";
		}
		else if(num_month.contains("07")) {
			letter_month = "Juillet";
		}
		else if(num_month.contains("08")) {
			letter_month = "Août";
		}
		else if(num_month.contains("09")) {
			letter_month = "Septembre";
		}
		else if(num_month.contains("10")) {
			letter_month = "Novembre";
		}
		else if(num_month.contains("11")) {
			letter_month = "Octobre";
		}
		else if(num_month.contains("12")) {
			letter_month = "Decembre";
		}
		else {
			letter_month = "test";
		}

		return letter_month;
	}

}