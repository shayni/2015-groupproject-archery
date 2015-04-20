package archery;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Client implements Serializable{

	private ClientThread thread;

	public Client(Person person, ServerBowAndArrow arrow) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 1112);

		if (socket != null) {
			thread = new ClientThread(socket, person, arrow);
			thread.start();
		}
	}

	public ClientThread getClientThread() {
		return thread;
	}

}
