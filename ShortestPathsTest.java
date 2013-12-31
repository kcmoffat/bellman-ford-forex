import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


public class ShortestPathsTest {

	@Test
	public void test() {
		Graph g = new Graph();
		Node one = new Node("one");
		Node two = new Node("two");
		Node three = new Node("three");
		Node four = new Node("four");
		Node five = new Node("five");
		Node six = new Node("six");
		Node seven = new Node("seven");
		Node eight = new Node("eight");
		Edge e1 = new Edge(one,two, 4);
		Edge e2 = new Edge(one,four, 4);
		Edge e3 = new Edge(three,one, 3);
		Edge e4 = new Edge(three,four, 2);
		Edge e5 = new Edge(four,five, -2);
		Edge e6 = new Edge(four,six, 4);
		Edge e7 = new Edge(five,two, 3);
		Edge e8 = new Edge(five,six, -3);
		Edge e9 = new Edge(six,three, 1);
		Edge e10 = new Edge(six,seven, -2);
		Edge e11 = new Edge(seven,five, 2);
		Edge e12 = new Edge(seven,eight, 2);
		Edge e13 = new Edge(eight,six, -2);
		g.addNode(one);
		g.addNode(two);
		g.addNode(three);
		g.addNode(four);
		g.addNode(five);
		g.addNode(six);
		g.addNode(seven);
		g.addNode(eight);
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);
		g.addEdge(e6);
		g.addEdge(e7);
		g.addEdge(e8);
		g.addEdge(e9);
		g.addEdge(e10);
		g.addEdge(e11);
		g.addEdge(e12);
		g.addEdge(e13);
		//ShortestPaths result = Graph.bellmanFord(g, one);
		//System.out.print(g);
		//System.out.print(result);
		ArrayList<Node> cycle = Graph.negCycle(g, one);
		System.out.println(cycle);
		fail("Not yet implemented");
	}
}
