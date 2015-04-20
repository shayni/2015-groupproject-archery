package archery;

import java.io.Serializable;

public class ArrowReleased implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;
	private int x1;
	private int x2;

	public ArrowReleased(Arrow arrow /* , World world */) {
		this.arrow = arrow;
		// this.world = world;
	}

	public ArrowReleased(int x1, int x2) {
		this.x1 = x1;
		this.x2 = x2;

	}

	@Override
	public void perform(World world) {
		System.out.println("entered perform");
		int x1 = arrow.getX1();
		int x2 = arrow.getX2();
		// arrow.move();
		// System.out.println ("perform:" + arrow.getX1() + " " +
		// arrow.getX2());
		world.getSerArrow().setX1(x1);
		world.getSerArrow().setX2(x2);
		System.out.println("perform:" + arrow.getX1() + " " + arrow.getX2());
		// arrow.setX1(x1 );
		// arrow.setX2(x2);
	}
}
