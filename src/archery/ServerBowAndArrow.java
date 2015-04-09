package archery;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ServerBowAndArrow extends JComponent implements MouseMotionListener {

	private BufferedImage picture;
	private int x;
	private int y;

	public ServerBowAndArrow(int x, int y) throws IOException {
		picture = ImageIO.read(new File("bowandarrow.png"));
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

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println(" i moved");

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
