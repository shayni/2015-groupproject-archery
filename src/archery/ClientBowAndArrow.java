package archery;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ClientBowAndArrow implements Serializable{
	BufferedImage picture;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public ClientBowAndArrow(int x,int y) throws IOException{
		picture = ImageIO.read(new File("clientBowAndArrow.png"));
		this.x = x;
		this.y = y;
		width = 90;
		height = 120;
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
	public int getTheWidth(){
		return width;
	}
	public int getTheHeight(){
		return height;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		
		g.drawImage(picture, x, y, width, height, null);
		
	}
}
