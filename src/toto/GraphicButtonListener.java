package toto;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import dessin.ObjetGraphique;

public class GraphicButtonListener implements ActionListener {

	private TotoJPanel jPanel;
	private ObjetGraphique graphicObject;
	
	public GraphicButtonListener(TotoJPanel jPanel, ObjetGraphique graphicObject) {
		this.jPanel = jPanel;
		this.graphicObject = graphicObject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.graphicObject.toggleVisibility();
		this.jPanel.repaint();
		
	}

}
