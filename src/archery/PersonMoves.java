package archery;

import java.io.Serializable;

public class PersonMoves implements Messages, Serializable{

	private Person person;
	private ServerBowAndArrow arrow;

	public PersonMoves(Person person, ServerBowAndArrow arrow) {
		this.person = person;
		this.arrow = arrow;
	}

	@Override
	public void perform() {
		
		person.setY(person.getY());
		arrow.setY(arrow.getY());
		person.setX(person.getX());
		arrow.setX(arrow.getX());
		
	}

}
