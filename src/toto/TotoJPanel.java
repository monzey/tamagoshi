package toto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;


import dessin.*;

public class TotoJPanel extends JPanel {

	private ArrayList<ObjetGraphique> objects;

	public TotoJPanel() {
		// TODO Auto-generated constructor stub
		this.objects = new ArrayList<ObjetGraphique>();
		this.objects.add(new Circle(160, 150, 20));
		this.objects.add(new Circle(240, 150, 20));
		this.objects.add(new Rectangle(150, 220, 100, 40));
		this.objects.add(new Circle(200, 200, 100));
		
		
//		for (ObjetGraphique object : this.objects) {
//			JButton graphicButton = new JButton("Toggle object "+this.objects.indexOf(object));
//			add(graphicButton);
//			graphicButton.addActionListener(new GraphicButtonListener(this, object));
//		}
//		
//		this.addMouseListener(new GraphicMouseListener(this));
		
	}

	public TotoJPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	
	
	public ArrayList<ObjetGraphique> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<ObjetGraphique> objects) {
		this.objects = objects;
	}

	public TotoJPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public TotoJPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		 for (ObjetGraphique object : this.objects) {
			 if(object.isVisible()){
				 object.drawYouUrSelf(g);
			 }
		 }
		
		
	}

}
