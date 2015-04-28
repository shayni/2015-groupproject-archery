package archery;

import java.io.Serializable;

public class ServerArrowReleased implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;

	public ServerArrowReleased(Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		int x1 = arrow.getX1();
		int x2 = arrow.getX2();
		int y1 = arrow.getY1();
		int y2 = arrow.getY2();
		world.getSerArrow().setX1(x1);
		world.getSerArrow().setX2(x2);
		world.getSerArrow().setY1(y1);
		world.getSerArrow().setY2(y2);
	}
}
