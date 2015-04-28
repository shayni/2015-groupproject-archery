package archery;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class World extends JComponent implements Serializable, MouseListener,
		MouseMotionListener {

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
	private JLabel serverLabel;
	private JLabel clientLabel;

	public World(JFrame frame, JLabel serverLabel, JLabel clientLabel)
			throws IOException {
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
		this.clientLabel = clientLabel;
		this.serverLabel = serverLabel;
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
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);
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

		Graphics2D gr = (Graphics2D) g;

		Line2D line = new Line2D.Double(serArrow.getX1(), serArrow.getY1(),
				serArrow.getX2(), serArrow.getY2());
		Line2D line2 = new Line2D.Double(cliArrow.getX1(), cliArrow.getY1(),
				cliArrow.getX2(), cliArrow.getY2());

		AffineTransform rotate = AffineTransform
				.getRotateInstance(Math.toRadians(serArrow.getAngle()),
						line.getX1(), line.getY1());
		AffineTransform rotate2 = AffineTransform.getRotateInstance(
				Math.toRadians(cliArrow.getAngle()), line2.getX1(),
				line2.getY1());

		gr.draw(rotate.createTransformedShape(line));
		gr.draw(rotate2.createTransformedShape(line2));

		g.setColor(Color.RED);
		g.setFont(new Font("Kristen ITC", Font.BOLD, 30));
		if (serverOut) {
			g.drawString("You are dead!", serverPerson.getX(),
					serverPerson.getY());
		} else if (clientOut) {
			g.drawString("You are dead!", clientPerson.getX(),
					clientPerson.getY());
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

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// released = true;

	}

	public MouseAdapter getAdapter() {
		return adapter;
	}

	private MouseAdapter adapter = new MouseAdapter() {

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

		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("dragged");
			Point point = e.getPoint();

			// xPressed = world.getSerArrow().getX1();
			// yPressed = world.getSerArrow().getY1();
			// xDragged = e.getX();

			xDragged = point.x;
			System.out.println(xDragged);
			yDragged = point.y;
			System.out.println(yDragged);

			double angle = Math.atan2((yDragged - yPressed),
					(xDragged - xPressed));

			/*
			 * x = xDragged;
			 * 
			 * h = (float) Math.sqrt(Math.pow(xDragged - xPressed, 2) +
			 * Math.pow(yDragged - yPressed, 2)); o = (float)
			 * Math.sqrt(Math.pow(xDragged - x, 2) + Math.pow(yDragged - y, 2));
			 * a = (float) Math.sqrt(Math.pow(xPressed - x, 2) +
			 * Math.pow(yPressed - y, 2));
			 * 
			 * double angle = Math.acos((a / h));
			 * 
			 * 
			 * double angle1 = Math .atan2(yPressed - yDragged, xPressed -
			 * xDragged); double angle2 = Math.atan2(yPressed - y, xPressed -
			 * x);
			 * 
			 * 
			 * double amount =Math.abs(angle1) - Math.abs(angle2);
			 */
			double toDegree = Math.toDegrees(angle);
			System.out.println(toDegree);
			if (xPressed < (frame.getWidth() / 2)) {
				getSerArrow().rotate(180 - (toDegree / -1));
				ServerRotateArrow sra = new ServerRotateArrow(180 - (toDegree / -1));
				//ObjectOutputStream output = 
				/*output.writeObject(sra);
				output.flush();
				output.reset();*/
			} else {
				getCliArrow().rotate((toDegree));
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			released = true;

		}

	};

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// System.out.println("dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public JLabel getServerLabel() {
		return serverLabel;
	}

	public void setServerLabel(JLabel serverLabel) {
		this.serverLabel = serverLabel;
	}

	public JLabel getClientLabel() {
		return clientLabel;
	}

	public void setClientLabel(JLabel clientLabel) {
		this.clientLabel = clientLabel;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public boolean isReleased() {
		return released;
	}

}
