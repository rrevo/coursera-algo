
import java.util.*;

class Graph {

	Map<String, Vertex> vs = new TreeMap<>();

	Vertex getOrCreate(String name) {
		if (!vs.containsKey(name)) {
			vs.put(name, new Vertex(name));
		}
		return vs.get(name);
	}

	void remove(Vertex v) {
		vs.remove(v.name);
	}

	void remove(Set<Vertex> vs) {
		for (Vertex v : vs) {
			remove(v);
		}
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
	final Map<Vertex, Integer> vs = new LinkedHashMap<>();

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
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		Vertex thatVertex = (Vertex) that;
		return this.name.equals(thatVertex.name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append("->").append("[");
		for (Map.Entry<Vertex, Integer> entry : vs.entrySet()) {
			sb.append(entry.getKey().name).append(",").append(entry.getValue()).append(" ");
		}
		sb.append("]");
		return sb.toString();
	}

}
