
public class Intersection extends Node {
	routes = new Edge[3];

	public Intersection(double c, double pp, Edge[] hallways, double x0, double y0) {
		capacity = c;
		routes = hallways;
		peopleHere = pp;
		x = x0;
		y = y0;
	}

	/*
	public void flow() {
		for (int i = 0; i < people.length; i++) {
			 Person person = people[i];
			 //Edge edge = person.path.getNextEdge();
		}
	}*/
	
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
