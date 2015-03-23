package archery;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ArcheryFrame extends JFrame implements KeyListener {

	// private JLabel label;
	private ImageIcon image;
	private World world;

	public ArcheryFrame() throws IOException {
		this.setSize(800, 600);
		this.setTitle("Archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		// image = new ImageIcon("person.jpg");

		 world = new World();
		add(world);
		this.addKeyListener(this);
		// label = new JLabel();
		// label.setIcon(image);
		// label.
		// this.add(person);
		GameLoopThread t = new GameLoopThread(world);
		t.start();
	}

	public static void main(String[] args) {
		ArcheryFrame frame;
		try {
			frame = new ArcheryFrame();
			frame.setVisible(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Person person = world.getPer();
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
