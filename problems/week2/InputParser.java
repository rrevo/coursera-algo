import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class InputParser {

	public static List<Integer> parseInputFile(String inputFile) throws IOException {
		List<Integer> nums = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
    		for(String line; (line = br.readLine()) != null; ) {
    			nums.add(Integer.parseInt(line));
    		}
    	}
    	return nums;
	}

	public static int[] asArray(List<Integer> nums) {
		int[] numsArray = new int[nums.size()];
		for (int i = 0; i < nums.size(); i++) {
			numsArray[i] = nums.get(i);
		}
		return numsArray;
	}

}
