
import java.util.*;

class Graph {

	Map<String, Vertex> vs = new TreeMap<>();

	Vertex getOrCreate(String name) {
		if (!vs.containsKey(name)) {
			vs.put(name, new Vertex(name));
		}
		return vs.get(name);
	}

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

	final String name;
	final ArrayList<Vertex> vs = new ArrayList<>();

	Vertex(String name) {
		if (name == null) {
			throw new NullPointerException("name cannot be null");
		}
		this.name = name;
	}

	@Override
	public int compareTo(Vertex that) {
		return name.compareTo(that.name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append("->").append("[");
		for (Vertex v : vs) {
			sb.append(v.name).append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
