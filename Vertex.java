public class Vertex {
	private String myCurrency;
	
	public Vertex (String currency) {
		myCurrency = currency;
	}
	
	public Vertex copy () {
		return new Vertex (myCurrency);
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