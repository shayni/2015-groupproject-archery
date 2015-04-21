package archery;

import java.io.IOException;

public class ClientMain {
	
	public static void main(String[] args) {
		ClientArcheryFrame frame;
		try {
			frame = new ClientArcheryFrame();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
