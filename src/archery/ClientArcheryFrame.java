package archery;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

public class ClientArcheryFrame extends JFrame implements KeyListener {

	private World world;
	private Client client;
	private double mid;

	public ClientArcheryFrame() throws IOException {
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setTitle("client archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		world = new World(this);
		add(world);
		this.addKeyListener(this);
		this.setVisible(true);
		mid = (this.getWidth() / 2) + 5;
		GameLoopThread t = new GameLoopThread(world);
		t.start();
		client = new Client(world.getServerPerson(), world.getServerArrow());
	}

	public static void main(String[] args) {
		ClientArcheryFrame frame;
		try {
			frame = new ClientArcheryFrame();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Person person = world.getClientPerson();
		ClientBowAndArrow arrow = world.getClientArrow();
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_8:
			if (person.getY() > 0) {
				person.setY(person.getY() - 10);
				arrow.setY(arrow.getY() - 10);
			}
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_2:
			if (person.getY() + person.getHeight() < this.getHeight()) {
				person.setY(person.getY() + 10);
				arrow.setY(arrow.getY() + 10);
			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_6:
			if (person.getX() + person.getWidth() < this.getWidth()) {
				person.setX(person.getX() + 10);
				arrow.setX(arrow.getX() + 10);
			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_4:
			if (arrow.getX() > mid) {
				person.setX(person.getX() - 10);
				arrow.setX(arrow.getX() - 10);
			}
			break;
		}
		PrintWriter writer = client.getClientThread().getOut();
		writer.println(person.getX());
		writer.println(person.getY());
		writer.println(arrow.getX());
		writer.println(arrow.getY());
		writer.flush();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
