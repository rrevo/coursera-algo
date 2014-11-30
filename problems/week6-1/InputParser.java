import java.util.*;
import java.io.*;

public class InputParser {

	public static Map<Long, Long> parseInputFile(String inputFile) throws Exception {
        Map<Long, Long> nums = new HashMap<>();
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            for(String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                Long i = Long.parseLong(line);
                if (!nums.containsKey(i)) {
                    nums.put(i, 1l);
                } else {
                    nums.put(i, nums.get(i) + 1);
                }
            }
        }
        return nums;
    }
}
