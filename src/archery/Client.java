package archery;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Client {

	private ClientThread thread;

	public Client(Person person) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 1112);

		if (socket != null) {
			thread = new ClientThread(socket, person);
			thread.start();
		}
	}

	public ClientThread getClientThread() {
		return thread;
	}

}
