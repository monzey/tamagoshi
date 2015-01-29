package tamagoshi.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
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
import java.awt.event.KeyEvent;

import javax.swing.Action;

public class TamaFramePrincipale extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TamaFramePrincipale frame = new TamaFramePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TamaFramePrincipale() {
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
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		mnOptions.setMnemonic(KeyEvent.VK_O);
		
		JMenu mnDifficult = new JMenu("Difficulté");
		mnOptions.add(mnDifficult);
		mnDifficult.setMnemonic(KeyEvent.VK_D);
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("3");
		mnDifficult.add(radioButtonMenuItem);
		bG.add(radioButtonMenuItem);
		radioButtonMenuItem.setSelected(true);
		
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
		
		JCheckBoxMenuItem chckbxmntmGnrerLesNoms = new JCheckBoxMenuItem("Générer les noms automatiquement");
		mnOptions.add(chckbxmntmGnrerLesNoms);
		chckbxmntmGnrerLesNoms.setMnemonic(KeyEvent.VK_G);
		chckbxmntmGnrerLesNoms.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

		
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

	
	
}
