package executable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton enterbutton = new JButton("Chercher");
	JButton sortibutton = new JButton("Chercher");
	JButton okbutton = new JButton("Ok");
	JButton annulerbutton = new JButton("Annuler");
	JButton color_switch = new JButton();
	
	JTextField entertext = new JTextField();
	JTextField sortitext = new JTextField();
	JTextField exttext = new JTextField();
	JTextField filename = new JTextField();
	
	public String start_in_directory;
	public String start_out_directory;
	public String extention;
	
	JLabel endlabel ;
	
	boolean isok = false;
	public boolean start = false;
	
	ImageIcon image = new ImageIcon("triangle_warning_v4.png");	
	
	// state : 0 start          1 progress state       2 finich state
	
	public void start_frame(String start_in_directory,String start_out_directory,String extention,int copy,int i) {
		
		this.setResizable(false);
		
		Border border = BorderFactory.createLineBorder(new Color(0xeeeeee),5);
		
		enterbutton.setFont(new Font("Arial",Font.BOLD, 15));
		sortibutton.setFont(new Font("Arial",Font.BOLD, 15));
		okbutton.setFont(new Font("Arial",Font.BOLD, 15));
		annulerbutton.setFont(new Font("Arial",Font.BOLD, 15));
		
		
		enterbutton.addActionListener(this);
		sortibutton.addActionListener(this);
		okbutton.addActionListener(this);
		annulerbutton.addActionListener(this);
		
		enterbutton.setFocusable(false);
		sortibutton.setFocusable(false);
		okbutton.setFocusable(false);
		annulerbutton.setFocusable(false);
		
		//// dd-MM-yyyy  HHh mmmin sssec
		
		// 202123 : back ground / black theme
		// 2b2c2f : button select 
		
		
		// 509 x 200
		
		JLabel enterlabel = new JLabel();
		JLabel sortilabel = new JLabel();
		JLabel extlabel = new JLabel();
		endlabel = new JLabel();
		JLabel datelabel = new JLabel();
		
		enterlabel.setFont(new Font("Arial",Font.BOLD, 17));
		sortilabel.setFont(new Font("Arial",Font.BOLD, 17));
		extlabel.setFont(new Font("Arial",Font.BOLD, 17));
		endlabel.setFont(new Font("Arial",Font.BOLD, 17));
		datelabel.setFont(new Font("Arial",Font.BOLD, 17));
		
		enterlabel.setText("Entrée :");
		sortilabel.setText("Sorti :");	
		extlabel.setText("Extention :");
		endlabel.setText("Trieur 1.0   ©Mindblaze");
		datelabel.setText("Nom du fichier");
		
		
		entertext.addActionListener(this);
		sortitext.addActionListener(this);
		exttext.addActionListener(this);
		
		entertext.setFont(new Font("Arial",Font.BOLD, 15));
		sortitext.setFont(new Font("Arial",Font.BOLD, 15));
		exttext.setFont(new Font("Arial",Font.BOLD, 15));
		
		entertext.setText(start_in_directory);
		sortitext.setText(start_out_directory);
		exttext.setText(extention);
		
		entertext.setPreferredSize(new Dimension(300,30));
		sortitext.setPreferredSize(new Dimension(300,30));
		exttext.setPreferredSize(new Dimension(300,30));
		
		
		JPanel eslabelpanel = new JPanel();
		eslabelpanel.setLayout(new GridLayout(3,1,5,5));
		eslabelpanel.add(enterlabel);
		eslabelpanel.add(sortilabel);
		eslabelpanel.add(extlabel);
		
		
		JPanel estextpanel = new JPanel();
		estextpanel.setLayout(new GridLayout(3,1,5,5));
		estextpanel.add(entertext);
		estextpanel.add(sortitext);
		estextpanel.add(exttext);	
		
		JPanel esbuttonpanel = new JPanel();
		esbuttonpanel.setLayout(new GridLayout(3,1,5,5));
		esbuttonpanel.add(enterbutton);
		esbuttonpanel.add(sortibutton);

		
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new BorderLayout(5,5));
		inputpanel.add(eslabelpanel,BorderLayout.WEST);
		inputpanel.add(estextpanel,BorderLayout.CENTER);
		inputpanel.add(esbuttonpanel,BorderLayout.EAST);
		
		JPanel datepanel = new JPanel();
		datepanel.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
		

		JPanel finalpanel = new JPanel();
		finalpanel.setBorder(border);
		finalpanel.setLayout(new BorderLayout(5,20));
		finalpanel.add(inputpanel,BorderLayout.NORTH);
		finalpanel.add(endlabel,BorderLayout.CENTER);
		finalpanel.add(okbutton,BorderLayout.SOUTH);
		this.setTitle("Trieur");
		this.remove(finalpanel);
		this.add(finalpanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setVisible(true);
		this.pack();
		this.setResizable(true);
		
	}
	
	public void progress_frame(int copy) {
		endlabel.setText("Copiage en cours, fichiers copiées : " + copy);
		enterbutton.setEnabled(false);
		sortibutton.setEnabled(false);
		okbutton.setEnabled(false);
		annulerbutton.setEnabled(false);
		
		entertext.setEnabled(false);
		sortitext.setEnabled(false);
		exttext.setEnabled(false);
	}
	
	public void finish_frame(int copy) {
 
		endlabel.setText("Copiage en fini, fichiers copiées : " + copy);
		enterbutton.setEnabled(true);
		sortibutton.setEnabled(true);
		okbutton.setEnabled(true);
		annulerbutton.setEnabled(true);
		
		entertext.setEnabled(true);
		sortitext.setEnabled(true);
		exttext.setEnabled(true);
		
		start = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterbutton) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int responce = chooser.showOpenDialog(null);
			if (responce == JFileChooser.APPROVE_OPTION) {
				start_in_directory = chooser.getSelectedFile().getAbsolutePath();
				entertext.setText(start_in_directory);
			}
		}
		if (e.getSource() == sortibutton) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int responce = chooser.showOpenDialog(null);
			if (responce == JFileChooser.APPROVE_OPTION) {
				start_out_directory = chooser.getSelectedFile().getAbsolutePath();
				sortitext.setText(start_out_directory);
			}
		}
		if (e.getSource() == okbutton) {
			start_in_directory = entertext.getText();
			if (!start_in_directory.isBlank() && !start_in_directory.isEmpty()) {
				File start_in_directory_exist = new File(start_in_directory);
				
				if (start_in_directory_exist.exists()&&start_in_directory_exist.isDirectory()) {
					isok = true;
				}
				else if (start_in_directory.contentEquals("Entrez le chemin")) {
					endlabel.setText("Une ou plusieurs entrées sont vides");
					endlabel.setIcon(image);
				}
				else if (!start_in_directory_exist.exists()){
					endlabel.setText("Le chemin d'entrée n'existe pas");
					endlabel.setIcon(image);
				}
				else if (!start_in_directory_exist.isDirectory()) {
					endlabel.setText("Uniquement les dossiers sont acceptées");
					endlabel.setIcon(image);
				}
				else {
					endlabel.setText("Un problème est aparu, le programe va bientôt se fermer. code : entin");
					endlabel.setIcon(image);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
				
			}
			else {
				endlabel.setText("Une ou plusieurs entrées sont vides");
			}
			start_out_directory = sortitext.getText();
			if (!start_out_directory.isBlank() && isok == true && !start_out_directory.isEmpty()) {
				File start_out_directory_exist = new File(start_out_directory);
				
				if (start_out_directory_exist.exists()&&start_out_directory_exist.isDirectory()&& !start_out_directory.contentEquals(start_in_directory) ) {
					isok = true;
					endlabel.setIcon(image);
				}
				else if (start_out_directory.contentEquals(start_in_directory)) {
					endlabel.setText("Le chemin d'entrée de doit être le même que celui de sorti");
					isok = false;
					endlabel.setIcon(image);
				}
				else if (start_out_directory.contentEquals("Entrez le chemin")) {
					endlabel.setText("Une ou plusieurs entrées sont vides");
					endlabel.setIcon(image);
					isok = false;
				}
				else if (!start_out_directory_exist.exists()){
					endlabel.setText("Le chemin de sorti n'existe pas");
					isok = false;
					endlabel.setIcon(image);
				}
				else if (!start_out_directory_exist.isDirectory()) {
					endlabel.setText("Uniquement les dossiers sont acceptées");
					isok = false;
					endlabel.setIcon(image);
				}
				else {
					endlabel.setText("Un problème est aparu, le programe va bientôt se fermer. code : entout");
					endlabel.setIcon(image);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
					
				}
				
			}
			else if (isok == true){
				endlabel.setText("Une ou plusieurs entrées sont vides");
				isok = false;
				endlabel.setIcon(image);
			}
			extention = exttext.getText(); 
			if (isok == true) {
				if (extention.indexOf(".") == 0 && extention.lastIndexOf(".") == 0) {
					isok = true;
				}
				else if (extention.contentEquals("Entrez une extention")) {
					endlabel.setText("Une ou plusieurs entrées sont vides");
					endlabel.setIcon(image);
				}
				else if (extention.contentEquals(".")) {
					endlabel.setText("L'extension ne doit pas avoir que un point");
					endlabel.setIcon(image);
				}
				else if(extention.indexOf(".") != 0 || extention.lastIndexOf(".") != 0) {
					endlabel.setText("L'extension doit être vide ou commencer par un point");
					endlabel.setIcon(image);
				}
				else if(extention.contains(" ")) {
					endlabel.setText("L'extension ne doit pas avoir d'ecpace(s)");
					endlabel.setIcon(image);
				}
				else {
					endlabel.setText("Un problème est aparu, le programe va bientôt se fermer. code : ext");
					endlabel.setIcon(image);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
			}
		
		if (isok == true) {
			endlabel.setText("Copiage en cours");
			endlabel.setIcon(null);
			start = true;
			
		}
		}
		
	}

	
	
}
