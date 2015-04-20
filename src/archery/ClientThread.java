package archery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class ClientThread extends Thread implements Serializable {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter out;
	private Person person;
	private ServerBowAndArrow arrow;

	private ObjectInputStream input;
	private ObjectOutputStream output;

	public ClientThread(Socket socket, Person person, ServerBowAndArrow arrow) throws IOException {
		this.socket = socket;
		this.person = person;
		this.arrow = arrow;
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	/*
	 * public PrintWriter getOut() { return out; }
	 */

	public ObjectOutputStream getOutput() {
		return output;
	}

	@Override
	public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();
			// reader = new BufferedReader(new InputStreamReader(in));
			input = new ObjectInputStream(in);
			while (true){
			Messages msg = (Messages) input.readObject();
			msg.perform();
			System.out.println("i performed!");
			}

			/*out = new PrintWriter(socket.getOutputStream(), true);
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {

				person.setX(Integer.valueOf(inputLine));

				person.setY(Integer.valueOf(reader.readLine()));

				arrow.setX(Integer.valueOf(reader.readLine()));
				arrow.setY(Integer.valueOf(reader.readLine()));
			}*/


		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
