import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.math3.distribution.NormalDistribution;

public class PedestrianTraffic {
	public static final double STUDENT_MU = 1.2; // feet/s
	public static final double STUDENT_SIG = 4.0;
	public static final int NUM_STUDENTS = 3000;
	public static int[] roomCDF = {}; //This will be the CDF of the rooms. We'll randomly sample from bill.
	public static Map map = new Map();

	public static void main(String[] args) {
		//------------------Preparing program------------------
		Room[] rooms = Map.rooms;
		Intersection[] intersections = Map.intersections;
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
		
		
		//--------------------Make Frame stuff---------------------------
		JFrame frame = new JFrame("BlairPedestrian");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 600, 900);
		BufferedImage floorPlan;
	
		try {
			floorPlan = ImageIO.read(new File("FloorPlan.png"));
			JLabel picLabel = new JLabel(new ImageIcon(floorPlan.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH)));
			picLabel.setBounds(0, 0, 200, 300);
			frame.add(picLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		runModel();
	}
	
	public static void runModel() {
		
	}

	public static int genClass(){
		double num = Math.random();

		for(int i = 0; i < roomCDF.length; i++){
			if(num < roomCDF[i]){
				return i;
			}
		}
		return (int)(Math.random()*2);
		//throw new RuntimeException("Like, this should not happen.");
	}

}
