package archery;

import java.io.Serializable;

import javax.swing.JLabel;

public class ClientPersonHit implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	//private JLabel label;

	public ClientPersonHit() {
	//	this.label = label;
	}

	@Override
	public void perform(World world) {
		world.setNumClientOuts();
		System.out.println("number passed" +world.getNumClientOuts() );
		JLabel label = world.getClientLabel();

		label.setText("Client outs: " + world.getNumClientOuts());
	}

}
