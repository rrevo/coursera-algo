import java.util.*;
import java.io.*;

public class InputParser {

	public static Graph parseInputFile(String inputFile) throws Exception {
		Graph g = new Graph();
		Map<Integer, Vertex> vs = g.vs;
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
    		for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
    			String[] numStrings = line.split("\\s");
    			Integer data = Integer.parseInt(numStrings[0]);
    			Vertex v = new Vertex(data);
    			for (int i = 1; i < numStrings.length; i++) {
    				v.vs.add(Integer.parseInt(numStrings[i]));
    			}
    			vs.put(v.data, v);
    		}
    	}
    	return g;
	}

    public static int parseVertexCount(String inputFile) throws Exception {
        int i = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] numStrings = line.split("\\s");
                Integer data = Integer.parseInt(numStrings[0]);
                i++;
            }
        }
        return i;
    }
}
