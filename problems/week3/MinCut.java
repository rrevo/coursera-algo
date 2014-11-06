import java.util.*;

public class MinCut {
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		MinCut minCut = new MinCut();
		minCut.process(args[0]);
	}

	Random random = new Random();
	boolean debug = false;

	void process(String inputFile) throws Exception {
		int vertexCount = InputParser.parseVertexCount(inputFile);
		d("VertexCount: " + vertexCount);
		int repetitions = vertexCount;
		int minCut = Integer.MAX_VALUE;
		for (int i = 0; i < repetitions; i++) {
			Graph g = InputParser.parseInputFile(inputFile);
			d(g);
			int size = g.vs.size();
			while (size > 2) {
				contract(g);
				size = g.vs.size();
				d(g);
			}
			Vertex v = g.vs.values().iterator().next();
			int cuts = v.vs.size();
			d("cuts: " + cuts);

			if (cuts < minCut) {
				minCut = cuts;
			}
		}
		System.out.println("MinCuts: " + minCut);
	}

	void contract(Graph g) {
		// Pick a random edge
		int pIndex = random.nextInt(g.vs.size());
		Vertex p = getVertexByIndex(g.vs.values(), pIndex);
		d("p: " + p);

		int qIndex = random.nextInt(p.vs.size());
		Vertex q = g.vs.get(p.vs.get(qIndex));
		d("q: " + q);

		// Merge p into q
		for (Integer pvData : p.vs) {
			Vertex pv = g.vs.get(pvData);
			ArrayList<Integer> pvVs = pv.vs;
			for (int i = 0; i < pvVs.size(); i++) {
				if (pvVs.get(i).equals(q.data)) {
					continue;
				}

				if (pvVs.get(i).equals(p.data)) {
					pvVs.set(i, q.data);
					q.vs.add(pvData);
				}
			}
		}

		// Remove self loops
		q.vs.removeAll(Collections.singleton(q.data));

		// Remove p
		g.vs.remove(p.data);
	}

	Vertex getVertexByIndex(Collection<Vertex> vs, final int index) {
		int i = -1;
		Iterator<Vertex> iter = vs.iterator();
		Vertex v = null;
		while (i != index) {
			v = iter.next();
			i++;
		}
		return v;
	}

	void d(Object o) {
		if (debug) {
			System.out.println(o);
		}
	}

}