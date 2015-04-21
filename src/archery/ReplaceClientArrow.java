package archery;

import java.io.Serializable;

public class ReplaceClientArrow implements Messages, Serializable {

	private static final long serialVersionUID = 1L;
	private Arrow arrow;

	public ReplaceClientArrow(Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		Arrow arrowcli = world.getCliArrow();
		arrowcli.setX1(arrow.getX1());
		arrowcli.setX2(arrow.getX2());
		arrowcli.setY1(arrow.getY1());
		arrowcli.setY2(arrow.getY2());
	}

}
