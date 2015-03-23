package archery;

public class GameLoopThread extends Thread {

	private World world;
	

	public GameLoopThread(World world) {
		this.world=world;
	}

	public void run() {
		while (true) {
			
			world.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
