package archery;

import java.io.IOException;

public class ServerMain {
	
	public static void main(String[] args) {
		ServerArcheryFrame frame;
		try {
			frame = new ServerArcheryFrame();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
