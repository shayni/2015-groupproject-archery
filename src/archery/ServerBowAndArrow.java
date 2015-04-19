package archery;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ServerBowAndArrow  implements MouseMotionListener, Serializable {

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private Image picture;
	private int x;
	private int y;
	private int width;
	private int height;

	public ServerBowAndArrow(int x, int y) throws IOException {
		picture = ImageIO.read(new File("bowandarrow.png"));
	
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

	public void setY(int y) {
		this.y = y;
	}
	public int getTheWidth(){
		return width;
	}
	
	public void draw(Graphics g) {

		g.drawImage(picture, x, y, width, height, null);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println(" i moved");

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
