package archery;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerArcheryFrame extends JFrame implements KeyListener {

	private Server server;
	private World world;

	public ServerArcheryFrame() throws IOException {
		this.setSize(800, 600);
		this.setTitle("Archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		world = new World();
		add(world);
		this.addKeyListener(this);
		this.setVisible(true);
		server = new Server(world.getClientPerson());
		GameLoopThread t = new GameLoopThread(world);
		t.start();
	}

	public static void main(String[] args) {
		ServerArcheryFrame frame;
		try {
			frame = new ServerArcheryFrame();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Person person = world.getServerPerson();
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
		PrintWriter writer = server.getSocketThread().getOut();
		writer.println(person.getX());
		writer.flush();
		writer.println(person.getY());
		writer.flush();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
