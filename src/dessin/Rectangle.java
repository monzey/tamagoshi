package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends ObjetGraphique {

	protected java.awt.Rectangle rectangle;

	public Rectangle(int x, int y, int width, int height) {
		// TODO Auto-generated constructor stub
		super();
		this.rectangle = new java.awt.Rectangle();
		this.rectangle.setBounds(x, y, width, height);
	}
	
	public Rectangle(int x, int y, int width, int height, Color color) {
		// TODO Auto-generated constructor stub
		super();
		this.rectangle = new java.awt.Rectangle();
		this.rectangle.setBounds(x, y, width, height);
	}

	public Rectangle(Point point, int width, int height) {
		// TODO Auto-generated constructor stub
		super();
		this.rectangle = new java.awt.Rectangle();
		this.rectangle.setLocation(point);
		this.rectangle.setSize(width, height);
	}

	public Rectangle(Point point, int width, int height, Color color) {
		// TODO Auto-generated constructor stub
		super(color);
		this.rectangle = new java.awt.Rectangle();
		this.rectangle.setLocation(point);
		this.rectangle.setSize(width, height);
	}

	public Rectangle() {
		super();
		this.rectangle = new java.awt.Rectangle();
		this.rectangle.setBounds(0, 0, 0, 0);
	}

	public Rectangle(Color color) {
		// TODO Auto-generated constructor stub
		super(color);
	}

	@Override
	public void drawYouUrSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.color);
		g.drawRect(
				(int) this.rectangle.getX(),
				(int) this.rectangle.getY(),
				(int) this.rectangle.getWidth(),
				(int) this.rectangle.getHeight());

	}

	@Override
	public boolean contains(int x, int y) {
		return this.rectangle.contains(x, y);
	}
	
	

}
