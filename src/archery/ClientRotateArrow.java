package archery;

public class ClientRotateArrow implements Messages{

	private double angle;
	public ClientRotateArrow(double angle){
		this.angle = angle;
	}
	@Override
	public void perform(World world) {

		world.getCliArrow().setAngle(angle);
	}

}
