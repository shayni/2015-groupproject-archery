package archery;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class World extends JComponent implements Serializable{

	private Person serverPerson;
	private Person clientPerson;
	private ServerBowAndArrow serverArrow;
	private ClientBowAndArrow clientArrow;
	private Arrow serArrow;
	private Arrow cliArrow;

	// private JFrame frame;

	public World(JFrame frame) throws IOException {
		// this.frame=frame;
		int h = frame.getHeight();
		int w = frame.getWidth();
		serverPerson = new Person((int) (w * .1), (int) (h * .5));
		clientPerson = new Person((int) (w * .8), (int) (h * .5));
		int x = serverPerson.getX() + 120;
		int y = serverPerson.getY() - 15;
		int cx = clientPerson.getX() - 90;
		int cy = clientPerson.getY() - 15;
		serverArrow = new ServerBowAndArrow(x, y);
		clientArrow = new ClientBowAndArrow(cx, cy);
		serArrow = new Arrow(serverArrow.getX(), serverArrow.getY() + (serverArrow.getHeight() / 2), serverArrow.getX()
				+ serverArrow.getWidth(), serverArrow.getY() + (serverArrow.getHeight() / 2));
		cliArrow = new Arrow(clientArrow.getX() + 5, clientArrow.getY() + (clientArrow.getTheHeight() / 2),
				clientArrow.getX() + clientArrow.getTheWidth(), clientArrow.getY() + (clientArrow.getTheHeight() / 2));
	}

	public Person getServerPerson() {
		return serverPerson;
	}

	public Person getClientPerson() {
		return clientPerson;
	}

	public ServerBowAndArrow getServerArrow() {
		return serverArrow;
	}

	public ClientBowAndArrow getClientArrow() {
		return clientArrow;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		serverPerson.draw(g);
		clientPerson.draw(g);
		serverArrow.draw(g);
		clientArrow.draw(g);
		g.setColor(Color.GREEN);
		serArrow.draw(g);
		System.out.println("world: " + serArrow.getX1() + " " + serArrow.getX2());
		cliArrow.draw(g);
	}

	public Arrow getSerArrow() {
		return serArrow;
	}

	public Arrow getCliArrow() {
		return cliArrow;
	}

}
