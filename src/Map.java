//import java.util.ArrayList;
//import java.util.List;

//genRooms is very long

import com.sun.javafx.sg.prism.NodePath;

import java.util.ArrayList;
import java.util.List;

public class Map {
	public static final int NUM_ROOMS = 26;
	public static final int NUM_INTERSECTIONS = 4;
	public static final int NUM_NODES = 30;
	public static final int NUM_EDGES = 58;

	public static Room[] rooms = new Room[NUM_ROOMS];
	public static Intersection[] intersections = new Intersection[NUM_INTERSECTIONS];
	public static Hallway[] edges = new Hallway[NUM_EDGES];

	private static int curEdge = 0;
	private static int start = 0;
	private static int end = 0;


	public Map(){
		genRooms();
		//System.out.println("DOOOOOONE");
		//System.out.println(edges[0]);
	}

	public Path genPath(Node start, Node end){
		List<Node> nodes = new ArrayList<Node>();
		for(int i = 0; i < NUM_INTERSECTIONS; i++){
			nodes.add(intersections[i]);
		}
		for(int i = 0; i < NUM_ROOMS; i++){
			nodes.add(rooms[i]);
		}
		double[] distances = new double[NUM_NODES];
		int[] parents = new int[NUM_NODES];
		boolean[] visited = new boolean[NUM_NODES];

		int endNode = -1;
		int startNode = -1;

		for(int i = 0; i < NUM_NODES; i++){
			visited[i] = false;
			if(nodes.get(i).equals(start)){
				distances[i] = 0;
				startNode = i;
			}
			else{
				distances[i] = Integer.MAX_VALUE;
			}
			if(nodes.get(i).equals(end)){
				endNode = i;
			}
		}
		int currentPos = startNode;
		Node currentNode = start;
		while(!visited[endNode]){
			visited[currentPos] = true;
			List<Hallway> routes = new ArrayList<Hallway>();

			/*for(int i = 0; i < NUM_INTERSECTIONS; i++){
				System.out.println(intersections[i] + "  " + i);
			}*/

			for(int i = 0; i < NUM_EDGES; i++){

				if(edges[i].start.equals(nodes.get(currentPos))){
					routes.add(edges[i]);
				}
			}

			for(Hallway adj: routes){
				int pos = -1;
				for(int i = 0; i < NUM_NODES; i++){
					if(adj.end.equals(nodes.get(i))){
						pos = i;
						break;
					}
				}

				if(distances[pos] > distances[currentPos] + adj.length){
					distances[pos] = distances[currentPos] + adj.length;
					parents[pos] = currentPos;
				}

			}
			double curMin = Integer.MAX_VALUE;
			for (int i = 0; i < NUM_NODES; i++){
				if(!visited[i] && distances[i] < curMin){
					curMin = distances[i];
					currentPos = i;
				}
			}
		}

		List<Node> nodePath = new ArrayList<Node>();
		List<Hallway> hallwayPath = new ArrayList<Hallway>();

		currentPos = endNode;

		while(currentPos != startNode){
			nodePath.add(nodePath.size(), nodes.get(parents[currentPos]));
			for(Hallway hallway: edges){
				if(hallway.start.equals(nodes.get(parents[currentPos])) && hallway.end.equals(nodes.get(currentPos))){
					hallwayPath.add(hallwayPath.size(), hallway);
				}
			}
			currentPos = parents[currentPos];
		}



		Path path = new Path(start, end);
		while(nodePath.size() > 0){
			Node temp = nodePath.remove(0);
			//System.out.println(temp + " temp");
			path.addNode(temp);
		}
		while(hallwayPath.size() > 0){
			Hallway temp = hallwayPath.remove(0);
			//System.out.println(temp + " temp");
			path.addEdge(temp);
		}

		return path;
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

		intersections[curInter] = new Intersection(1, 0, x1, y1); curInter += 1;
		genHall(y0, y1, x1, interval, 1);
		if(rooms[end-1] == null){
			System.out.println(149);
		}
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[end-1], intersections[curInter-1], x1, y1, 1); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-1], rooms[end-1], x1, y1, 1); curEdge += 1;
		x0 = x1; y0 = y1;

		//Top horizontal hallway 1
		x1 = 325; y1 = 240; interval = 15;

		if(rooms[start-1] == null){
			System.out.println(159);
		}
		if(rooms[end-1] == null){
			System.out.println(162);
		}
		intersections[curInter] = new Intersection(1, 0, x1, y1); curInter += 1;
		genHall(x0, x1, y1, interval, 0);
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-2], rooms[start-1], x0, y0, 1); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[start-1], intersections[curInter-2], x0, y0, 1); curEdge += 1;
		intersections[curInter-2].routes[0] = edges[curEdge-2];
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[end-1], intersections[curInter-1], x1, y1, 0); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-1], rooms[end-1], x1, y1, 0); curEdge += 1;
		intersections[curInter-1].routes[1] = edges[curEdge-1];
		x0 = x1; y0 = y1;

		//Top vertical hallway 2 part 1
		x1 = 325; y1 = 250; interval = 10;

		if(rooms[start-1] == null){
			System.out.println(178);
		}
		if(rooms[end-1] == null){
			System.out.println(181);
		}
		intersections[curInter] = new Intersection(1, 0, x1, y1); curInter += 1;
		genHall(y0, y1, x1, interval, 1);
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-2], rooms[start-1], x0, y0, 0); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[start-1], intersections[curInter-2], x0, y0, 0); curEdge += 1;
		intersections[curInter-2].routes[0] = edges[curEdge-2];
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[end-1], intersections[curInter-1], x1, y1, 1); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-1], rooms[end-1], x1, y1, 1); curEdge += 1;
		intersections[curInter-1].routes[1] = edges[curEdge-1];
		x0 = x1; y0 = y1;

		//Top vertical hallway 2 part 2
		x1 = 325; y1 = 260; interval = 10;

		if(rooms[start-1] == null){
			System.out.println(197);
		}
		//intersections[curInter] = new Intersection(1, 2, 0, x1, y1); curInter += 1;
		genHall(y0, y1, x1, interval, 1);
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-2], rooms[start-1], x0, y0, 1); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[start-1], intersections[curInter-2], x0, y0, 1); curEdge += 1;
		intersections[curInter-1].routes[0] = edges[curEdge-2];
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-2], rooms[start-1], x0, y0, 1); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[start-1], intersections[curInter-2], x0, y0, 1); curEdge += 1;
		intersections[curInter-1].routes[1] = edges[curEdge-1];
		x0 = x1; y0 = y1;

		if(rooms[start-1] == null){
			System.out.println(210);
		}
		//BACKTRACK: Top vertical hallway 2 part 0
		x0 = 325; y0 = 180;
		x1 = 325; y1 = 240; interval = 10;
		genHall(y0, y1, x1, interval, 1);
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-2], rooms[start-1], x0, y0, 1); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[start-1], intersections[curInter-2], x0, y0, 1); curEdge += 1;
		intersections[curInter-2].routes[2] = edges[curEdge-2];
		x0 = x1; y0 = y1;

		//Top horizontal hallway 2
		x0 = 325; y0 = 250;
		x1 = 425; y1 = 250; interval = 10;

		intersections[curInter] = new Intersection(1, 2, x1, y1); curInter += 1;
		genHall(x0, x1, y1, interval, 0);
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, intersections[curInter-2], rooms[start-1], x0, y0, 0); curEdge += 1;
		edges[curEdge] = new Hallway(0, edge_cap, edge_pop, rooms[start-1], intersections[curInter-2], x0, y0, 0); curEdge += 1;
		intersections[curInter-2].routes[2] = edges[curEdge-2];
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
				//
				edges[curEdge] = new Hallway(interval, edge_cap, edge_pop, rooms[i-1], rooms[i], aStart+(i-start)*interval, b, vertical);
				curEdge += 1;
				edges[curEdge] = new Hallway(interval, edge_pop, edge_init, rooms[i], rooms[i-1], aStart+(i-start)*interval, b, vertical);
				rooms[i].routes[0] = edges[curEdge];
				rooms[i-1].routes[1] = edges[curEdge-1];
				curEdge += 1;
			}
		}else{
			rooms[start] = new Room(room_size, room_flow, init_pop, b, aStart);
			for(int i = start+1; i < end; i++) {
				System.out.println("i: " + i + " start: " + start + " end: " + end + " aStart: " + aStart + " aEnd: " + aEnd + " interval: " + interval + " vertical: " + vertical);
				rooms[i] = new Room(room_size, room_flow, init_pop, b, aStart+(i-start)*interval);
				edges[curEdge] = new Hallway(interval, edge_pop, edge_init, rooms[i-1], rooms[i], b, aStart+(i-start)*interval, vertical);
				curEdge += 1;
				edges[curEdge] = new Hallway(interval, edge_pop, edge_init, rooms[i], rooms[i-1], b, aStart+(i-start)*interval, vertical);
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
