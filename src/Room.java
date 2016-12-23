
public class Room extends Node {
	public Room(double c, double fR, double pp, Edge[] rout, Person[] peeps) {
		capacity = c;
		flowRate = fR;
		peopleHere = pp;
		routes = rout;
		people = peeps;
	}
}
