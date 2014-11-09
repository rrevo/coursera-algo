import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class InputParser {

	public static Graph parseInputFile(String inputFile) throws Exception {
		Graph g = new Graph();
        Pattern p = Pattern.compile("\\s");
		Map<String, Vertex> vs = g.vs;
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
    		for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
    			String[] names = p.split(line);
                Vertex u = g.getOrCreate(names[0]);
                Vertex v = g.getOrCreate(names[1]);
                u.vs.add(v);
    		}
    	}
    	return g;
	}
}
