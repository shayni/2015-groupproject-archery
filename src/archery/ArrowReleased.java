package archery;

import java.io.Serializable;

public class ArrowReleased implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;
	//private World world;

	public ArrowReleased(Arrow arrow /*, World world*/) {
		this.arrow = arrow;
		//this.world = world;
	}

	@Override
	public void perform() {
		System.out.println("hello");
		int x1 = arrow.getX1();
		int x2 = arrow.getX2();
		arrow.move();
		System.out.println ("perform:" + arrow.getX1() + " " + arrow.getX2());
		//world.getSerArrow().setX1(x1);
		//.getSerArrow().setX2(x2);
		arrow.setX1(x1 );
		arrow.setX2(x2);
	}
}
