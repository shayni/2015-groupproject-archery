package archery;

import java.io.Serializable;

import javax.swing.JLabel;

public class ServerPersonHit implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private JLabel serverLabel;

	public ServerPersonHit(JLabel serverLabel) {
		this.serverLabel = serverLabel;
	}

	@Override
	public void perform(World world) {
		world.setNumServerOuts();
		serverLabel.setText("Server outs: " + world.getNumServerOuts());
	}

}
