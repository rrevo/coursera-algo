import java.util.*;

public class ShortestPath {
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		ShortestPath shortestPath = new ShortestPath();
		shortestPath.process(args[0]);
	}

	boolean debug = false;
	boolean course = true;

	void process(String inputFile) throws Exception {
		Graph g = InputParser.parseInputFile(inputFile);
		d(g);

		Vertex source = g.getOrCreate(InputParser.getFirstVertexName(inputFile));
		d(source);

		Set<Vertex> unreachableVs = findUnreachable(g, source);
		d(unreachableVs);

		g.remove(unreachableVs);
		d(g);

		Map<Vertex, Integer> pathWeights = getShortestPaths(g, source);
		d(pathWeights);
		for (Vertex v : unreachableVs) {
			pathWeights.put(v, 1000000);
		}

		if (course) {
			String[] keys = new String[] {"7", "37", "59", "82", "99", "115", "133", "165", "188", "197"};
			StringBuilder sb = new StringBuilder();
			for (String key : keys) {
				sb.append(pathWeights.get(g.getOrCreate(key))).append(",");
			}
			System.out.println(sb.substring(0, sb.length() - 1));
		} else {
			System.out.println(pathWeights);
		}
	}
	
	Set<Vertex> findUnreachable(Graph g, Vertex source) {
		Map<Vertex, Boolean> visited = new HashMap<>();
		for (Vertex v : g.vs.values()) {
			visited.put(v, false);
		}
		dfs(source, visited);
		Set<Vertex> unreachableVs = new HashSet<>();
		for (Map.Entry<Vertex, Boolean> vb : visited.entrySet()) {
			if (!vb.getValue()) {
				unreachableVs.add(vb.getKey());
			}
		}
		return unreachableVs;
	}

	void dfs(Vertex source, Map<Vertex, Boolean> visited) {
		if (visited.get(source)) {
			return;
		}
		visited.put(source, true);
		for (Vertex dest : source.vs.keySet()) {
			dfs(dest, visited);
		}
	}

	Map<Vertex, Integer> getShortestPaths(Graph g, Vertex source) {
		List<Vertex> vs = new ArrayList<>();
		Map<Vertex, Integer> pathWeights = new LinkedHashMap<>();
		pathWeights.put(source, 0);
		vs.add(source);

		while (!vs.isEmpty()) {
			Vertex u = vs.remove(0);
			int uWeight = pathWeights.get(u);

			for (Map.Entry<Vertex, Integer> vWeight : u.vs.entrySet()) {
				int weight = uWeight + vWeight.getValue();
				Vertex v = vWeight.getKey();
				if (!pathWeights.containsKey(v) || (weight < pathWeights.get(v))) {
					pathWeights.put(v, weight);
					vs.add(v);
				}
			}
		}

		return pathWeights;
	}

	void d(Object o) {
		if (debug) {
			System.out.println(o);
		}
	}

}