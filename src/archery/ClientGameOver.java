package archery;

import java.io.Serializable;

public class ClientGameOver implements Messages, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void perform(World world) {
		world.setClientOut();
	}

}
