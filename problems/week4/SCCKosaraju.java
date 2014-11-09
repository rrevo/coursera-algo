import java.util.*;

public class SCCKosaraju {
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		SCCKosaraju scc = new SCCKosaraju();
		scc.process(InputParser.parseInputFile(args[0]));
	}

	boolean debug = false;

	void process(Graph g) {
		d("Actual  :" + g);
		Graph r = reverse(g);
		d("Reverse :" + r);
		TreeMap<Integer, Vertex> order = dfsFinishTime(r);
		d("Timings :" + order);
		Map<Vertex, List<Vertex>> sccs = dfsSCC(order, g);
		for(List<Vertex> scc : sccs.values()) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Vertex v : scc) {
				sb.append(v.name).append(", ");
			}
			sb.append("]");
			d("SCC     :" + sb.toString());
		}
		List<Integer> largeSccs = largeSccs(sccs);
		for (int i = 0; i < 4; i++) {
			System.out.print(largeSccs.get(i) + "");
			System.out.print(",");
		}
		System.out.println(largeSccs.get(4) + "");
	}

	Graph reverse(Graph g) {
		Graph r = new Graph();
		for (Vertex u : g.vs.values()) {
			for (Vertex v : u.vs) {
				Vertex uCopy = r.getOrCreate(u.name);
				Vertex vCopy = r.getOrCreate(v.name);
				vCopy.vs.add(uCopy);
			}
		}
		return r;
	}

	TreeMap<Integer, Vertex> dfsFinishTime(Graph g) {
        TreeMap<Integer, Vertex> result = new TreeMap<>();
        Time time = new Time();

        Set<Vertex> visited = new HashSet<Vertex>();

        for (Vertex v : g.vs.values()) {
            dfsFinishTime(v, result, time, visited);
        }

        return result;
    }

    void dfsFinishTime(Vertex v, TreeMap<Integer, Vertex> result, Time time, Set<Vertex> visited) {
        if (visited.contains(v)){
        	return;
        }

        visited.add(v);
        for (Vertex u : v.vs) {
        	dfsFinishTime(u, result, time, visited);
        }

        time.value++;
        result.put(time.value, v);
    }

    Map<Vertex, List<Vertex>> dfsSCC(TreeMap<Integer, Vertex> order, Graph g) {
        Map<Vertex, List<Vertex>> result = new HashMap<>();

        Set<String> visited = new HashSet<String>();

        Set<Integer> descendingOrder = order.descendingKeySet();
        for (Integer i : descendingOrder) {
        	String name = order.get(i).name;
        	Vertex v = g.getOrCreate(name);
        	LeaderResult leaderResult = new LeaderResult();
            dfsSCC(v, leaderResult, visited);
            if (leaderResult.leader != null) {
            	result.put(leaderResult.leader, leaderResult.vs);
            }
        }

        return result;
    }

    void dfsSCC(Vertex v, LeaderResult result, Set<String> visited) {
        if (visited.contains(v.name)){
        	return;
        }

        visited.add(v.name);
        if (result.leader == null) {
        	result.leader = v;
        }
        result.vs.add(v);
        for (Vertex u : v.vs) {
        	dfsSCC(u, result, visited);
        }
    }

    List<Integer> largeSccs(Map<Vertex, List<Vertex>> sccs) {
    	List<Integer> sccSizes = new ArrayList<>();
    	for (List<Vertex> scc : sccs.values()) {
    		sccSizes.add(scc.size());
    	}
    	// Need at least 5 sizes for the output for the course
    	while(sccSizes.size() < 5) {
    		sccSizes.add(0);
    	}

    	Collections.sort(sccSizes);
    	Collections.reverse(sccSizes);
    	return sccSizes;
    }

	void d(Object o) {
		if (debug) {
			System.out.println(o);
		}
	}

	class Time {
		int value = 0;
	}

	class LeaderResult {
		Vertex leader = null;
		List<Vertex> vs = new ArrayList<>();
	}

}