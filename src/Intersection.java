
public class Intersection extends Node {
	public Edge[] routes = new Edge[4]; 
	
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
			 //Edge edge = person.path.getNextEdge();
		}
	}
	
	@Override
	public String toString() {
		String s = x + ", " + y;
		for(int i = 0; i < 4; i++){
			if(routes[i] != null){
				s += " Edge" + i + " = " + routes[i].toString();
			}
		}
		return s;
	}
}
