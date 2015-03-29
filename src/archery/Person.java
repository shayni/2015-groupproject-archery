package archery;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Person {

	// private JLabel label;
	BufferedImage picture; // = ImageIO.read(new File("person.jpg"));
	private int x;
	private int y;

	public Person(int x, int y) throws IOException {
		picture = ImageIO.read(new File("person.jpg"));
		this.x = x;
		this.y = y;

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

	public void draw(Graphics g) {
		// label.setIcon(picture);
		g.drawImage(picture, x, y, 120, 170, null);

	}
}
