package tamagoshi.jeu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
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

public class TamaGameGraphic extends JFrame{

	private JPanel contentPane;
	private ArrayList<TamaFrame> tamaFrames;
	private JLabel cycle;
	
	private Properties parametres;
	private Properties noms;
	private static final String emplacementParametres = "parametres.properties";
	private static final String emplacementGenerationNoms = "noms.properties";

	private static final int NB_CYCLES = 10;
	
	InputStream inParams = null;
	OutputStream outParams = null;
	
	InputStream inNoms = null;
	OutputStream outNoms = null;


	
	
	/**
	 * Create the frame.
	 */
	public TamaGameGraphic() {
		init();
	}

	private void init() {
		this.tamaFrames = new ArrayList<TamaFrame>();
		this.parametres = new Properties();
		this.noms = new Properties();
		
		String contenuGenerationNom = "";
		String contenuDifficulte = "";
		String contenuCycle = "Jeu de Tamagoshi - Développé par Monzey \n" + contenuGenerationNom + contenuDifficulte;
		
		cycle = new JLabel();
		cycle.setText(contenuCycle);
		this.getContentPane().add(cycle);
		
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
	}
	
	
	private void play(){
		for (int i = 1; i <= Integer.valueOf(parametres.getProperty("difficulte")); i++) {
		tamaFrames.add(new TamaFrame(noms.getProperty(String.valueOf(i))));
		}
		for (TamaFrame tamaFrame : tamaFrames) {
			tamaFrame.setVisible(true);
			tamaFrame.addObservers(tamaFrames);
			System.out.println(tamaFrame.countObservers());
		}
//		int numCycle = 0;
//		boolean nextTurn = false;
//		while(numCycle < NB_CYCLES){
//			System.out.println("Partie lancée");
//			for (TamaFrame tamaFrame : tamaFrames) {
//					nextTurn = (tamaFrame.bothBtnsDisabled());
//			}
//			if(nextTurn) numCycle++;
//		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TamaGameGraphic frame = new TamaGameGraphic();
		frame.setVisible(true);
	}


	
	
}
