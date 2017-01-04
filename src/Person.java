import java.util.Map;

public class Person {
	//public Node startNode;
	//public Edge atEdge;
	public double location; //location relative to the beginning of an edge
	public double speed; //amount to change location by each run unit
	
	public Node destNode;
	//public Intersection[] intersectionPrefs;
	public Map<Room, Edge> edgePrefs; //Maps preferred edges to get to the room
	public double numFriends;
}
