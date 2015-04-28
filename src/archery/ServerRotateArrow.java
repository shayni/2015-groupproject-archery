package archery;

public class ServerRotateArrow implements Messages{

	private double angle;
	
	public ServerRotateArrow(double angle){
		this.angle=angle;
	}
	
	@Override
	public void perform(World world) {

		world.getSerArrow().setAngle(angle);
		
	}

}
