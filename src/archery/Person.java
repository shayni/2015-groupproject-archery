package archery;

import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Person extends JComponent implements Serializable {

	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	private int x;
	private int y;
	private int height;
	private int width;

	public Person(int x, int y) throws IOException {
		height = 170;
		width = 120;
		icon = new ImageIcon("man2.jpg");
		this.x = x;
		this.y = y;

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void draw(Graphics g) {
		icon.paintIcon(this, g, x, y);
	}

}
