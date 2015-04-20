package archery;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements  Serializable{

	private ServerSocket serverSocket;
	private ServerThread thread;
	private World world;

	public Server(World world) throws IOException {
		serverSocket = new ServerSocket(1112);
		Socket socket = serverSocket.accept();
		if (socket != null) {
			thread = new ServerThread(socket, world);
			thread.start();

		}
	}

	public ServerThread getSocketThread() {
		return thread;
	}

}
