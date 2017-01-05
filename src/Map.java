import java.util.ArrayList;
import java.util.List;

public class Map {
    List<Room> rooms = new ArrayList<Room>();
    List<Intersection> intersections = new ArrayList<Intersection>();
    List<Edge> edges = new ArrayList<Edge>();


    public Map(){}

    //You'll probably have to do it manually.

    public Path genPath(Node start, Node end){
        //Run Djikstra's
        throw new RuntimeException("Return something in genPath");
    }

    public double getDistance(Path path){
        //sum the edges along the path. Return a distance.
    }


}
