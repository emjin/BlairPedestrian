
public class Hallway extends Edge {
	public static final double MIN_FLOW_RATE = 10; //seconds it takes for someone to go from one end to another
	
	//Left = 0, Right = 1
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	public double length;
	public double[] capacity = new double[2];
	public double[] numPeople = new double[2];
	public Node[][] sides = new Node[2][2];
	
	public Person[] people;
	
	public Hallway(double len, double cap, double numP, Node l1, Node l2, Node r1, Node r2) {
		length = len;
		
		capacity[0] = cap;
		capacity[1] = cap;
		
		numPeople[0] = numP;
		numPeople[1] = numP;
		
		sides[0][0] = l1;
		sides[0][1] = l2;
		sides[1][0] = r1;
		sides[1][1] = r2;
	}
	
	public void flow() {
		for(int i = 0; i < people.length; i++) {
			Person person = people[i];
			hallwaySide(LEFT, person);
			hallwaySide(RIGHT, person);
		}
	}
	
	public void hallwaySide(int side, Person person) {
		double speed = person.speed;
		if (numPeople[side] > capacity[side]/2) {
			speed = speed * (1.1 - numPeople[side]/capacity[side]); //arbitrary number
			capacity[side] += 1;
			capacity[Math.abs(side-1)] -= 1;
		}
		
		if(person.location >= length) {
			if (person.destNode.equals(sides[side][1])){
				numPeople[side] -= 1;
				//Add person to the intersection
				//Remove person from hallways
				person.location = 0;
			}
		}else{
			person.location += speed;
		}
	}

}
