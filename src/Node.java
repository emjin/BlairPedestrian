
public abstract class Node {
	public double capacity;
	public double peopleHere;
	//public Edge[] route
	public double x;
	public double y;

	public Node() {};
	
	public String toString() {
		return x + ", " + y;
	}
}
