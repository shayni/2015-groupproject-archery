package archery;

import java.io.Serializable;

public class PersonMoves implements Messages, Serializable {

	private Person person;
	private Arrow arrow;

	public PersonMoves(Person person, Arrow arrow) {
		this.person = person;
		this.arrow = arrow;
	}

	//@Override
	public void perform() {

		person.setY(person.getY());
		arrow.setY1(arrow.getY1());
		person.setX(person.getX());
		arrow.setX1(arrow.getX1());

	}

	@Override
	public void perform(World world) {
		// TODO Auto-generated method stub
		
	}

}
