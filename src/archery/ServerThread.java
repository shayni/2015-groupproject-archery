package archery;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private World world;

	public ServerThread(Socket socket, World world) throws IOException {
		this.socket = socket;
		this.world = world;
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();

			input = new ObjectInputStream(in);
			while (true) {
				Messages msg = (Messages) input.readObject();
				msg.perform(world);
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ObjectOutputStream getOutput() {
		return output;
	}

}
