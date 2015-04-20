package archery;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ClientBowAndArrow extends JComponent implements Serializable{
	private Image picture;
	private ImageIcon icon;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public ClientBowAndArrow(int x,int y) throws IOException{
		
		width = 90;
		height = 120;
		//picture = ImageIO.read(new File("clientBowAndArrow.png"));
		 icon = new ImageIcon("clientBowAndArrow.png");
		//icon= new ImageIcon(i.getScaledInstance(width, height, Image.SCALE_SMOOTH));
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
		
		//g.drawImage(icon, x, y, width, height, null);
		icon.paintIcon(this, g, x, y);
	}
}
