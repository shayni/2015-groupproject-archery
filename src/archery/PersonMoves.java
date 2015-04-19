package archery;

public class PersonMoves implements Messages {

	private Person person;
	private ServerBowAndArrow arrow;

	public PersonMoves(Person person, ServerBowAndArrow arrow) {
		this.person = person;
		this.arrow = arrow;
	}

	@Override
	public void perform() {
		person.setX(person.getX());
		person.setY(person.getY());
		arrow.setX(arrow.getX());
		arrow.setX(arrow.getY());
	}

}
