package archery;

import java.io.Serializable;

public class ArrowReleased implements Messages, Serializable {

	private Arrow arrow;

	public ArrowReleased(Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public void perform() {
		int x1 = arrow.getX1();
		int x2 = arrow.getX2();
		arrow.setX1(x1 + 100);
		arrow.setX2(x2 + 100);
	}

}
