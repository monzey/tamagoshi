package tamagoshi.jeu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.swing.Action;

import org.xml.sax.InputSource;

import tamagoshi.graphic.TamaFrame;
import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

import java.awt.FlowLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;


/**
 * Classe permettant de lancer une partie de Tamagoshi en mode Graphique
 * @author Maxime Bertrand
 *
 */
public class TamaGameGraphic extends JFrame implements Observer{

	private JPanel contentPane;
	private ArrayList<TamaFrame> tamasFrames;
	private ArrayList<Tamagoshi> vivants;
	private JLabel lblCycle;
	
	private JLabel lblDifficulte;
	private JLabel lblGeneration;
	
	private Properties parametres;
	private Properties noms;
	private static final String emplacementParametres = "parametres.properties";
	private static final String emplacementGenerationNoms = "noms.properties";

	private static final int NB_CYCLES = 10;
	
	InputStream inParams = null;
	OutputStream outParams = null;
	
	InputStream inNoms = null;
	OutputStream outNoms = null;

	private int numeroCycle = 1;
	
	
	/**
	 * Create the frame.
	 */
	public TamaGameGraphic() {
		init();
	}

	private void init() {
		this.tamasFrames = new ArrayList<TamaFrame>();
		this.parametres = new Properties();
		this.noms = new Properties();
		
		this.vivants = new ArrayList<Tamagoshi>();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		loadInitFiles();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		FormLayout fl_panel = new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("150px"),
				ColumnSpec.decode("150px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("70px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("15px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,});
		panel.setLayout(fl_panel);
		
		this.lblDifficulte = new JLabel("Difficulte : " + (this.parametres.getProperty("difficulte")));
		panel.add(lblDifficulte, "1, 2, 4, 1, left, top");
		
		this.lblGeneration = new JLabel("Génération des noms : " + ((this.parametres.getProperty("generation").equals("oui")) ? "automatique" : "manuelle"));
		panel.add(lblGeneration, "1, 4, 4, 1, left, top");
		
		this.lblCycle = new JLabel("");
		panel.add(lblCycle, "1, 10, 4, 1");
		
		
		
		String contenuGenerationNom = "Génération des noms : " + ((this.parametres.getProperty("generation").equals("oui")) ? "automatique" : "manuelle") ;
		String contenuDifficulte = "Difficulte : " + (this.parametres.getProperty("difficulte"));
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		ButtonGroup bG = new ButtonGroup();
		
		JMenu mnJeu = new JMenu("Jeu");
		menuBar.add(mnJeu);
		mnJeu.setMnemonic(KeyEvent.VK_J);
		
		JMenuItem mntmNouvellePartie = new JMenuItem("Nouvelle Partie");
		mnJeu.add(mntmNouvellePartie);
		mntmNouvellePartie.setMnemonic(KeyEvent.VK_N);
		mntmNouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		
		mntmNouvellePartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
				lblCycle.setText("----------------Cycle n°" + numeroCycle + "----------------");
				System.out.println("----------------Cycle n°" + numeroCycle + "----------------");
			}
		});
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		mnOptions.setMnemonic(KeyEvent.VK_O);
		
		JMenu mnDifficult = new JMenu("Difficulté");
		mnOptions.add(mnDifficult);
		mnDifficult.setMnemonic(KeyEvent.VK_D);
		
		
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("3");
		mnDifficult.add(radioButtonMenuItem);
		bG.add(radioButtonMenuItem);
		
		JRadioButtonMenuItem radioButtonMenuItem_1 = new JRadioButtonMenuItem("4");
		mnDifficult.add(radioButtonMenuItem_1);
		bG.add(radioButtonMenuItem_1);
		
		JRadioButtonMenuItem radioButtonMenuItem_2 = new JRadioButtonMenuItem("5");
		bG.add(radioButtonMenuItem_2);
		mnDifficult.add(radioButtonMenuItem_2);
		
		JRadioButtonMenuItem radioButtonMenuItem_3 = new JRadioButtonMenuItem("6");
		bG.add(radioButtonMenuItem_3);
		mnDifficult.add(radioButtonMenuItem_3);
		
		JRadioButtonMenuItem radioButtonMenuItem_4 = new JRadioButtonMenuItem("7");
		bG.add(radioButtonMenuItem_4);
		mnDifficult.add(radioButtonMenuItem_4);
		
		JRadioButtonMenuItem radioButtonMenuItem_5 = new JRadioButtonMenuItem("8");
		bG.add(radioButtonMenuItem_5);
		mnDifficult.add(radioButtonMenuItem_5);
		
