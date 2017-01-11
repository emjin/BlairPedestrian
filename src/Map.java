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
    	int room_size = 30;
    	int init_pop = 0;
    	int room_flow = 2;
    	
    	int edge_cap = 5;
    	int edge_pop = 5;
    	
    	//Top vertical hallway
    	rooms[0] = new Room(room_size, room_flow, init_pop, 235, 220);
    	rooms[1] = new Room(room_size, room_flow, init_pop, 235, 230);
    	rooms[2] = new Room(room_size, room_flow, init_pop, 235, 240);
    	edges[0] = new Hallway(edge_cap, edge_pop, 0, rooms[0], rooms[1], rooms[1], rooms[0], 235, 220, 1);
    	edges[1] = new Hallway(edge_cap, edge_pop, 0, rooms[1], rooms[2], rooms[2], rooms[1], 235, 230, 1);
    	

    	intersections[0] = new Intersection(1, 2, 0, 235, 240);
    	edges[2] = new Hallway(edge_cap, edge_pop, 0, rooms[2], intersections[0], intersections[0], rooms[2], 235, 240, 1);
    	
    	//Top horizontal hallway 1
    	rooms[3] = new Room(room_size, room_flow, init_pop, 245, 240);
    	rooms[4] = new Room(room_size, room_flow, init_pop, 260, 240);
    	rooms[5] = new Room(room_size, room_flow, init_pop, 275, 240);
    	rooms[6] = new Room(room_size, room_flow, init_pop, 290, 240);
    	edges[3] = new Hallway(edge_cap, edge_pop, 0, intersections[0], rooms[3], rooms[3], intersections[0], 245, 240, 0);
    	edges[4] = new Hallway(edge_cap, edge_pop, 0, rooms[3], rooms[4], rooms[4], rooms[3], 260, 240, 0);
    	edges[5] = new Hallway(edge_cap, edge_pop, 0, rooms[4], rooms[5], rooms[5], rooms[4], 275, 240, 0);
    	edges[6] = new Hallway(edge_cap, edge_pop, 0, rooms[5], rooms[6], rooms[6], rooms[5], 290, 240, 0);
    	intersections[0].routes[0] = edges[2];
    	intersections[0].routes[1] = edges[3];
    }

}
