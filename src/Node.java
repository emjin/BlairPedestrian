
public abstract class Node {
	public double capacity;
	public double flowRate;
	public double peopleHere;
	public Edge[] routes;
	public Person[] people;
	public double x;
	public double y;
	
	public Node() {};
	
	public String toString() {
		return x + ", " + y;
	}
}
