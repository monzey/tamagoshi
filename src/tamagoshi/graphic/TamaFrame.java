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

public class TamaFrame extends Observable implements Observer{

	private JFrame frame;
	
	private TamaJPanel tamaPanel;
	private Tamagoshi tamagoshi;
	private JPanel contentPane;

	
	private JButton btnNourrir;
	private JButton btnJouer;
	
	private static final int BTN_JOUER = 10;
	private static final int BTN_NOURRIR = 20;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TamaFrame frame = new TamaFrame("Jean-paul");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TamaFrame(String nomTmagoshi){
		init(nomTmagoshi);
	}


	private void init(String nomTamagoshi) {
		this.tamagoshi = new Tamagoshi(nomTamagoshi);
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
			}
		});
		
	}
	
	public boolean nourri(){
		
		return false;
	}
	
	public boolean joué(){
		return false;
	}
	
	public void setVisible(boolean b){
		this.frame.setVisible(b);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if((int)arg == TamaFrame.BTN_JOUER) this.btnJouer.setEnabled(false);
		else this.btnNourrir.setEnabled(false);
	}
	
	public void addObservers(ArrayList<TamaFrame> obs){ 
		for (Observer observer : obs) {
			this.addObserver(observer);
		}
	}

	public boolean bothBtnsDisabled() {
		// TODO Auto-generated method stub
		return !(this.btnJouer.isEnabled() && this.btnNourrir.isEnabled());
	}
	
	

}
