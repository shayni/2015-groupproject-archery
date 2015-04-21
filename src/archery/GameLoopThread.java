package archery;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameLoopThread extends Thread {

	private World world;
	private ServerArcheryFrame frame;
	private ClientArcheryFrame frame2;
	private boolean released;
	private ObjectOutputStream output;

	public GameLoopThread(World world, ServerArcheryFrame frame) {
		this.world = world;
		this.frame = frame;
		output = frame.getServer().getSocketThread().getOutput();
	}

	public GameLoopThread(World world, ClientArcheryFrame frame2) {
		this.world = world;
		this.frame2 = frame2;
		output = frame2.getClient().getClientThread().getOutput();
	}

	@Override
	public void run() {
		while (true) {
			released = world.isReleased();
			if (released) {
				Person serverPerson = world.getServerPerson();
				Person clientPerson = world.getClientPerson();

				int start = clientPerson.getX();
				int top = clientPerson.getY();
				int bottom = clientPerson.getY() + serverPerson.getHeight();

				if (frame2 == null) {
					Arrow arrow = world.getSerArrow();
					int x = arrow.getX1();
					int x2 = arrow.getX2();
					while (x < frame.getWidth()) {

						arrow.moveServer();
						try {
							Thread.sleep(1);
							world.repaint();
							ArrowReleased arrowReleased = new ArrowReleased(arrow);

							try {
								output.writeObject(arrowReleased);
								output.flush();
								output.reset();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							x = arrow.getX1();

							Thread.sleep(1);
							world.repaint();
							if (x2 > start && x2 > top && x2 < bottom) {
								frame.setNumbOuts();
								break;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int x1 = serverPerson.getX() + 150;
					int y1 = serverPerson.getY() + 90;
					arrow.setX1(x1);
					arrow.setY1(y1);
					arrow.setX2(x1 + 100);
					arrow.setY2(y1);
					ReplaceServerArrow replace = new ReplaceServerArrow(arrow);
					try {
						output.writeObject(replace);
						output.flush();
						output.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (frame.gameOver()) {
						world.setServerOut(true);
					}
				} else {
					Arrow arrow2 = world.getCliArrow();
					int x2 = arrow2.getX1();
					while (x2 > 0) {
						arrow2.moveClient();
						try {
							Thread.sleep(1);
							world.repaint();
							ClientArrowReleases arrowReleased2 = new ClientArrowReleases(arrow2);

							try {
								output.writeObject(arrowReleased2);
								output.flush();
								output.reset();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							x2 = arrow2.getX1();

							Thread.sleep(1);
							world.repaint();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					int x1 = clientPerson.getX();
					int y1 = clientPerson.getY() + 90;
					arrow2.setX1(x1);
					arrow2.setY1(y1);
					arrow2.setX2(x1 - 100);
					arrow2.setY2(y1);
					ReplaceClientArrow replace = new ReplaceClientArrow(arrow2);
					try {
						output.writeObject(replace);
						output.flush();
						output.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (frame2.gameOver()) {
						world.setClientOut(true);
					}
				}
			}
			world.setReleased(false);

			world.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

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
			released = true;
			// Arrow arrow = world.getSerArrow();
			// int x = arrow.getX1();
			/*
			 * while (x < frame.getWidth()) { arrow.moveServer(); ArrowReleased
			 * arrowReleased = new ArrowReleased(arrow);
			 * 
			 * try { output.writeObject(arrowReleased); output.flush();
			 * output.reset(); } catch (IOException e1) { e1.printStackTrace();
			 * } x = arrow.getX1(); try { Thread.sleep(1000); } catch
			 * (InterruptedException e1) {
			 * 
			 * e1.printStackTrace(); }
			 * 
			 * }
			 */
		}

	};

}
