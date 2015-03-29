package archery;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

public class ClientArcheryFrame extends JFrame implements KeyListener {

	private World world;
	private Client client;

	public ClientArcheryFrame() throws IOException {
		this.setSize(800, 600);
		this.setTitle("Archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		world = new World();
		add(world);
		this.addKeyListener(this);
		GameLoopThread t = new GameLoopThread(world);
		t.start();
		client = new Client(world.getServerPerson());
	}

	public static void main(String[] args) {
		ClientArcheryFrame frame;
		try {
			frame = new ClientArcheryFrame();
			frame.setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Person person = world.getClientPerson();
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_8:
			person.setY(person.getY() - 10);
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_2:
			person.setY(person.getY() + 10);

			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_6:
			person.setX(person.getX() + 10);

			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_4:
			person.setX(person.getX() - 10);

			break;
		}
		PrintWriter writer = client.getClientThread().getOut();
		writer.println(person.getX());
		writer.println(person.getY());
		writer.flush();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
