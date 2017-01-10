import java.util.List;

/**
 * Created by erics on 1/5/2017.
 */
public class Path {
    public Node start;
    public Node end;
    public List<Node> nodes;
    public List<Double> distances;
    public List<Hallway> hallways;
    int nIncrement = -1;
    int hIncrement = -1;

    public Path(Node start, Node end){
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
        distances.add(edge.length + distances.get(distances.size() - 1));
    }

    public Node getNode(int index){
        return nodes.get(index);
    }

    public Edge getHallway(double distance){
        for(int i = 1; i < distances.size(); i++){
            if(distances.get(i) < distance){
                return hallways.get(i - 1);
            }
        }
        throw new RuntimeException("getHallway has a bug");
    }

    public double getDistance(double distance){
        for(int i = 1; i < distances.size(); i++){
            if(distances.get(i) < distance){
                return distances.get(i) - distance;
            }
        }
        throw new RuntimeException("getDistance has a bug");
    }


}
