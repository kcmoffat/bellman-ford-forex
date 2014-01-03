public class Edge {
	private Vertex myStart;
	private Vertex myEnd;
	private double myWeight;
	
	public Edge (Vertex s, Vertex t, double weight) {
		myStart = s;
		myEnd = t;
		myWeight = weight;
	}
	
	public double getWeight () {
		return myWeight;
	}
	
	public Vertex getStart () {
		return myStart;
	}
	
	public Vertex getEnd () {
		return myEnd;
	}
	
	public void updateWeight (double w) {
		myWeight = w;
	}
	
	public boolean equals (Object obj) {
		return this.getStart().equals(((Edge)obj).getStart())
				&& this.getEnd().equals(((Edge)obj).getEnd());
	}
	
	public String toString () {
		return myStart.toString() + "->" + myEnd.toString() + ", " + myWeight;
	}
}