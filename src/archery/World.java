package archery;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComponent;

public class World extends JComponent {

	private Person per;
	
	public World() throws IOException{
		per = new Person();
	}
	public Person getPer() {
		return per;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		per.draw(g);
	}
}
