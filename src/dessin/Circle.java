package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
/**
 * 
 * @author bertrandm
 * La classe Circle est une classe représentant un cercle. 
 */
public class Circle extends ObjetGraphique {

	protected Point center;
	protected int radius;
	
	public Circle(Point center, int radius) {
		// TODO Auto-generated constructor stub
		super();
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius,Color color) {
		// TODO Auto-generated constructor stub
		super(color);
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(int center_x, int center_y, int radius) {
		// TODO Auto-generated constructor stub
		super();
		this.center = new Point(center_x, center_y);
		this.radius = radius;
	}
	
	public Circle(int center_x, int center_y, int radius,Color color) {
		// TODO Auto-generated constructor stub
		super(color);
		this.center = new Point(center_x, center_y);
		this.radius = radius;
	}

	public Circle(){
		super();
		this.center = new Point(100, 100);
		this.radius = 10;
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void drawYouUrSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.color);
		g.drawArc((int)this.center.getX()-this.getRadius(), (int)this.center.getY()-this.getRadius(), this.getRadius()*2, this.getRadius()*2, 0, 360);

	}

	@Override
	public boolean contains(int x, int y) {
		return (((x-this.center.getX())*(x-this.center.getX()))+((y-this.center.getY())*(y-this.center.getY())) <= this.getRadius()*this.getRadius());
	}
	

}
