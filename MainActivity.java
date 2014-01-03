import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

		Graph g = new Graph();
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
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Rate");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				System.out.println("Current element: " + nNode.getNodeName());
				
				Element eElement = (Element) nNode;
				System.out.println("Currency: " + eElement.getAttribute("Symbol"));
				try {
					String from = eElement.getAttribute("Symbol").substring(0, 3);
					String to = eElement.getAttribute("Symbol").substring(3, 6);
					Double weight = Double.parseDouble(eElement.getElementsByTagName("Ask").item(0).getTextContent());
					Vertex f = new Vertex(from);
					Vertex t = new Vertex(to);
					Edge e = new Edge(f, t, weight);
					if (!g.contains(f) && !g.contains(t) && !g.contains(e)) {
						g.addNode(f);
						g.addNode(t);
						g.addEdge(e);
					}
					
				} catch (Exception e) {
					// do nothing
				}
				
			}
			System.out.println(g);
			
			/*
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			StringBuilder builder = new StringBuilder();
			for (String line = null; (line = reader.readLine()) != null;) {
				builder.append(line).append("\n");
				System.out.println(line);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
