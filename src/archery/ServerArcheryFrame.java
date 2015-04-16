package archery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerArcheryFrame extends JFrame implements KeyListener,
		MouseMotionListener {

	private Server server;
	private World world;
	private double mid;

	public ServerArcheryFrame() throws IOException {
		// this.setSize(800, 600);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setTitle("server archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		mid = (this.getWidth() / 2) - 5;

		world = new World(this);
		add(world);
		this.addKeyListener(this);
		this.addMouseMotionListener(adapter);

		this.setVisible(true);
		server = new Server(world.getClientPerson(), world.getClientArrow());
		GameLoopThread t = new GameLoopThread(world);
		t.start();
	}

	MouseAdapter adapter = new MouseAdapter() {

		int xPressed;
		int yPressed;
		int xDragged;
		int yDragged;

		@Override
		public void mousePressed(MouseEvent e) {
			xPressed = e.getX();
			yPressed = e.getY();
		}

		public void mouseDragged(MouseEvent e) {
			xDragged = e.getX();
			yDragged = e.getY();

			int amount = yPressed - yDragged;
			world.getSerArrow().setY2(world.getSerArrow().getY2() + amount);

		}
	};

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
		ServerBowAndArrow arrow = world.getServerArrow();
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
			if (arrow.getX() + arrow.getTheWidth() < mid) {
				person.setX(person.getX() + 10);
				arrow.setX(arrow.getX() + 10);
			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_4:
			if (person.getX() > 0) {
				person.setX(person.getX() - 10);
				arrow.setX(arrow.getX() - 10);
			}
			break;
		}
		PrintWriter writer = server.getSocketThread().getOut();
		writer.println(person.getX());
		writer.println(person.getY());
		writer.println(arrow.getX());
		writer.println(arrow.getY());
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// System.out.println("i moved!");
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

}
