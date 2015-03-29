package archery;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	private ServerThread thread;

	public Server(Person person, ClientBowAndArrow arrow) throws IOException {
		serverSocket = new ServerSocket(1112);
		Socket socket = serverSocket.accept();
		if (socket != null) {
			thread = new ServerThread(socket, person, arrow);
			thread.start();

		}
	}

	public ServerThread getSocketThread() {
		return thread;
	}

}
