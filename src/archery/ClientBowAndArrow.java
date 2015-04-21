package archery;

import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ClientBowAndArrow extends JComponent implements Serializable {

	private ImageIcon icon;
	private int x;
	private int y;
	private int width;
	private int height;

	public ClientBowAndArrow(int x, int y) throws IOException {

		width = 90;
		height = 120;
		icon = new ImageIcon("clientBowAndArrow.png");
		this.x = x;
		this.y = y;

	}

	@Override
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	public int getTheWidth() {
		return width;
	}

	public int getTheHeight() {
		return height;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		icon.paintIcon(this, g, x, y);
	}
}
