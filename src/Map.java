import java.util.ArrayList;
import java.util.List;

public class Map {
	public static final int NUM_ROOMS = 60;
	public static final int NUM_INTERSECTIONS = 3000;
	
	public static Room[] rooms = new Room[NUM_ROOMS];
    public static Intersection[] intersections = new Intersection[NUM_INTERSECTIONS];
    public static Edge[] edges;


    public Map(){
    	double[][] coords = {{20, 20}, {30, 20}};
    	genRooms(coords);
    }

    public void genRooms(double[][] coords){
    	for (int i = 0; i < coords.length; i++) {
    		rooms[i] = new Room(30, 2, 0, coords[i][0], coords[i][1]);
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
