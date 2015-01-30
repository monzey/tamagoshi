package tamagoshi.graphic;

import javax.swing.JPanel;
import toto.TotoJPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class TamaJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TamaJPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		TotoJPanel totoJPanel = new TotoJPanel();
		add(totoJPanel);

	}

}
