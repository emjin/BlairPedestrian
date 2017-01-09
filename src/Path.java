import java.util.List;

/**
 * Created by erics on 1/5/2017.
 */
public class Path {
    public Node start;
    public Node end;
    public List<Node> nodes;
    public List<Edge> hallways;
    int nIncrement = -1;
    int hIncrement = -1;

    public Path(Node start, Node end){
        this.start = start;
        this.end = end;
        nodes.add(start);
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addEdge(Edge edge){
        hallways.add(edge);
    }

    public Node getNode(int index){
        return nodes.get(index);
    }

    public Edge getHallway(int index){
        return hallways.get(index);
    }

    public Node getNextNode(){
        nIncrement++;
        return nodes.get(nIncrement);
    }

    public Edge getNextEdge(){
        hIncrement++;
        return hallways.get(hIncrement);
    }

}
