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
	

	public GameLoopThread(World world, ServerArcheryFrame frame) throws IOException {
		this.world = world;
		this.frame = frame;
		//frame2 = frame2.getFrame();
		//frame2 = new ClientArcheryFrame();
		output = frame.getServer().getSocketThread().getOutput();
	}

	public GameLoopThread(World world, ClientArcheryFrame frame2) throws IOException {
		this.world = world;
		this.frame2 = frame2;
		//frame = new ServerArcheryFrame();
		output = frame2.getClient().getClientThread().getOutput();
	}
	/*public GameLoopThread(World world, Frame frame3) {
		this.world = world;
		this.frame3 = frame3;
		//output = frame2.getClient().getClientThread().getOutput();
	}*/

	@Override
	public void run() {
		while (true) {
			released = world.isReleased();
			if (released) {
				Person serverPerson = world.getServerPerson();
				Person clientPerson = world.getClientPerson();

				int start = clientPerson.getX();
				int top = clientPerson.getY();
				int bottom = clientPerson.getY() + (clientPerson.getHeight()*2);

				if (frame2 == null) {
					Arrow arrow = world.getSerArrow();
					int x = arrow.getX1();

					while (x < frame.getWidth()) {

						arrow.moveServer();
						try {
							Thread.sleep(1);
							world.repaint();
							ServerArrowReleased arrowReleased = new ServerArrowReleased(arrow);

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
							int x2 = arrow.getX2();
							int y2 = arrow.getY2();
							if (x2 > start && y2 > top && y2 < bottom) {
								world.setNumClientOuts();
								frame.getClientLabel().setText("Client outs: " + world.getNumClientOuts());
								ClientPersonHit personHit = new ClientPersonHit();
								output.writeObject(personHit);
								output.flush();
								output.reset();
								if (world.getNumClientOuts() == 4) {
									world.setClientOut();
									world.removeMouseListener(world.getAdapter());
									world.removeMouseMotionListener(world.getAdapter());
									ClientGameOver gameOver = new ClientGameOver();
									output.writeObject(gameOver);
									output.flush();
									output.reset();
								}
								break;
							}
						} catch (InterruptedException | IOException e) {
							e.printStackTrace();
						}
					}
					int x1 = serverPerson.getX() + 150;
					int y1 = serverPerson.getY() + 90;
					arrow.setX1(x1);
					arrow.setY1(y1);
					arrow.setX2(x1 + 100);
					arrow.setY2(y1);
					arrow.setAngle(0);
					ReplaceServerArrow replace = new ReplaceServerArrow(arrow);
					try {
						output.writeObject(replace);
						output.flush();
						output.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					int start2 = serverPerson.getX() + serverPerson.getWidth();
					int top2 = serverPerson.getY();
					int bottom2 = serverPerson.getY() + (serverPerson.getHeight()*2);
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
							int x3 = arrow2.getX2();
							int y3 = arrow2.getY2();
							if (x3 < start2 && y3 > top2 && y3 < bottom2) {
								world.setNumServerOuts();
								frame2.getServerLabel().setText("Server outs: " + world.getNumServerOuts());
								ServerPersonHit personHit = new ServerPersonHit();
								output.writeObject(personHit);
								output.flush();
								output.reset();
								if (world.getNumServerOuts() == 4) {
									world.setServerOut();
									world.removeMouseListener(world.getAdapter());
									world.removeMouseMotionListener(world.getAdapter());
									ServerGameOver gameOver = new ServerGameOver();
									output.writeObject(gameOver);
									output.flush();
									output.reset();
								}
								break;
							}
							Thread.sleep(1);
							world.repaint();
						} catch (InterruptedException | IOException e) {
							e.printStackTrace();
						}

					}
					int x1 = clientPerson.getX();
					int y1 = clientPerson.getY() + 90;
					arrow2.setX1(x1);
					arrow2.setY1(y1);
					arrow2.setX2(x1 - 100);
					arrow2.setY2(y1);
					arrow2.setAngle(0);
					ReplaceClientArrow replace = new ReplaceClientArrow(arrow2);
					try {
						System.out.println("sending replacement arrow");
						output.writeObject(replace);
						output.flush();
						output.reset();
					} catch (IOException e) {
						e.printStackTrace();
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
