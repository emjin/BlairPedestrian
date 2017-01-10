//import java.util.ArrayList;
//import java.util.List;

public class Map {
	public static final int NUM_ROOMS = 60;
	public static final int NUM_INTERSECTIONS = 3000;
	
	public static Room[] rooms = new Room[NUM_ROOMS];
    public static Intersection[] intersections = new Intersection[NUM_INTERSECTIONS];
    public static Edge[] edges = new Edge[NUM_ROOMS + NUM_INTERSECTIONS];


    public Map(){
    	double[][] coords = {
    			{235, 220, 1}, {235, 230, 1}, {235, 240, 1},
    			{245, 240, 0}, {260, 240, 0}, {290, 240, 0}
    			};
    	genRooms(coords);
    	//System.out.println(edges[0]);
    }

    public void genRooms(double[][] coords){
		rooms[0] = new Room(30, 2, 0, coords[0][0], coords[0][1]);
    	for (int i = 1; i < coords.length; i++) {
    		rooms[i] = new Room(30, 2, 0, coords[i][0], coords[i][1]);
    		edges[i-1] = new Hallway(5, 5, 0, rooms[i-1], rooms[i], rooms[i], rooms[i-1], coords[i][0], coords[i][1], coords[i][2]);
    	}
    }

    public Path genPath(Node start, Node end){
        //Run Djikstra's
        throw new RuntimeException("Return something in genPath");
    }

    public double getDistance(Path path){
        //sum the edges along the path. Return a distance.
    	return 0;
    }


}
