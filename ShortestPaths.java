import java.util.HashMap;
import java.util.Iterator;

public class ShortestPaths {
	private HashMap<Vertex, Double> myDists;
	private HashMap<Vertex, Vertex> myPrevs;
	
	public ShortestPaths () {
		myDists = new HashMap<Vertex, Double> ();
		myPrevs = new HashMap<Vertex, Vertex> ();
	}
	
	public void updateDist (Vertex n, double d) {
		myDists.put(n, d);
	}
	
	public void updatePrev (Vertex n, Vertex p) {
		myPrevs.put(n, p);
	}
	
	public double getDist (Vertex n) {
		return myDists.get(n);
	}
	
	public Vertex getPrev (Vertex n) {
		return myPrevs.get(n);
	}
	
	public String toString () {
		String result = "";
		Iterator<Vertex> nodes = myDists.keySet().iterator();
		Vertex next;
		while (nodes.hasNext()) {
			next = nodes.next();
			result += next + ": " + myDists.get(next) + "\n";
		}
		return result;
	}
	
}
