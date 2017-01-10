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
	public static final double STUDENT_MU = 1.2; // feet/s
	public static final double STUDENT_SIG = 4.0;
	public static final int NUM_STUDENTS = 3000;
	public static int[] roomCDF = {}; //This will be the CDF of the rooms. We'll randomly sample from bill.
	public static Map map = new Map();
	
	static Room[] rooms = Map.rooms;
	static Intersection[] intersections = Map.intersections;
	static Edge[] edges = Map.edges;
	static Person[] people = new Person[NUM_STUDENTS];
	
	
	public static void main(String[] args) {
		
		
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
