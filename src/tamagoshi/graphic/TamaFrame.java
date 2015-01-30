package tamagoshi.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import tamagoshi.tamagoshis.Tamagoshi;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;


/**
 * Classe représentant un tamagoshi graphiquement
 * @author Maxime Bertrand
 *
 */
public class TamaFrame extends Observable implements Observer{

	private JFrame frame;
	
	private TamaJPanel tamaPanel;
	private Tamagoshi tamagoshi;
	private JPanel contentPane;

	
	private JButton btnNourrir;
	private JButton btnJouer;
	
	public static final int BTN_JOUER = 10;
	public static final int BTN_NOURRIR = 20;

	public static final int NEXT_CYCLE = 30;
	
	

	/**
	 * Lancement d'une frame individuelle
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TamaFrame frame = new TamaFrame(new Tamagoshi("Jean-paul"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructeur d'une TamaFrame
	 * @param tama un tamagoshi quelconque
	 */
	public TamaFrame(Tamagoshi tama){
		init(tama);
	}

	/**
	 * Initialise la fenêtre du Tamagoshi et les différents éléments graphiques
	 * permettant d'intéragir avec ce dernier
	 * @param tama un tamagoshi quelconque
	 */
	private void init(Tamagoshi tama) {
		this.tamagoshi = tama;
		this.frame = new JFrame();
		this.frame.setTitle(this.tamagoshi.getName());
		this.tamaPanel = this.tamagoshi.getPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		contentPane.add(this.tamaPanel);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnNourrir = new JButton("Nourrir");
		panel.add(btnNourrir);
		
		btnNourrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tamagoshi.parle();
				tamagoshi.mange();
				setChanged();
				notifyObservers(TamaFrame.BTN_NOURRIR);
				notifyNextCycle();
			}

		});
		
		btnJouer = new JButton("Jouer");
		panel.add(btnJouer);
		
		btnJouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tamagoshi.parle();
				tamagoshi.joue();
				setChanged();
				notifyObservers(TamaFrame.BTN_JOUER);
				notifyNextCycle();
			}
		});
		
	}
	
	/**
	 * Notification aux écouteurs du passage au cycle suivant dans
	 * le cours de la partie en cours
	 */
	private void notifyNextCycle() {
		if(this.bothBtnsDisabled()){
			this.setChanged();
			this.notifyObservers(TamaFrame.NEXT_CYCLE);
		}
	}
	
	
	public void setVisible(boolean b){
		this.frame.setVisible(b);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if((int)arg == TamaFrame.BTN_JOUER){
			this.btnJouer.setEnabled(false);
		}
		else if((int)arg == TamaFrame.BTN_NOURRIR){
			this.btnNourrir.setEnabled(false);
		}
	}
	
	/**
	 * Ajout de plusieurs Observer
	 * @param obs une collection d'Observer (TamaFrame en l'occurrence)
	 */
	public void addObservers(ArrayList<TamaFrame> obs){ 
		for (Observer observer : obs) {
			this.addObserver(observer);
		}
	}

	/**
	 * Vérifie que les deux boutons "nourrir" et "jouer" sont désactivés
	 * @return
	 */
	public boolean bothBtnsDisabled() {
		return (!this.btnJouer.isEnabled() && !this.btnNourrir.isEnabled());
	}

	/**
	 * Passe au cycle suivant et veille au bon déroulement de l'anthropie des Tamagoshis
	 * (vieillissement, faim, ennui)
	 * @return
	 */
	public boolean cycleSuivant() {
		if(! this.tamagoshi.consommeEnergie() || ! this.tamagoshi.consommeFun() || this.tamagoshi.vieillit()){
			this.disableBothBtns();
			return false;
		} else{
			this.enableBothBtns();
			this.tamagoshi.parle();
			return true;
		}
	}

	/**
	 * Désactive les deux boutons "nourrir" et "jouer"
	 */
	public void disableBothBtns() {
		this.btnNourrir.setEnabled(false);
		this.btnJouer.setEnabled(false);
	}

	/**
	 * Active les deux boutons "nourrir" et "jouer"
	 */
	public void enableBothBtns() {
		this.btnNourrir.setEnabled(true);
		this.btnJouer.setEnabled(true);
	}
	/**
	 * 
	 * @return tamagoshi le tamagoshi de la TamaFrame
	 */
	public Tamagoshi getTamagoshi() {
		return this.tamagoshi;
	}
	
	

}
