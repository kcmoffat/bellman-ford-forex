public class Node {
	private String myCurrency;
	
	public Node (String currency) {
		myCurrency = currency;
	}
	
	public Node copy () {
		return new Node (myCurrency);
	}
	
	public String toString() {
		return myCurrency;
	}
	
	public int hashCode() {
		return myCurrency.hashCode();
	}
	
	public boolean equals (Object obj) {
		return this.toString().equals(obj.toString());
	}
}