
public abstract class Node {
	public double capacity;
	public double peopleHere;
	public Hallway[] routes;
	public double x;
	public double y;

	public Node() {};

	public String toString() {
		return x + ", " + y;
	}
}
