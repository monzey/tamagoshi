package tamagoshi.graphic;

import javax.swing.JPanel;

import toto.TotoJPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JLabel;

/**
 * Classe incluse dans une TamaFrame représentant un Tamagoshi graphiquement.
 * Il est à noter que cette classe utilise le package dessin pour dessiner la 
 * tête du tamagoshi
 * @author Maxime Bertrand
 *
 */
public class TamaJPanel extends JPanel {

	private JLabel lblHumeur;
	private JLabel lblParle;
	private JLabel lblAge;

	/**
	 * Crée le panel contenant
	 */
	public TamaJPanel() {
		setLayout(null);
		
		TotoJPanel totoJPanel = new TotoJPanel();
		totoJPanel.setBounds(0, -40, 450, 312);
		add(totoJPanel);
		totoJPanel.setLayout(null);
		
		lblHumeur = new JLabel();
		lblHumeur.setBounds(12, 50, 387, 15);
		totoJPanel.add(lblHumeur);
		
		lblParle = new JLabel();
		lblParle.setBounds(22, 77, 271, 15);
		totoJPanel.add(lblParle);
		
		lblAge = new JLabel();
		lblAge.setBounds(351, 172, 70, 15);
		totoJPanel.add(lblAge);

	}

	/**
	 * Change le texte du label correspondant à l'humeur du Tamagoshi
	 * @param message
	 */
	public void setTextHumeur(String message) {
		this.lblHumeur.setText(message);
	}
	/**
	 * Change le texte du label correspondant au ressentit du Tamagoshi par 
	 * rapport à une intéraction avec le joueur
	 * @param message
	 */
	public void setTextParle(String message) {
		this.lblParle.setText(message);
	}
	
	/**
	 * Change le texte du label correspondant à l'âge du Tamagoshi
	 * @param message
	 */
	public void setTextAge(String message){
		this.lblAge.setText(message);
	}
}
