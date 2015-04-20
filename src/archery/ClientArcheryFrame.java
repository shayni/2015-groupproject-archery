package archery;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.swing.JFrame;

public class ClientArcheryFrame extends JFrame implements KeyListener, MouseMotionListener, Serializable {

	private World world;
	private Client client;
	private double mid;
	private boolean released;

	public ClientArcheryFrame() throws IOException {
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setTitle("client archery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		world = new World(this);
		add(world);
		this.addKeyListener(this);
		this.addMouseMotionListener(adapter);
		this.addMouseListener(listern);
		this.setVisible(true);
		mid = (this.getWidth() / 2) + 5;
		client = new Client(world.getServerPerson(), world.getServerArrow());

		GameLoopThread t = new GameLoopThread(world, this);
		t.start();
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

			// world.getSerArrow().setX1(world.getSerArrow().getX1()+10);
			// world.getSerArrow().setX2(world.getSerArrow().getX2()+10);

		}
	};
	
	
	MouseListener listern = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			world.getCliArrow().move();
			System.out.println("released client");
			released = true;

		}

	};
	
	public boolean isReleased() {
		return released;
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




	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
