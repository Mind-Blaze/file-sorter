package executable;

import java.io.File;
import javax.swing.JOptionPane;

public class oldGui {
	public String start_in_directory;
	public String start_out_directory;
	public String extention;
	public boolean stop;

	public void Ask (String start_in_directory,String start_out_directory,boolean start) {
		
		String extention = null;
		boolean in_exist = false;
		boolean out_exist = false;
		boolean extention_exist = false;
		boolean finish = false;
		boolean stop = false;
		boolean dont_do = false;
		boolean continu_in = false;
		boolean continu_out = false;
		boolean chose_tow = false;
		String[] second_gui_string = new String[] {"Oui", "Non"};
		String[] chose_tow_gui_string = new String[] {"Entrée", "Sorti", "Les deux", "Annuler"};
		String[] end_gui_string = new String[] {"Oui","Non"};
		
		while (finish == false) {
			
			if (start == false) {
				
		        int second_gui = JOptionPane.showOptionDialog(null, 
		        		"Voulez-vous garder ces paramètres? \ndossier d'entrée : "+start_in_directory+
														   "\ndossier de sorti : "+start_out_directory, "Trieur",
														   JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
		                null, second_gui_string, second_gui_string[0]);

				 if (second_gui == 0) {
					continu_in = true;
					continu_out = true;
					chose_tow = false;
				 }
				 else if (second_gui == 1) {
					continu_in = false;
					continu_out = false;
					chose_tow = true;
				 }
				 else {
					in_exist = true;
					out_exist = true;
					extention_exist = true;
					chose_tow = false;
					finish = true;
					stop = true;
					dont_do = true;
				 }
				 if (chose_tow == true) {
					 
					 int chose_tow_gui = JOptionPane.showOptionDialog(null, "Quelle option voulez-vous changer ?", "Trieur",
							 JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
							 null, chose_tow_gui_string, chose_tow_gui_string[0]);
					 
					 if (chose_tow_gui == -1) {
						 in_exist = true;
						 out_exist = true;
						 extention_exist = true;
						 chose_tow = false;
						 finish = true;
						 stop = true;
						 dont_do = true;
					 }
					 else if (chose_tow_gui == 3) {
						 continu_in = true;
						 continu_out = true;
						 extention_exist = false;
					 }
					 else if (chose_tow_gui == 2) {
						 continu_in = false;
						 continu_out = false;
					 }
					 else if (chose_tow_gui == 1) {
						 continu_out = false;
						 continu_in = true;
					 }
					 else {
						 continu_out = true;
						 continu_in = false;
					 }
				 }
			}
			if (continu_in == false) {
			
				while (in_exist == false) {
					start_in_directory = JOptionPane.showInputDialog(null, "Entrez le dossier d'entrée", "Trieur", 3);
					if (start_in_directory != null) {
						File start_in_directory_exist = new File(start_in_directory);
						
						if (start_in_directory_exist.exists()&&start_in_directory_exist.isFile()==false) {
							in_exist = true;
						}
						if(in_exist == false || start_in_directory_exist.isFile()) {
							JOptionPane.showMessageDialog(null, "dossier invalide ou inexistant", "Trieur", 2);
							in_exist = false;
		
						}
					}
					else {
						start_in_directory = null;
						in_exist = true;
						out_exist = true;
						extention_exist = true;
						finish = true;
						stop = true;
						dont_do = true;
						System.out.println("off");
					}
				}
			}
			if (continu_out == false) {
				while (out_exist == false) {
					start_out_directory = JOptionPane.showInputDialog(null, "Entrez le dossier de sorti", "Trieur", 3);
					if (start_out_directory != null) {
						File start_out_directory_exist = new File(start_out_directory);
						if (start_out_directory_exist.exists()&&start_out_directory_exist.isFile()==false) {
							out_exist = true;
						}
						if(out_exist == false || start_out_directory_exist.isFile()||start_out_directory.equalsIgnoreCase(start_in_directory)) {
							JOptionPane.showMessageDialog(null, "dossier invalide ou inexistant", "Trieur", 2);
							out_exist = false;
						}
					}
					else {
						in_exist = true;
						out_exist = true;
						extention_exist = true;
						finish = true;
						stop = true;
						dont_do = true;
					}
				}
			}
			continu_in = false;
			continu_out = false;
			
			while (extention_exist == false) {
				extention = JOptionPane.showInputDialog(null, "Entrez une extension", "Trieur", 3);
				if (extention == null) {
					in_exist = true;
					out_exist = true;
					extention_exist = true;
					finish = true;
					stop = true;
					dont_do = true;
					
				}
				else if (extention.contains(".") == false) {
					JOptionPane.showMessageDialog(null, "extention incorecte", "Trieur", 2);
					
				}
				else if (extention.length() == 1) {
					JOptionPane.showMessageDialog(null, "extention incorecte", "Trieur", 2);
				}
				else {
					extention_exist = true;
				}
			}
			extention_exist = false;
			if (stop == false) {
				
				
				int end_gui = JOptionPane.showOptionDialog(null, 
						"Ces paramètres sont t-il correcte? \ndossier d'entrée : "+start_in_directory+
						 								   "\ndossier de sorti : "+start_out_directory+
						 								   "\nextention à trouver : "+extention, "Trieur",
        				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                		null, end_gui_string, end_gui_string[0]);
                
				 if (end_gui == 0) {
					 finish = true;
				 }
				 else if (end_gui == 1) {
					in_exist = false;
					out_exist = false;
					extention_exist = false;
				 }
				 else {
					in_exist = true;
					out_exist = true;
					extention_exist = true;
					finish = true;
					dont_do = true;
				 }
			}
			stop = false;
		}
		
		this.start_in_directory = start_in_directory;
		this.start_out_directory = start_out_directory;
		this.extention = extention;
		this.stop = dont_do;	
	}
}
