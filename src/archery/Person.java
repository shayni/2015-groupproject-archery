package archery;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Person implements Serializable {

	// private JLabel label;
	private Image picture; // = ImageIO.read(new File("person.jpg"));
	private int x;
	private int y;
	private int height;
	private int width;

	public Person(int x, int y) throws IOException {
		picture = ImageIO.read(new File("person.jpg"));
		this.x = x;
		this.y = y;
		height = 170;
		width = 120;

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void draw(Graphics g) {
		g.drawImage(picture, x, y, width, height, null);

	}

}
