import org.apache.commons.math3.distribution.NormalDistribution;

public class PedestrianTraffic {
	public static final double STUDENT_MU = 1.2; // feet/s
	public static final double STUDENT_SIG = 4.0;
	public static final int NUM_STUDENTS = 3000;
	public static final int NUM_ROOMS = 3000;
	public static final int NUM_INTERSECTIONS = 3000;
	public static int[] roomCDF = {}; //This will be the CDF of the rooms. We'll randomly sample from bill.

	public static void main(String[] args) {

		Room[] rooms = new Room[NUM_ROOMS];
		Intersection[] intersections = new Intersection[NUM_INTERSECTIONS];
		Person[] people = new Person[NUM_STUDENTS];

		NormalDistribution studentSpeed = new NormalDistribution(STUDENT_MU, STUDENT_SIG);


		for(int i = 0; i < NUM_STUDENTS; i++){
			double speed = studentSpeed.sample();
			Room start = rooms[genClass()];
			Room stop = rooms[genClass()];
			while (stop.equals(start)){
				stop = rooms[genClass()];
			}
			people[i] = new Person(start, stop, speed);
		}
	}

	public static int genClass(){
		double num = Math.random();

		for(int i = 0; i < roomCDF.length; i++){
			if(num < roomCDF[i]){
				return i;
			}
		}
		throw new RuntimeException("Like, this should not happen.");
	}

}
