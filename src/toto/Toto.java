package toto;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Toto extends JFrame {

	public void init(){
		this.setTitle("Test");
		JPanel panel = new TotoJPanel();
		this.add(panel);
	}
	
	public Toto() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.init();
	}

	public Toto(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public Toto(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public Toto(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		JFrame frame = new Toto();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
