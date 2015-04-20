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
		
		int arrowx1 = arrow.getX1();
		int arrowy1 = arrow.getY1();
		int arrowx2 = arrow.getX2();
		int arrowy2 = arrow.getY2();
		Person per = world.getServerPerson();
		Arrow a = world.getSerArrow();

		per.setX(x);
		per.setY(y);
		
		a.setX1(arrowx1);
		a.setX2(arrowx2);
		a.setY1(arrowy1);
		a.setY2(arrowy2);
		
		
		

	}

}