		Enumeration<AbstractButton> en = bG.getElements();
		while (en.hasMoreElements()) {
			final AbstractButton radioBt = (AbstractButton) en.nextElement();
			if(parametres.getProperty("difficulte").equals(radioBt.getText())) radioBt.setSelected(true);
			radioBt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (radioBt.isSelected()) parametres.setProperty("difficulte", radioBt.getText());
					System.out.println("Changement de difficulté : " + radioBt.getText());
					enregistrerParametres();
				}
			});
			 
		} 
		
		JCheckBoxMenuItem chckbxmntmGnrerLesNoms = new JCheckBoxMenuItem("Générer les noms automatiquement");
		mnOptions.add(chckbxmntmGnrerLesNoms);
		chckbxmntmGnrerLesNoms.setMnemonic(KeyEvent.VK_G);
		chckbxmntmGnrerLesNoms.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		
		if(this.parametres.getProperty("generation").equals("oui")) chckbxmntmGnrerLesNoms.setSelected(true);
		
		chckbxmntmGnrerLesNoms.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				boolean generationNoms = (e.getStateChange() == ItemEvent.SELECTED);
				if (generationNoms) parametres.setProperty("generation", "oui");
				else parametres.setProperty("generation", "non");
				System.out.println("Génération des noms");
				enregistrerParametres();
			}
		});
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		mnAide.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem mntmAPropos = new JMenuItem("A propos");
		mnAide.add(mntmAPropos);
		mntmAPropos.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem mntmAide = new JMenuItem("Aide");
		mnAide.add(mntmAide);
		mntmAide.setMnemonic(KeyEvent.VK_I);
		
	
		
	}

	/**
	 * Charge le fichier nécessaire à la configuration par défaut
	 * (difficulté, génération des noms automatiques)
	 */
	private void loadInitFiles() {
		try {
			inParams = new FileInputStream(emplacementParametres);
			inNoms = new FileInputStream(emplacementGenerationNoms);
			parametres.load(inParams);
			noms.load(inNoms);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void enregistrerParametres() {
		try {
			outParams = new FileOutputStream(emplacementParametres);
			parametres.store(outParams, null);	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.lblDifficulte.setText("Difficulte : " + (this.parametres.getProperty("difficulte")));
		this.lblGeneration.setText("Génération des noms : " + ((this.parametres.getProperty("generation").equals("oui")) ? "automatique" : "manuelle"));
	}
	
	/**
	 * Lance le jeu et crée une fenêtre pour chaque tamagoshi créé par le joueur
	 */
	private void play(){
		
		if(this.parametres.getProperty("generation").equals("oui")){
			for (int i = 1; i <= Integer.valueOf(parametres.getProperty("difficulte")); i++) {
				Tamagoshi t;
				if(Math.random()<.5)
					t = new GrosJoueur(noms.getProperty(String.valueOf(i)));
				else
					t = new GrosMangeur(noms.getProperty(String.valueOf(i)));
				tamasFrames.add(new TamaFrame(t));
				this.vivants.add(t);
			}
		} else {
			for (int i = 1; i <= Integer.valueOf(parametres.getProperty("difficulte")); i++) {
				Tamagoshi t;
				if(Math.random()<.5)tamasFrames.add(new TamaFrame(new GrosJoueur(JOptionPane.showInputDialog("Veuillez saisir le nom des tamagoshis"))));
				else
					tamasFrames.add(new TamaFrame(new GrosMangeur(JOptionPane.showInputDialog("Veuillez saisir le nom des tamagoshis"))));
			}
		}
		
		for (TamaFrame tamaFrame : tamasFrames) {
			tamaFrame.setVisible(true);
			tamaFrame.addObservers(tamasFrames);
			tamaFrame.addObserver(this);
		}
		
	}
	
	
	public static void main(String[] args) {
		TamaGameGraphic frame = new TamaGameGraphic();
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(((int)arg == TamaFrame.NEXT_CYCLE)){
			if(!this.vivants.isEmpty() && numeroCycle <= TamaGameGraphic.NB_CYCLES){
				for (TamaFrame tamaFrame : tamasFrames) {
					if (!tamaFrame.cycleSuivant()){
						this.vivants.remove(tamaFrame.getTamagoshi());
					}
				}
				continueGame();
				if(this.vivants.isEmpty()){
					endGame();
				}
			} else {
				endGame();
			}
		} 
	}

	/**
	 * Continue le jeu en passant au cycle suivant
	 */
	private void continueGame() {
		this.lblCycle.setText("----------------Cycle n°" + numeroCycle + "----------------");
		System.out.println("----------------Cycle n°" + numeroCycle + "----------------");
		numeroCycle++;
	}

	/**
	 * Termine le jeu et calcule les résultats
	 */
	private void endGame() {
		for (TamaFrame tamaFrame : tamasFrames) {
			tamaFrame.disableBothBtns();
		}
		this.lblCycle.setText("----------------Fin de la partie----------------");
		System.out.println("----------------Fin de la partie----------------");
		calculResultats();
//		enregistrementMeilleurScores();
	}

	
	/*
	 * Permet de calculer les 3 meilleurs scores en fonction de la difficulté (non terminé)
	 */
	
//	private void enregistrementMeilleurScores() {
//		try{
//			String[] scores = this.parametres.getProperty("meilleur.score.difficulte."+this.parametres.getProperty("difficulte"));
//		} catch(NullPointerException e) {
//			this.parametres.setProperty("meilleur.score.difficulte."+this.parametres.getProperty("difficulte"), String.valueOf(score()+","));
//		} finally {
//			enregistrerParametres();
//		}
//	}

	/**
	 * Résume la partie en affichant les catégories de Tamagoshis présents dans la partie
	 * et en calculant le score total du joueur.
	 */
	
	private void calculResultats() {
		String resultats = "<html>-------------bilan------------<br>";
		for(TamaFrame t : this.tamasFrames){
			String classe = t.getTamagoshi().getClass().getSimpleName();
			resultats += t.getTamagoshi().getName()+" qui était un "+classe+" "+(t.getTamagoshi().getAge()==Tamagoshi.getLifeTime()?" a survécu et vous remercie :)":" n'est pas arrivé au bout et ne vous félicite pas :(<br>");
		}
		resultats += "<br>niveau de difficulté : "+this.tamasFrames.size()+", score obtenu :"+score()+"%</html>";
		this.lblCycle.setText(resultats);
	}
	
	/**
	 * Calcule les résultats en fonction de l'âge de tous les tamagoshis par 
	 * rapport à la somme des âges max de tous les tamagoshis
	 * @return score 
	 */
	private double score(){
		int score=0;
		for(TamaFrame t : this.tamasFrames)
			score += t.getTamagoshi().getAge();
		return score*100/(Tamagoshi.getLifeTime()*this.tamasFrames.size());
	}
	


	
	
}
