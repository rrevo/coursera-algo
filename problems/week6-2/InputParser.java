import java.util.*;
import java.io.*;

public class InputParser {

	public static List<Integer> parseInputFile(String inputFile) throws Exception {
		List<Integer> nums = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
    		for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                Integer i = Integer.parseInt(line);
                nums.add(i);
    		}
    	}
    	return nums;
	}
}
