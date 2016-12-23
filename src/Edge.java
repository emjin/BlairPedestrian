
public abstract class Edge {
	public static final double MIN_FLOW_RATE = 10; //seconds it takes for someone to go from one end to another
	
	public double[] capacity = new double[2];
	public double[] numPeople = new double[2];
	
	public Person[] people;
	
	public Edge() {}
	
	public abstract void flow();
	
}
