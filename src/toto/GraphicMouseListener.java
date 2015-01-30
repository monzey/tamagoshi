package toto;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dessin.ObjetGraphique;

public class GraphicMouseListener implements MouseListener {

	private TotoJPanel jPanel;
	
	public GraphicMouseListener(TotoJPanel jPanel) {
		// TODO Auto-generated constructor stub
		this.jPanel = jPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getX() + "/" +  e.getY());
		for (ObjetGraphique object : this.jPanel.getObjects()) {
			if (object.contains(e.getX(), e.getY())){
				object.changeColor();
				break;
			}
		}
//		
//		while (jPanel.getObjects().iterator().hasNext()) {
//			ObjetGraphique object = (ObjetGraphique) jPanel.getObjects().iterator().next();
//			if (object.contains(e.getX(), e.getY())) object.changeColor();
//		}
		
		this.jPanel.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
