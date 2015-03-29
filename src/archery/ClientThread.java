package archery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter out;
	private Person person;
	private boolean xOrY;

	public ClientThread(Socket socket, Person person) {
		this.socket = socket;
		this.person = person;
		xOrY = true;
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
			out = new PrintWriter(socket.getOutputStream(), true);
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				if (xOrY) {
					person.setX(Integer.valueOf(inputLine));
					xOrY = false;
				} else {
					person.setY(Integer.valueOf(inputLine));
					xOrY = true;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
