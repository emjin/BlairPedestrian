public class Person {
	public Path path;
	public double distance; //distance along the path
	public double speed; //amount to change location by each run unit
	public static final double AVG = 5;
	public static final double MIN = 1;

	Map map = new Map();

	public Person(Node start, Node end, double speed){

		path = map.genPath(start, end);
		distance = getDistance();
		this.speed = speed;

	}

	public void run(double r, double p){ //Substracts you distance from the path.
		double actSpeed = speed + pos(AVG-speed) * r / (r + 10) - pos((speed-MIN) * p / (p + 10));
	}

	public double pos(double num){ //returns num if pos. 0 if neg.
		if(num > 0){
			return num;
		}
		return 0;
	}


	public double getAbsoluteDistance(){ //Don't see a reason to ever use this. Maybe to get some nice data at the end
		return distance;
	}


	public double getDistance(){ //This returns how far you are on the edge. Not the path.
		return path.getDistance(distance);
	}

	public Hallway getHallway(){
		return path.getHallway(distance);
	}



}
