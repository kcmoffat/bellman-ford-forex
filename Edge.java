public class Edge {
	private Node myStart;
	private Node myEnd;
	private double myWeight;
	
	public Edge (Node s, Node t, double weight) {
		myStart = s;
		myEnd = t;
		myWeight = weight;
	}
	
	public double getWeight () {
		return myWeight;
	}
	
	public Node getStart () {
		return myStart;
	}
	
	public Node getEnd () {
		return myEnd;
	}
	
	public String toString () {
		return myStart.toString() + "->" + myEnd.toString() + ", " + myWeight;
	}
}