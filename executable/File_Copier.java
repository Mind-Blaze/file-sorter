package executable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class File_Copier {
	public static void Folder(String start_in_directory, String progress_out_directory, String start_out_directory,String extention,int[] copy,Gui gui) throws IOException {
		System.out.println("initialisation method start in : "+start_in_directory);
		boolean folder_find = false;
		int y = 0;
		String final_out_directory;
		String progress_in_directory = start_in_directory;
		String final_in_directory = start_in_directory;
		File directoryPath = new File(start_in_directory);
		String contents[] = directoryPath.list();
		System.out.println(directoryPath.getPath());
		

		int i = 0 ;
		
		if (contents != null) {
			for (i = 0; i < contents.length; i++) {
				File testdirectory = new File(start_in_directory + "\\"+contents[i]);

				if(testdirectory.isDirectory()){
					if(directoryPath.isDirectory() /*&&  directoryPath.getPath() != null*/) {
						progress_in_directory = progress_in_directory + "\\" + contents[i];
						System.out.println("\ncontains");
						File_Copier.Folder(progress_in_directory,progress_out_directory,start_out_directory,extention, copy,gui);
						progress_in_directory = start_in_directory;
					
					}
				}
				else if(contents[i].contains(extention)){
					//catch file
					final_in_directory = progress_in_directory + "\\" + contents[i];
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
					y = 0;
					if (folder_find == false) {
						new File(progress_out_directory + "\\" + date_years).mkdirs();
					}
					
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
					System.out.println("final in directory folder : " + final_in_directory);
					System.out.println("final out directory folder : "+ final_out_directory);
					for(y = 0; y < contentsSectionFour.length; y++) {
						if (contentsSectionFour.length <= 0) {
							System.out.println("folder lenght");
							break;
						}
						else if (contentsSectionFour[y].contains(complet_date + extention)) {
							folder_find = true;
							System.out.println("folder find");
							break;
						}
					}
					y = 0;
						// if no : past
					if (folder_find == false) {
						File source = new File(final_in_directory);
						File dest = new File(final_out_directory);
						Files.copy(source.toPath(), dest.toPath());
						copy[0]++;
						gui.progress_frame(copy[0]);
						System.out.println("copy folder done");
					}
					System.out.println("");
					folder_find = false;
					progress_out_directory = start_out_directory;
					final_in_directory = start_in_directory;
					System.out.println("end of copy progress out : "+ progress_out_directory);
					System.out.println("end of copy final in : "+final_in_directory);
				}	
			}
		}
		
		final_in_directory = start_in_directory;
		System.out.println("end of for : "+final_in_directory+"\n");
	}	
}