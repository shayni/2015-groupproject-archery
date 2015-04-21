package archery;



public class GameLoopThread extends Thread {

	private World world;
	private ServerArcheryFrame frame;
	private ClientArcheryFrame frame2;

	public GameLoopThread(World world, ServerArcheryFrame frame) {
		this.world = world;
		this.frame = frame;
	}

	public GameLoopThread(World world, ClientArcheryFrame frame2) {
		this.world = world;
		this.frame2 = frame2;
	}

	@Override
	public void run() {
		while (true) {

			world.repaint();
			/*
			 * if (frame.isReleased()){ world.getSerArrow().move();
			 * System.out.println ("sever arrow moving"); }
			 */
			/*
			 * if (frame2.isReleased()){ world.getCliArrow().move(); }
			 */
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
