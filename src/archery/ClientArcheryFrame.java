package archery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ClientArcheryFrame extends JFrame implements KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private World world;
	private Client client;
	private double mid;
	private boolean released;
	private JLabel serverLabel;
	private JLabel clientLabel;

	private ObjectOutputStream output;

	public ClientArcheryFrame() throws IOException {
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setTitle("client archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		// this.setBackground(Color.GREEN);
		this.setVisible(true);

		serverLabel = new JLabel("Server outs: 0", SwingConstants.CENTER);
		clientLabel = new JLabel("Client outs: 0", SwingConstants.CENTER);

		world = new World(this, serverLabel, clientLabel);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));

		panel.add(serverLabel);
		panel.add(clientLabel);

		container.add(world, BorderLayout.CENTER);
		container.add(panel, BorderLayout.NORTH);
		this.addKeyListener(this);
		this.setVisible(true);
		mid = (this.getWidth() / 2) + 5;
		client = new Client(world);
		output = client.getClientThread().getOutput();
		GameLoopThread t = new GameLoopThread(world, this);
		t.start();
	}

	public JLabel getServerLabel() {
		return serverLabel;
	}

	public JLabel getClientLabel() {
		return clientLabel;
	}

	public ClientArcheryFrame getFrame() {
		return this;
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

	/*
	 * MouseListener listener = new MouseListener() {
	 * 
	 * @Override public void mouseClicked(MouseEvent e) {
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) {
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) {
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) {
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { Arrow arrow =
	 * world.getCliArrow(); arrow.moveClient(); ClientArrowReleases
	 * arrowReleased = new ClientArrowReleases(arrow);
	 * 
	 * try { output.writeObject(arrowReleased); output.flush(); output.reset();
	 * } catch (IOException e1) { e1.printStackTrace(); } }
	 * 
	 * };
	 */

	public boolean isReleased() {
		return released;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Person person = world.getClientPerson();
		Arrow arrow = world.getCliArrow();
		// ClientBowAndArrow arrow = world.getClientArrow();
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
			if (person.getX() + person.getWidth() < this.getWidth()) {
				person.setX(person.getX() + 10);
				arrow.setX1(arrow.getX1() + 10);
				arrow.setX2(arrow.getX2() + 10);
			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_4:
			if (arrow.getX2() > mid) {
				person.setX(person.getX() - 10);
				arrow.setX1(arrow.getX1() - 10);
				arrow.setX2(arrow.getX2() - 10);
			}
			break;
		}

		ClientPersonMoves moves = new ClientPersonMoves(person, arrow);
		try {
			output = client.getClientThread().getOutput();
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
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void mouseDragged(MouseEvent arg0) {
	 * 
	 * }
	 * 
	 * @Override public void mouseMoved(MouseEvent arg0) {
	 * 
	 * }
	 */

}
