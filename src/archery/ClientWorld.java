package archery;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComponent;

public class ClientWorld extends JComponent {

	private PersonForClient per;
	
	public ClientWorld() throws IOException{
		per = new PersonForClient();
	}
	public PersonForClient getPer() {
		return per;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		per.draw(g);
	}
}
