import java.util.HashMap;
import java.util.Iterator;

public class ShortestPaths {
	private HashMap<Node, Double> myDists;
	private HashMap<Node, Node> myPrevs;
	
	public ShortestPaths () {
		myDists = new HashMap<Node, Double> ();
		myPrevs = new HashMap<Node, Node> ();
	}
	
	public void updateDist (Node n, double d) {
		myDists.put(n, d);
	}
	
	public void updatePrev (Node n, Node p) {
		myPrevs.put(n, p);
	}
	
	public double getDist (Node n) {
		return myDists.get(n);
	}
	
	public Node getPrev (Node n) {
		return myPrevs.get(n);
	}
	
	public String toString () {
		String result = "";
		Iterator<Node> nodes = myDists.keySet().iterator();
		Node next;
		while (nodes.hasNext()) {
			next = nodes.next();
			result += next + ": " + myDists.get(next) + "\n";
		}
		return result;
	}
	
}
