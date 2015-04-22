package archery;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class World extends JComponent implements Serializable, MouseListener {

	private Person serverPerson;
	private Person clientPerson;
	// private ServerBowAndArrow serverArrow;
	// private ClientBowAndArrow clientArrow;
	private Arrow serArrow;
	private Arrow cliArrow;
	private boolean released;
	private int numServerOuts;
	private int numClientOuts;
	private boolean serverOut;
	private boolean clientOut;
	private JFrame frame;

	public void setReleased(boolean released) {
		this.released = released;
	}

	public boolean isReleased() {
		return released;
	}

	public World(JFrame frame) throws IOException {
		this.frame = frame;
		int h = frame.getHeight();
		int w = frame.getWidth();
		serverPerson = new Person((int) (w * .1), (int) (h * .5));
		clientPerson = new Person((int) (w * .8), (int) (h * .5));
		int x = serverPerson.getX() + 150;
		int y = serverPerson.getY() + 90;
		int cx = clientPerson.getX();
		int cy = clientPerson.getY() + 90;
		numServerOuts = 0;
		numClientOuts = 0;
		serverOut = false;
		clientOut = false;
		// serverArrow = new ServerBowAndArrow(x, y);
		// clientArrow = new ClientBowAndArrow(cx, cy);
		// serArrow = new Arrow(serverArrow.getX(), serverArrow.getY() +
		// (serverArrow.getHeight() / 2), serverArrow.getX()
		// + serverArrow.getWidth(), serverArrow.getY() +
		// (serverArrow.getHeight() / 2));
		// cliArrow = new Arrow(clientArrow.getX() + 5, clientArrow.getY() +
		// (clientArrow.getTheHeight() / 2),
		// clientArrow.getX() + clientArrow.getTheWidth(), clientArrow.getY() +
		// (clientArrow.getTheHeight() / 2));
		serArrow = new Arrow(x, y, x + 100, y);
		cliArrow = new Arrow(cx, cy, cx - 100, cy);
	}

	public Person getServerPerson() {
		return serverPerson;
	}

	public Person getClientPerson() {
		return clientPerson;
	}

	public void setNumServerOuts() {
		numServerOuts = numServerOuts + 1;
	}

	public void setNumClientOuts() {
		numClientOuts = numClientOuts + 1;
	}

	/*
	 * public ServerBowAndArrow getServerArrow() { return serverArrow; }
	 * 
	 * public ClientBowAndArrow getClientArrow() { return clientArrow; }
	 */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		serverPerson.draw(g);
		clientPerson.draw(g);
		// serverArrow.draw(g);
		// clientArrow.draw(g);
		g.setColor(Color.GREEN);
		serArrow.draw(g);
		cliArrow.draw(g);
		g.setColor(Color.RED);
		g.setFont(new Font("Kristen ITC", Font.BOLD, 30));
		if (serverOut) {
			g.drawString("You are dead!", serverPerson.getX(), serverPerson.getY());
		} else if (clientOut) {
			g.drawString("You are dead!", clientPerson.getX(), clientPerson.getY());
		}
	}

	public Arrow getSerArrow() {
		return serArrow;
	}

	public Arrow getCliArrow() {
		return cliArrow;
	}

	public int getNumServerOuts() {
		return numServerOuts;
	}

	public int getNumClientOuts() {
		return numClientOuts;
	}

	public void setServerOut() {
		serverOut = true;
	}

	public void setClientOut() {
		clientOut = true;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		released = true;

	}

	public JFrame getFrame() {
		return frame;
	}

}
