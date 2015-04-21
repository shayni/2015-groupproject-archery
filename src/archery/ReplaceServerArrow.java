package archery;

import java.io.Serializable;

public class ReplaceServerArrow implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;

	public ReplaceServerArrow(Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		Arrow arrowser = world.getSerArrow();
		arrowser.setX1(arrow.getX1());
		arrowser.setX2(arrow.getX2());
		arrowser.setY1(arrow.getY1());
		arrowser.setY2(arrow.getY2());
	}

}
