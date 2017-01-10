
public class Intersection extends Node {
	public Intersection(double c, double fR, double pp, double x0, double y0) {
		capacity = c;
		flowRate = fR;
		peopleHere = pp;
		x = x0;
		y = y0;
	}

	public void flow() {
		for (int i = 0; i < people.length; i++) {
			 Person person = people[i];
			 Edge edge = person.path.getNextEdge();
		}
	}
}
