
import java.util.*;

class Graph {

	Map<Integer, Vertex> vs = new TreeMap<>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Vertex v : vs.values()) {
			sb.append("[").append(v).append("]").append(", ");
		}
		sb.append("}");
		return sb.toString();
	}
}

class Vertex implements Comparable<Vertex> {

	Integer data;
	ArrayList<Integer> vs = new ArrayList<>();

	Vertex(Integer data) {
		if (data == null) {
			throw new NullPointerException("data cannot be null");
		}
		this.data = data;
	}

	@Override
	public int compareTo(Vertex that) {
		return data.compareTo(that.data);
	}

	@Override
	public String toString() {
		return data + "->" + vs;
	}

}
