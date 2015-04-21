package archery;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.swing.JFrame;

public class ServerArcheryFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private Server server;
	private World world;
	private double mid;
	private boolean released;
	private ObjectOutputStream output;

	public ServerArcheryFrame() throws IOException {
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setTitle("server archery");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);

		mid = (this.getWidth() / 2) - 5;

		world = new World(this);
		add(world);
		this.addKeyListener(this);
		addMouseListener(world);
		//this.addMouseMotionListener(adapter);
		//this.addMouseListener(listener);

		this.setVisible(true);
		server = new Server(world);
		output = server.getSocketThread().getOutput();

		GameLoopThread t = new GameLoopThread(world, this);
		t.start();
	}

	public Server getServer() {
		return server;
	}

	MouseAdapter adapter = new MouseAdapter() {

		int xPressed;
		int yPressed;
		int xDragged;
		int yDragged;
		int x;
		int y;

		float h;
		float o;
		float a;

		@Override
		public void mousePressed(MouseEvent e) {
			xPressed = e.getX();
			yPressed = e.getY();
			y = yPressed;
		}

		public void mouseDragged(MouseEvent e) {
			xDragged = e.getX();
			yDragged = e.getY();
			x = xDragged;

			h = (float) Math.sqrt(Math.pow(xDragged - xPressed, 2) + Math.pow(yDragged - yPressed, 2));
			o = (float) Math.sqrt(Math.pow(xDragged - x, 2) + Math.pow(yDragged - y, 2));
			a = (float) Math.sqrt(Math.pow(xPressed - x, 2) + Math.pow(yPressed - y, 2));

			double angle = Math.acos((a / h));

			/*
			 * double angle1 = Math .atan2(yPressed - yDragged, xPressed -
			 * xDragged); double angle2 = Math.atan2(yPressed - y, xPressed -
			 * x);
			 * 
			 * 
			 * double amount =Math.abs(angle1) - Math.abs(angle2);
			 */
			double toDegree = Math.toDegrees(angle);
			System.out.println(toDegree);
			// world.getSerArrow().move();

		}

		public void mouseReleased(MouseEvent e) {
		}
	};



	@Override
	public void keyPressed(KeyEvent e) {
		Person person = world.getServerPerson();
		Arrow arrow = world.getSerArrow();
		// ServerBowAndArrow arrow = world.getServerArrow();
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_8:
			if (person.getY() > 0) {
				person.setY(person.getY() - 10);
				arrow.setY1(arrow.getY1() - 10);
				arrow.setY2(arrow.getY2() - 10);
			}
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_2:
			if (person.getY() + person.getHeight() < this.getHeight()) {
				person.setY(person.getY() + 10);
				arrow.setY1(arrow.getY1() + 10);
				arrow.setY2(arrow.getY2() + 10);
			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_6:
			int tempX = arrow.getX1();
			int tempX2 = arrow.getX2();
			if (tempX2 < mid) {
				person.setX(person.getX() + 10);
				arrow.setX1(arrow.getX1() + 10);
				arrow.setX2(arrow.getX2() + 10);
			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_4:
			if (person.getX() > 0) {
				person.setX(person.getX() - 10);
				arrow.setX1(arrow.getX1() - 10);
				arrow.setX2(arrow.getX2() - 10);
			}
			break;
		}

		PersonMoves moves = new PersonMoves(person, arrow);
		try {
			output.writeObject(moves);
			output.flush();
			output.reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

/*	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}*/

	MouseListener listener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//released = true;
		/*	JFrame frame = getFrame();
			Arrow arrow = world.getSerArrow();
			int x = arrow.getX1();
			while (x < frame.getWidth()) {
				arrow.moveServer();
				ArrowReleased arrowReleased = new ArrowReleased(arrow);

				try {
					output.writeObject(arrowReleased);
					output.flush();
					output.reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				x = arrow.getX1();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}

			}*/
		}

	};

	public JFrame getFrame() {
		return this;
	}

	public boolean isReleased() {
		return released;
	}

}
