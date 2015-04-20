package archery;

import java.io.Serializable;

public class PersonMoves implements Messages, Serializable {

	private Person person;
	private Arrow arrow;

	public PersonMoves(Person person, Arrow arrow) {
		this.person = person;
		this.arrow = arrow;
	}

	@Override
	public void perform(World world) {
		int x = person.getX();
		int y = person.getY();
		world.getServerPerson().setX(x);
		world.getServerPerson().setY(y);

	}

}
