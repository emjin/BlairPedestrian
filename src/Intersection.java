
public class Intersection extends Node {
	public Hallway[] routes = new Hallway[3];

	public Intersection(double c, double pp, Hallway[] hallways, double x0, double y0) {
		capacity = c;
		routes = hallways;
		peopleHere = pp;
		x = x0;
		y = y0;
	}

	public double calcConstant(int[] people){
		int min = 100000;
		for(int i = 0; i < people.length; i++){
			if (people[i] < min){
				min = people[i];
			}
		}

		double ret = (capacity - min)/capacity;
		if(ret < 0.5){
			return 0.5;
		}
		return ret;
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
