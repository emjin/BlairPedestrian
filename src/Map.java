//import java.util.ArrayList;
//import java.util.List;

//genRooms is very long

public class Map {
	public static final int NUM_ROOMS = 60;
	public static final int NUM_INTERSECTIONS = 3000;
	
	public static Room[] rooms = new Room[NUM_ROOMS];
    public static Intersection[] intersections = new Intersection[NUM_INTERSECTIONS];
    public static Edge[] edges = new Edge[NUM_ROOMS + NUM_INTERSECTIONS];
    
    private static int curEdge = 0; 
    private static int start = 0;
    private static int end = 0;


    public Map(){
    	genRooms();
    	//System.out.println("DOOOOOONE");
    	//System.out.println(edges[0]);
    }

    public Path genPath(Node start, Node end){
    	Path path = new Path(start, end); //delete
    	//System.out.println("Map says hiiiiiii");
		//System.out.println(start + " " + end);
    	return path; //delete
        //Run Djikstra's
        //throw new RuntimeException("Return something in genPath");
    }

    public double getDistance(Path path){
        //sum the edges along the path. Return a distance.
    	return 0;
    }

    public void genRooms(){
    	int x0; int y0; int x1; int y1; int interval; 
    	int curInter = 0;
    	
    	int edge_cap = 5; //intersection edges
    	int edge_pop = 5; 
    	
    	x0 = 235; y0 = 220; //Start of hallway
    	
    	//Top vertical hallway 1
    	x1 = 235; y1 = 240; interval = 10;
    	
       	intersections[curInter] = new Intersection(1, 2, 0, x1, y1); curInter += 1;
        genHall(y0, y1, x1, interval, 1);
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, rooms[end], intersections[curInter-1], intersections[curInter-1], rooms[end], x1, y1, 1); curEdge += 1;
    	x0 = x1; y0 = y1;

    	//Top horizontal hallway 1
    	x1 = 325; y1 = 240; interval = 15;
    	
    	intersections[curInter] = new Intersection(1, 2, 0, x1, y1); curInter += 1;
    	genHall(x0, x1, y1, interval, 0);
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, intersections[curInter-2], rooms[start], rooms[start], intersections[curInter-2], x0, y0, 1); curEdge += 1;
    	intersections[curInter-2].routes[0] = edges[curEdge-1];
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, rooms[end], intersections[curInter-1], intersections[curInter-1], rooms[end], x1, y1, 0); curEdge += 1;
    	intersections[curInter-2].routes[1] = edges[curEdge-1];
    	x0 = x1; y0 = y1;
    	
    	//Top vertical hallway 2 part 1
    	x1 = 325; y1 = 250; interval = 10;
    	
    	intersections[curInter] = new Intersection(1, 2, 0, x1, y1); curInter += 1;
    	genHall(y0, y1, x1, interval, 1);
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, intersections[curInter-2], rooms[start], rooms[start], intersections[curInter-2], x0, y0, 0); curEdge += 1;
    	intersections[curInter-2].routes[0] = edges[curEdge-1];
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, rooms[end], intersections[curInter-1], intersections[curInter-1], rooms[end], x1, y1, 1); curEdge += 1;
    	intersections[curInter-2].routes[1] = edges[curEdge-1];
    	x0 = x1; y0 = y1;
    	
    	//Top vertical hallway 2 part 2
    	x1 = 325; y1 = 260; interval = 10;
    	
    	//intersections[curInter] = new Intersection(1, 2, 0, x1, y1); curInter += 1;
    	genHall(y0, y1, x1, interval, 1);
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, intersections[curInter-2], rooms[start], rooms[start], intersections[curInter-2], x0, y0, 1); curEdge += 1;
    	intersections[curInter-1].routes[0] = edges[curEdge-1];
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, rooms[end], intersections[curInter-1], intersections[curInter-1], rooms[end], x1, y1, 1); curEdge += 1;
    	intersections[curInter-1].routes[1] = edges[curEdge-1];
    	x0 = x1; y0 = y1;
    	
    	//BACKTRACK: Top vertical hallway 2 part 0
    	x0 = 325; y0 = 180;
    	x1 = 325; y1 = 240; interval = 10;
    	genHall(y0, y1, x1, interval, 1);
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, intersections[curInter-2], rooms[start], rooms[start], intersections[curInter-2], x0, y0, 1); curEdge += 1;
    	intersections[curInter-2].routes[2] = edges[curEdge-1];
    	x0 = x1; y0 = y1;

    	//Top horizontal hallway 2
    	x0 = 325; y0 = 250;
    	x1 = 425; y1 = 250; interval = 10;
    	
    	intersections[curInter] = new Intersection(1, 2, 0, x1, y1); curInter += 1;
    	genHall(x0, x1, y1, interval, 0);
    	edges[curEdge] = new Hallway(edge_cap, edge_pop, 0, intersections[curInter-2], rooms[end], rooms[end], intersections[curInter-2], x0, y0, 0); curEdge += 1;
    	intersections[curInter-2].routes[2] = edges[curEdge-1];
    	x0 = x1; y0 = y1;

    }
    
    public void genHall(int aStart, int aEnd, int b, int interval, int vertical) {
    	end = (int)((aEnd-aStart)/interval)+start;
    	
    	int room_size = 30;
    	int init_pop = 0;
    	int room_flow = 2;
    	
    	int edge_cap = 5;
    	int edge_pop = 5;
    	int edge_init = 0;
    	
    	if (vertical == 0) {
    		rooms[start] = new Room(room_size, room_flow, init_pop, aStart, b);
    		for(int i = start+1; i < end; i++) {
    			System.out.println("i: " + i + " start: " + start + " end: " + end + " aStart: " + aStart + " aEnd: " + aEnd + " interval: " + interval + " vertical: " + vertical);
    			rooms[i] = new Room(room_size, room_flow, init_pop, aStart+(i-start)*interval, b);
    			edges[curEdge] = new Hallway(edge_cap, edge_pop, edge_init, rooms[i-1], rooms[i], rooms[i], rooms[i-1], aStart+(i-start)*interval, b, vertical);
    			curEdge += 1;
    		}
    	}else{
       		rooms[start] = new Room(room_size, room_flow, init_pop, b, aStart);
    		for(int i = start+1; i < end; i++) {
    			System.out.println("i: " + i + " start: " + start + " end: " + end + " aStart: " + aStart + " aEnd: " + aEnd + " interval: " + interval + " vertical: " + vertical);
    			rooms[i] = new Room(room_size, room_flow, init_pop, b, aStart+(i-start)*interval);
    			edges[curEdge] = new Hallway(edge_cap, edge_pop, edge_init, rooms[i-1], rooms[i], rooms[i], rooms[i-1], b, aStart+(i-start)*interval, vertical);
    			curEdge += 1;
    		}
    	}
    	start += end - start;
    }

    public String toString() {
		String s = "Rooms\n";
		for(int i = 0; i < rooms.length; i++) {
			Room room = rooms[i];
			if(room == null) break;
			s += i + " " + room + "\n";
		}
		
		s += "Edges\n";
		for(int i = 0; i < edges.length; i++) {
			Edge edge = edges[i];
			if(edge == null) break;
			s += i + " " + edge + "\n";
		}
		
		s += "Intersections\n";
		for(int i = 0; i < intersections.length; i++) {
			Intersection inter = intersections[i];
			if(inter == null) break;
			s += i + " " + inter + "\n";
		}
		
		return s;
    }
    
}
