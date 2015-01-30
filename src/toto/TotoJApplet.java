package toto;

import java.awt.HeadlessException;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TotoJApplet extends JApplet {

	public TotoJApplet() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.init();
	}
	
	public void init(){
//		this.setTitle("Test");
		JPanel panel = new TotoJPanel();
		this.add(panel);
	}

	public static void main(String[] args){
		JApplet applet = new TotoJApplet();
//		frame.setSize(400, 400);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applet.setVisible(true);
	}
}
