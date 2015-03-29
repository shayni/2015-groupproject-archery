package archery;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PersonForClient {
	
	// private JLabel label;
		BufferedImage picture; // = ImageIO.read(new File("person.jpg"));
		private int x;
		private int y;

		public PersonForClient() throws IOException {
			picture = ImageIO.read(new File("person.jpg"));
			x = 700;
			y = 40;
		
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
			g.drawImage(picture, x, y, 60, 110, null);

		}

}
