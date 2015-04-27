package archery;

import java.io.Serializable;

public class ServerGameOver implements Messages, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void perform(World world) {
		world.setServerOut();
		world.removeMouseListener(world.getAdapter());
		world.removeMouseMotionListener(world.getAdapter());
	}

}
