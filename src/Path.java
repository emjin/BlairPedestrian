import java.util.ArrayList;
import java.util.List;

/**
 * Created by erics on 1/5/2017.
 */
public class Path {
    public Node start;
    public Node end;
    public List<Node> nodes = new ArrayList<Node>(); //may change type of list
    public List<Double> distances = new ArrayList<Double>(); //may change type of list
    public List<Hallway> hallways = new ArrayList<Hallway>(); //may change type of list
    int nIncrement = -1;
    int hIncrement = -1;

    public Path(Node start, Node end){
        //System.out.println("start " + start);
        this.start = start;
        this.end = end;
        nodes.add(start);
        distances.add(new Double(0));
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addEdge(Hallway edge){
        hallways.add(edge);
        //System.out.println(edge.length);
        distances.add(edge.length + distances.get(distances.size()-1));
        //System.out.println(distances.get(distances.size()-1));
    }

    public Node getNode(int index){
        return nodes.get(index);
    }

    public Intersection getIntersection(double distance){
        for(int i = 1; i < distances.size(); i++){
            if(distances.get(i) < distance + 0.01 && i != nodes.size()-1){
                return (Intersection)nodes.get(i); //Maybe i +- 1. C
            }
        }
        return null;
    }

    public double getRDistance(double distance){
        return getHallway(distance).length - getDistance(distance);
    }


    public Hallway getHallway(double distance){
        for(int i = hallways.size()-1; i >= 0; i--){
            if(distances.get(i) < distance + 0.01){
                return hallways.get(i);
            }
        }
        throw new RuntimeException("getHallway has a bug");
    }

    public double getDistance(double distance){
        for(int i = distances.size()-1; i >= 0; i--){
            //System.out.println(distance);
            if(distances.get(i) < distance + 0.01){
                return distance - distances.get(i) ;
            }
        }
        return -2;
    }


}
