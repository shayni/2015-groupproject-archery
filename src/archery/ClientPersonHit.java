package archery;

import java.io.Serializable;

public class ClientPersonHit implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private int numOuts;

	public ClientPersonHit(int numOuts) {
		this.numOuts = numOuts;
	}

	@Override
	public void perform(World world) {
		world.setNumClientOuts();
	}

}
