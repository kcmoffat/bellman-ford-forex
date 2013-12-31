import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		Node u = new Node("USD");
		Node e = new Node("EUR");
		Edge e1 = new Edge(u,e, 1.2234);
		Edge e2 = new Edge(e,u, 0.5566);
		g.addNode(u);
		g.addNode(e);
		g.addEdge(e1);
		g.addEdge(e2);
		ShortestPaths result = Graph.bellmanFord(g, e);
		System.out.println(result.getDist(u));
		System.out.println(result.getDist(e));
		System.out.print(g);
	}
}
