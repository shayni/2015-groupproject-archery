package archery;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.Serializable;

public class Arrow implements Serializable {

	private static final long serialVersionUID = 1L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private double angle;
	private int t = 1;

	public Arrow(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void draw(Graphics g) {

		g.drawLine(x1, y1, x2, y2);
	}

	public void draw2D(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

	public void moveClient() {
		// setX1(x1 - 1);
		// setX2(x2 - 1);

		x1 = (int) (x1 + 2 * t * Math.cos(angle));
		// x1 = (int) (2 *t *Math.cos(angle));
		x2 = x1 + 100;
		y1 = (int) ((y1 + 2 * t * Math.sin(angle)) + (.5 * -9.8 * Math.pow(t, 2)));
		// y1 = (int) (( 2* t* Math.sin(angle)) +( .5 * -9.8 * Math.pow(t, 2)));
		y2 = y1;
		t++;

	}

	public void moveServer() {
		// setX1(x1 + 1);
		// setX2(x2 + 1);

		x1 = (int) (x1 + 5 * t * Math.cos(angle));
		x2 = x1 + 100;
		y1 = (int) ((y1 + 5 * t * Math.sin(angle)) + (.5 * -9.8 * Math.pow(t, 2)));
		y2 = y1;
		t++;

	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;

	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

}
