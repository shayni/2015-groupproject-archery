package archery;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

	
	private World world;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public ClientThread(Socket socket, World world) throws IOException {
		this.socket = socket;
		this.world = world;
		output = new ObjectOutputStream(socket.getOutputStream());
		InputStream in = socket.getInputStream();
		input = new ObjectInputStream(in);
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Messages msg = (Messages) input.readObject();
				msg.perform(world);
				System.out.println("i performed!");
			}

			/*
			 * out = new PrintWriter(socket.getOutputStream(), true); String
			 * inputLine; while ((inputLine = reader.readLine()) != null) {
			 * 
			 * person.setX(Integer.valueOf(inputLine));
			 * 
			 * person.setY(Integer.valueOf(reader.readLine()));
			 * 
			 * arrow.setX(Integer.valueOf(reader.readLine()));
			 * arrow.setY(Integer.valueOf(reader.readLine())); }
			 */

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
