import java.util.ArrayList;
import java.util.Iterator;
import java.util.AbstractList;
import java.util.Stack;
import java.util.HashSet;

public class Graph {
	private ArrayList<Vertex> myNodes;
	private ArrayList<Edge> myEdges;
	
	public static ShortestPaths bellmanFord (Graph g, Vertex s) {
		
		// Initialize
		ShortestPaths result = new ShortestPaths ();
		Iterator<Vertex> nodes = g.getNodes();
		Vertex next;
		while (nodes.hasNext()) {
			next = nodes.next();
			result.updateDist(next, Double.POSITIVE_INFINITY);
			result.updatePrev(next, null);
		}
		
		result.updateDist(s, 0);
		// Perform |V|-1 updates of all edges
		int V = g.numNodes();
		Iterator<Edge> edges;
		Edge e;
		Vertex u;
		Vertex v;
		for (int i = 0; i < V-1; i++) {
			 edges = g.getEdges();
			 while (edges.hasNext()) {
				 e = edges.next();
				 u = e.getStart();
				 v = e.getEnd();
				 if (result.getDist(u) + e.getWeight() < result.getDist(v)) {
					 result.updateDist(v, result.getDist(u) + e.getWeight());
					 result.updatePrev(v, u);
				 }
			 }
		}
		return result;
	}
	
	// Returns an ArrayList containing a negative cycle in g if one exists.  
	// Return null otherwise.
	public static ArrayList<Vertex> negCycle (Graph g, Vertex s) {
		ShortestPaths bf = bellmanFord(g, s);
		Iterator<Edge> edges = g.getEdges();
		ArrayList<Vertex> result = new ArrayList<Vertex> ();
		Edge e;
		Vertex u;
		Vertex v;
		while (edges.hasNext()) {
			e = edges.next();
			u = e.getStart();
			v = e.getEnd();
			if (bf.getDist(u) + e.getWeight() < bf.getDist(v)) {
				Stack<Vertex> cyc = new Stack<Vertex> ();
				HashSet<Vertex> seen = new HashSet<Vertex> ();
				while (!seen.contains(v)) {
					cyc.push(v);
					seen.add(v);
					v = bf.getPrev(v);
				}
				cyc.push(v);
				while (!cyc.isEmpty()) {
					result.add(cyc.pop());
				}
				return result;
			}
		}
		return result;
	}
	
	public Graph () {
		myNodes = new ArrayList<Vertex>();
		myEdges = new ArrayList<Edge>();
	}
	
	public void addNode (Vertex n) {
		myNodes.add(n);
	}
	
	public void addEdge (Edge e) {
		myEdges.add(e);
	}
	
	public Iterator<Vertex> getNodes() {
		return myNodes.iterator();
	}
	
	public Iterator<Edge> getEdges() {
		return myEdges.iterator();
	}
	
	public int numNodes () {
		return myNodes.size();
	}
	
	public int numEdges () {
		return myEdges.size();
	}
	
	public boolean contains (Vertex n) {
		return myNodes.contains(n);
	}
	
	public boolean contains (Edge e) {
		return myEdges.contains(e);
	}
	
	public String toString () {
		String result = "";
		if (!myNodes.isEmpty()) {
			Iterator<Vertex> nodes = this.getNodes();
			result += "Nodes:\n";
			while (nodes.hasNext()) {
				result += nodes.next() + "\n";
			}
		}
		if (!myEdges.isEmpty()) {
			Iterator<Edge> edges = this.getEdges();
			result += "Edges:\n";
			while (edges.hasNext()) {
				result += edges.next() + "\n";
			}
		}
		return result;
	}
}
