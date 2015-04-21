package archery;

import java.io.Serializable;

public class ClientArrowReleases implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;

	public ClientArrowReleases(Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		int x1 = arrow.getX1();
		int x2 = arrow.getX2();
		world.getCliArrow().setX1(x1);
		world.getCliArrow().setX2(x2);
		System.out.println("perform:" + arrow.getX1() + " " + arrow.getX2());
	}
}
