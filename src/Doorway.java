
public class Doorway extends Edge {
	public double capacity;
	public double numPeople;
	public Person[] people;
	
	public Room room;
	public Intersection exit;
	
	
	public Doorway(double np, double cap, Person[] peeps, Room rm) {
		people = peeps;
		numPeople = np;
		capacity = cap;
		room = rm;
	}
	
	public void flow() {
		for (int i = 0; i < people.length; i++) {
			Person person = people[i];
			//remove person from room
			//add person to edge
		}
	}
}
