package archery;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComponent;

public class World extends JComponent {

	private Person serverPerson;
	private Person clientPerson;

	public World() throws IOException {
		serverPerson = new Person(40, 40);
		clientPerson = new Person(700, 40);
	}

	public Person getServerPerson() {
		return serverPerson;
	}

	public Person getClientPerson() {
		return clientPerson;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		serverPerson.draw(g);
		clientPerson.draw(g);
	}
}
