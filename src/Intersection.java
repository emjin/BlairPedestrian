
public class Intersection extends Node {
	public Intersection(double c, double fR, double pp, Edge[] rout, Person[] peeps) {
		capacity = c;
		flowRate = fR;
		peopleHere = pp;
		routes = rout;
		people = peeps;
	}

	public void flow() {
		for (int i = 0; i < people.length; i++) {
			 Person person = people[i];
			 Edge nextEdge;
		}
	}
}
