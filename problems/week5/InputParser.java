import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern p = Pattern.compile("\\s");

	public static Graph parseInputFile(String inputFile) throws Exception {
		Graph g = new Graph();
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
    		for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] names = p.split(line);
                Vertex u = g.getOrCreate(names[0]);
    			for (int i = 1; i < names.length; i++) {
                    String nameWeight = names[i].trim();
                    String [] parts = nameWeight.split(",");
                    Vertex v = g.getOrCreate(parts[0]);
                    int weight = Integer.parseInt(parts[1]);
                    u.vs.put(v, weight);
    			}
    		}
    	}
    	return g;
	}

    public static String getFirstVertexName(String inputFile) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] names = p.split(line);
                return names[0];
            }
        }
        return null;
    }
}
