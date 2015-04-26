package archery;

import java.io.Serializable;

import javax.swing.JLabel;

public class ServerPersonHit implements Messages, Serializable {

	private static final long serialVersionUID = 1L;

	// private JLabel serverLabel;

	public ServerPersonHit() {
		// this.serverLabel = serverLabel;
	}

	@Override
	public void perform(World world) {
		world.setNumServerOuts();
		System.out.println("number passed" + world.getNumServerOuts());
		JLabel label = world.getServerLabel();
		label.setText("Server outs: " + world.getNumServerOuts());
	}

}
