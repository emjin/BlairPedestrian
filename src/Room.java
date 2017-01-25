public class Room extends Node {

	public Hallway[] routes = new Hallway[2];

	public Room(double c, double fR, double pp, Hallway[] hallways, double x0, double y0) {
		//capacity = c;
		//flowRate = fR;
		peopleHere = pp;
		routes = hallways;
		x = x0;
		y = y0;
	}
}
