import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.commons.math3.distribution.NormalDistribution;

public class PedestrianTraffic {
	public static final double STUDENT_MU = 1.32*0.328; // feet/s
	public static final double STUDENT_SIG = 0.17*0.328;
	public static final int NUM_STUDENTS = 3000;
	public static final int TIME = 3600;
	public static int[] roomCDF = {}; //This will be the CDF of the rooms. We'll randomly sample from bill.
	public static Map map;
	
	static Room[] rooms = Map.rooms;
	static Intersection[] intersections = Map.intersections;
	static Hallway[] edges = Map.edges;
	static Person[] people = new Person[NUM_STUDENTS];
	
	
	public static void main(String[] args) {
		map = new Map();
		System.out.println(map);

		//------------------Preparing program------------------

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

		for(int time = 0; time < TIME; time++) {
			for(int i = 0; i < edges.length; i++){
				for(int j = 0; j < NUM_STUDENTS; j++){
					if(people[j].getHallway().equals(edges[i]){
						edges[i].numPeople += 1;
					}
				}
			}

			for (int i = 0; i < NUM_STUDENTS; i++) {
				double pressure = 0;
				double resistance = 0;
				double iConstant = 1;
				Hallway[] intersectionH = new Hallway[3];
				int[] hallwaysP = new int[3];
				if(people[i].getIntersection != null){
					intersectionH = people[i].getIntersection().hallways;
					hallwaysP = {0,0,0};
				}
				for (int j = 0; j < NUM_STUDENTS; j++) {
					if(people[i].getHallway().equals(people[j].getHallway())){
						double diff = people[i].getDistance() - people[j].getDistance();
						if(diff > 0 && diff < 5){
							resistance += (5-diff);
						}
						if(diff < 0 && diff > -5){
							pressure += (5+diff);
						}
					}

					if(people[i].getIntersection != null) {
						for (int k = 0; k < 3; k++) {
							if (people[j].getHallway().equals(hallways[k])) {
								if (people[j].getDistance() < 10) {
									hallwaysP[k]++;
								}
							}
						}
					}
				}

				if(people[i].getIntersection() != null){
					iConstant = people[i].getIntersection().calcConstant(hallwaysP);
				}

				people[i].run(resistance,pressure,iConstant);
			}
		}
		
		Surface surface = new Surface(people, edges);
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
