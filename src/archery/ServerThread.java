package archery;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter out;
	private Person person;
	private ClientBowAndArrow arrow;
	private ObjectOutputStream output;

	public ServerThread(Socket socket, Person person, ClientBowAndArrow arrow) {
		this.socket = socket;
		this.person = person;
		this.arrow = arrow;
	}

	@Override
	public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			out = new PrintWriter(socket.getOutputStream(), true);
			output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {

				person.setX(Integer.valueOf(inputLine));

				person.setY(Integer.valueOf(reader.readLine()));

				arrow.setX(Integer.valueOf(reader.readLine()));
				arrow.setY(Integer.valueOf(reader.readLine()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public PrintWriter getOut() {
		return out;
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

}
