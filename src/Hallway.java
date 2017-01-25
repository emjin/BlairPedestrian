
public class Hallway extends Edge {

	//Left = 0, Right = 1

	public double length;
	public double capacity;
	public double numPeople = new double;
	//public Node[][] sides = new Node[2][2];
	
	//public Person[] people;
	
	public Hallway(double len, double cap, double numP, double x0, double y0, double v) {
		length = len;
		
		capacity = cap;
		/*
		sides[0][0] = l1;
		sides[0][1] = l2;
		sides[1][0] = r1;
		sides[1][1] = r2;
		*/
		
		x = x0;
		y = y0;
		vertical = v;
	}

	/*
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
		
		if(person.distance >= length) {
			if (person.path.end.equals(sides[side][1])){
				numPeople[side] -= 1;
				//Add person to the intersection
				//Remove person from hallways
				person.location = 0;
			}
		}else{
			person.location += speed;
		}
	}
	*/

}
