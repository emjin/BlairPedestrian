public class Room extends Node {

	public Hallway[] routes = new Hallway[2];

	public Room(double c, double fR, double pp, double x0, double y0) {
		//capacity = c;
		//flowRate = fR;
		peopleHere = pp;
		x = x0;
		y = y0;
	}
}
