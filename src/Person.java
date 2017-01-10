public class Person {
	public Path path;
	public double distance; //distance along the path
	public double location; //location on an edge
	public double speed; //amount to change location by each run unit

	Map map = new Map();

	public Person(Node start, Node end, double speed){

		//path = map.genPath(start, end);
		distance = map.getDistance(path);
		this.speed = speed;

	}
}
