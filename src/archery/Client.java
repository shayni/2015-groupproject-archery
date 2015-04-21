package archery;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private ClientThread thread;

	public Client(World world) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 1112);

		if (socket != null) {
			thread = new ClientThread(socket, world);
			thread.start();
		}
	}

	public ClientThread getClientThread() {
		return thread;
	}

}
