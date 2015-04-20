package archery;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;
	// private BufferedReader reader;
	// private PrintWriter out;
	private Person person;
	private ClientBowAndArrow arrow;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public ServerThread(Socket socket, Person person, ClientBowAndArrow arrow) throws IOException {
		this.socket = socket;
		this.person = person;
		this.arrow = arrow;
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

			Messages msg = (Messages) input.readObject();
			msg.perform();
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
