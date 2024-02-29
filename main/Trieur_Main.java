package main;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import executable.File_Copier;
import executable.Gui;
import executable.Time_File_Find;

public class Trieur_Main {
	public static void main(String[] args) throws IOException, InterruptedException{

		String start_in_directory = "Entrez le chemin";
		String start_out_directory = "Entrez le chemin";
		String extention = "Entrez une extention";
		int[] copy = {0};
		
		Gui gui = new Gui();
		gui.start_frame(start_in_directory, start_out_directory, extention, copy[0], 0);
		
		while (true) {
			TimeUnit.MILLISECONDS.sleep(500);
			while (gui.start == true) {
				start_in_directory = gui.start_in_directory;
				start_out_directory = gui.start_out_directory;
				extention = gui.extention;

				boolean folder_find = false;
				int i = 0;
				int y = 0;
				String progress_in_directory = start_in_directory;
				String progress_out_directory = start_out_directory;
				String final_in_directory = start_in_directory;
				String final_out_directory = start_out_directory;
				
				File directoryPathSectionOne = new File(start_in_directory);
		
				String contentsSectionOne[] = directoryPathSectionOne.list();
				
				for(i=0; i<contentsSectionOne.length; i++) {
					if(contentsSectionOne[i].contains(extention)) {
						
						//catch file
						final_in_directory = final_in_directory + "\\" + contentsSectionOne[i];;
						
						String complet_date = Time_File_Find.Complet_date(final_in_directory);
						//catch dest
							//years
						File directoryPathSectionTow = new File(progress_out_directory);
						String contentsSectionTow[] = directoryPathSectionTow.list();
						
						String date_years = Time_File_Find.Time_finder(final_in_directory, "yyyy");
						
						for(y = 0; y < contentsSectionTow.length; y++) {
							if (contentsSectionTow[y] == date_years) {
								folder_find = true;
								break;
							}
						}
						if (folder_find == false) {
							new File(progress_out_directory + "\\" + date_years).mkdirs();
						}
						y = 0;
						folder_find = false;
						progress_out_directory = progress_out_directory + "\\" + date_years;
							//month
						File directoryPathSectionThree = new File(progress_out_directory);
						String contentsSectionThree[] = directoryPathSectionThree.list();
						
						String date_month = Time_File_Find.converter(Time_File_Find.Time_finder(final_in_directory, "MM"));
						
						for(y = 0; y < contentsSectionThree.length; y++) {
							if (contentsSectionThree[y] == date_month) {
								folder_find = true;
								break;
							}
						}
						y = 0;
						if (folder_find == false) {
							new File(progress_out_directory + "\\" + date_month).mkdirs();
						}
						folder_find = false;
						progress_out_directory = progress_out_directory + "\\" + date_month;
						
						File directoryPathSectionFour = new File(progress_out_directory);
						String contentsSectionFour[] = directoryPathSectionFour.list();
						
						final_out_directory = progress_out_directory +"\\"+ complet_date + extention;
						// past file
							// check if the file exist
						System.out.println("final in directory normal : " + final_in_directory);
						System.out.println("final out directory normal : "+ final_out_directory);
						for(y = 0; y < contentsSectionFour.length; y++) {
							if (contentsSectionFour.length <= 0) {
								System.out.println("normal lenght");
								break;
							}
							else if (contentsSectionFour[y].contains(complet_date + extention)) {
								folder_find = true;
								System.out.println("normal find");
								break;
							}
						}
						
						y = 0;
							// if no : past
						if (folder_find == false) {
							
							File source = new File(final_in_directory);
							File dest = new File(final_out_directory);;
							Files.copy(source.toPath(), dest.toPath());
							copy[0]++;
							gui.progress_frame(copy[0]);
							System.out.println("copy normal done");
						}
						
						System.out.println("");
						// reset
						folder_find = false;
						progress_out_directory = start_out_directory;
						final_in_directory = start_in_directory;
						final_out_directory = start_out_directory;
					}
	
					else {
						File testdirectory = new File(start_in_directory + "\\"+contentsSectionOne[i]);
						
						if(testdirectory.isDirectory()) {
							System.out.println("folder in  \\\\\\\\\\\\\\\\\\\\\\\\\\ \n");
							progress_in_directory = start_in_directory +"\\"+ contentsSectionOne[i];
							File_Copier.Folder(progress_in_directory, progress_out_directory, start_out_directory,extention,copy,gui);
							folder_find = false;
							progress_out_directory = start_out_directory;
							System.out.println("folder out /////////////");
						
						}
					}
				}
				gui.finish_frame(copy[0]);
			}	
		}
	}
}
