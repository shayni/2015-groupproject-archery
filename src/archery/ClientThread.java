package archery;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter out;
	private Person person;
	private ServerBowAndArrow arrow;
	private ObjectInputStream input;

	public ClientThread(Socket socket, Person person, ServerBowAndArrow arrow) {
		this.socket = socket;
		this.person = person;
		this.arrow = arrow;
	}

	public PrintWriter getOut() {
		return out;
	}

	@Override
	public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			input = new ObjectInputStream(new BufferedInputStream(in));
			Messages msg = (Messages) input.readObject();
			msg.perform();
			out = new PrintWriter(socket.getOutputStream(), true);
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {

				person.setX(Integer.valueOf(inputLine));

				person.setY(Integer.valueOf(reader.readLine()));

				arrow.setX(Integer.valueOf(reader.readLine()));
				arrow.setY(Integer.valueOf(reader.readLine()));
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
