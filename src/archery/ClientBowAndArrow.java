package archery;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ClientBowAndArrow {
	BufferedImage picture;
	private int x;
	private int y;
	
	public ClientBowAndArrow(int x,int y) throws IOException{
		picture = ImageIO.read(new File("clientBowAndArrow.png"));
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		
		g.drawImage(picture, x, y, 90, 120, null);
		
	}
}
