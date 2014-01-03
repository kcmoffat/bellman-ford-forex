import java.util.ArrayList;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class MainActivity {
	public static void main(String[] args) {
		
		System.out.print("Running Negative Cycle Detector.");
		
		while (true) {
		
			Graph g = new Graph();
			Vertex s = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String urlString = "http://rates.fxcm.com/RatesXML";
			HttpGet httpget = new HttpGet(urlString);
			try {
				CloseableHttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(content);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("Rate");
				
				for (int i = 0; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);					
					Element eElement = (Element) nNode;

					try {
						String from = eElement.getAttribute("Symbol").substring(0, 3);
						String to = eElement.getAttribute("Symbol").substring(3, 6);
						Double ask = Double.parseDouble(eElement.getElementsByTagName("Ask").item(0).getTextContent());
						Double bid = Double.parseDouble(eElement.getElementsByTagName("Bid").item(0).getTextContent());
						Vertex u = new Vertex(from);
						Vertex v = new Vertex(to);
						Edge e1 = new Edge(u, v, -Math.log(bid));
						Edge e2 = new Edge(v, u, -Math.log(1/ask));
						
						if (!g.contains(u)) {
							if (from.equals("USD")) {
								s = u;
							}
							g.addNode(u);
						}
						if (!g.contains(v)) {
							if (to.equals("USD")) {
								s = v;
								e2.updateWeight(e2.getWeight());
							}
							g.addNode(v);
						}
						if (!g.contains(e1)) {
							g.addEdge(e1);
						}
						if (!g.contains(e2)) {
							g.addEdge(e2);
						}
					} catch (Exception e) {
						// do nothing
					}
				}
			} catch (Exception e) {
				// do nothing
			}
			
			ArrayList<Vertex> result = Graph.negCycle(g, s); 
			if (result.isEmpty()) {
				System.out.print(".");
			} else {
				System.out.println(result);
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
