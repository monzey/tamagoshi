package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class ObjetGraphique {

	protected Color color;
	protected boolean visible = true;
	
	protected static ArrayList<Color> colorCycle = new ArrayList<Color>();
	protected int currentColor = 0;
	
	public void initColorCycle(){
		colorCycle.add(Color.black);
		colorCycle.add(Color.blue);
		colorCycle.add(Color.green);
		colorCycle.add(Color.red);
		colorCycle.add(Color.orange);
		colorCycle.add(Color.white);
	}
	
	public static Color getColorCycle(int index){
		return colorCycle.get(index);
	}
	
	public void nextColor(){
		if(this.currentColor == 5) this.currentColor = 0;
		else this.currentColor++;
	}
	
	public void changeColor(){
		this.nextColor();
		this.setColor(getColorCycle(this.currentColor));
	}
	
	public ObjetGraphique() {
		this.initColorCycle();
		this.color = getColorCycle(0);
	}

	public ObjetGraphique(Color color) {
		this.initColorCycle();
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.initColorCycle();
	}
	
	public boolean isVisible(){
		return this.visible;
	}
	
	private void setVisible(boolean yesOrNo){
		this.visible = yesOrNo;
	}
	
	public void toggleVisibility(){
		this.setVisible(!this.isVisible());
	}
	
	public abstract void drawYouUrSelf(Graphics g);
	
	public abstract boolean contains(int x, int y);
	
	

}
