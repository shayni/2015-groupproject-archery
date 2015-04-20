package archery;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import java.net.Socket;

public class ServerThread extends Thread implements Serializable {

	private Socket socket;
	// private BufferedReader reader;
	// private PrintWriter out;
	private Person person;
	private ClientBowAndArrow arrow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private World world;

	public ServerThread(Socket socket, World world) throws IOException {
		this.socket = socket;
		this.world = world;
		// this.person = person;
		// this.arrow = arrow;
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();
			// reader = new BufferedReader(new InputStreamReader(in));
			// out = new PrintWriter(socket.getOutputStream(), true);
			input = new ObjectInputStream(in);
			while (true) {
				Messages msg = (Messages) input.readObject();
				msg.perform(world);
			}
			// String inputLine;

			/*
			 * while ((inputLine = reader.readLine()) != null) {
			 * 
			 * person.setX(Integer.valueOf(inputLine));
			 * person.setY(Integer.valueOf(reader.readLine()));
			 * arrow.setX(Integer.valueOf(reader.readLine()));
			 * arrow.setY(Integer.valueOf(reader.readLine())); }
			 */

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public PrintWriter getOut() { return out; }
	 */
	public ObjectOutputStream getOutput() {
		return output;
	}

}
