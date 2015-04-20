package archery;

import java.io.Serializable;

public class ClientPersonMoves implements Messages, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Person person;
	private Arrow arrow;

	public ClientPersonMoves(Person person, Arrow arrow) {
		this.person = person;
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		int x = person.getX();
		int y = person.getY();
		world.getClientPerson().setX(x);
		world.getClientPerson().setY(y);

	}

}
