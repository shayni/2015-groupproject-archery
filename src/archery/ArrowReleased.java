package archery;

import java.io.Serializable;

public class ArrowReleased implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;

	public ArrowReleased(Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		int x1 = arrow.getX1();
		int x2 = arrow.getX2();
		world.getSerArrow().setX1(x1);
		world.getSerArrow().setX2(x2);
	}
}
