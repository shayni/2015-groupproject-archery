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
				if (frame2 == null) {
					Arrow arrow = world.getSerArrow();
					int x = arrow.getX1();
					while (x < frame.getWidth()) {
						arrow.moveServer();
						try {
							Thread.sleep(70);
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

							Thread.sleep(70);
							world.repaint();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				} else {
					Arrow arrow2 = world.getCliArrow();
					int x2 = arrow2.getX1();
					while (x2 > 0) {
						arrow2.moveClient();
						try {
							Thread.sleep(70);
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

							Thread.sleep(70);
							world.repaint();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

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
