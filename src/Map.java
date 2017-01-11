//import java.util.ArrayList;
//import java.util.List;

public class Map {
	public static final int NUM_ROOMS = 60;
	public static final int NUM_INTERSECTIONS = 3000;
	
	public static Room[] rooms = new Room[NUM_ROOMS];
    public static Intersection[] intersections = new Intersection[NUM_INTERSECTIONS];
    public static Edge[] edges = new Edge[NUM_ROOMS + NUM_INTERSECTIONS];


    public Map(){
    	genRooms();
    	//System.out.println(edges[0]);
    }

    public Path genPath(Node start, Node end){
        //Run Djikstra's
        throw new RuntimeException("Return something in genPath");
    }

    public double getDistance(Path path){
        //sum the edges along the path. Return a distance.
    	return 0;
    }

    public void genRooms(){
    	int x0; int y0; int x1; int y1; int interval; 
    	int start; int end;
    	
    	int edge_cap = 5; //intersection edges
    	int edge_pop = 5; 
    	
    	x0 = 235; y0 = 220; //Start of hallway
    	
    	//Top vertical hallway
    	x1 = 235; y1 = 240; start = 0; interval = 10;
    	end = ((y1 - y0)/interval) + start;
    	
       	intersections[0] = new Intersection(1, 2, 0, x1, y1);
        genHall(start, y0, y1, x1, interval, 1);
    	edges[2] = new Hallway(edge_cap, edge_pop, 0, rooms[end], intersections[0], intersections[0], rooms[end], x1, y1, 1);
    	x0 = x1; y0 = y1;

    	
    	//Top horizontal hallway 1
    	x1 = 305; y1 = 240; start = 3; interval = 15;
    	end = ((x1 - x0)/interval) + start;
    	
    	intersections[1] = new Intersection(1, 2, 0, x1, y1);
    	genHall(0, x0, x1, y1, interval, 0);
    	edges[3] = new Hallway(edge_cap, edge_pop, 0, intersections[0], rooms[start], rooms[start], intersections[0], x0, y0, 1);
    	intersections[0].routes[0] = edges[2];
    	intersections[0].routes[1] = edges[3];
    	edges[8] = new Hallway(edge_cap, edge_pop, 0, rooms[end], intersections[1], intersections[1], rooms[end], x1, y1, 1);
    	x0 = x1; y0 = y1;
    }
    
    public void genHall(int start, int aStart, int aEnd, int b, int interval, int vertical) {
    	int end = (int)((aEnd-aStart)/interval)+1;
    	
    	int room_size = 30;
    	int init_pop = 0;
    	int room_flow = 2;
    	
    	int edge_cap = 5;
    	int edge_pop = 5;
    	int edge_init = 0;
    	
    	if (vertical == 0) {
    		rooms[start] = new Room(room_size, room_flow, init_pop, aStart, b);
    		for(int i = start+1; i < end; i++) {
    			rooms[i] = new Room(room_size, room_flow, init_pop, aStart+i*interval, b);
    			edges[i-1] = new Hallway(edge_cap, edge_pop, edge_init, rooms[i-1], rooms[i], rooms[i], rooms[i-1], aStart+i*interval, b, vertical);
    		}
    	}else{
       		rooms[start] = new Room(room_size, room_flow, init_pop, b, aStart);
    		for(int i = start+1; i < end; i++) {
    			rooms[i] = new Room(room_size, room_flow, init_pop, b, aStart+i*interval);
    			edges[i-1] = new Hallway(edge_cap, edge_pop, edge_init, rooms[i-1], rooms[i], rooms[i], rooms[i-1], b, aStart+i*interval, vertical);
    		}
    	}
    }

}
