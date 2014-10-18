
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Inversions {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		new Inversions().process(args);
	}

	void process(String[] args) throws Exception {
		List<Integer> nums = parseInputFile(args[0]);
		Holder inversions = countInversions(nums);
		System.out.println(inversions.inversions);
	}

	List<Integer> parseInputFile(String inputFile) throws IOException {
		List<Integer> nums = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
    		for(String line; (line = br.readLine()) != null; ) {
    			nums.add(Integer.parseInt(line));
    		}
    	}
    	return nums;
	}

	Holder countInversions(List<Integer> nums) {
		Holder h = new Holder();
		if (nums.isEmpty()) {
			h.data = nums;
			h.inversions = 0;
		} else if (nums.size() == 1) {
			h.data = nums;
			h.inversions = 0;
		} else {
			int midIndex = nums.size() / 2;
			List<Integer> left = split(nums, 0, midIndex);
			List<Integer> right = split(nums, midIndex, nums.size());

			Holder leftInversions = countInversions(left);
			Holder rightInversions = countInversions(right);
			Holder mergedNums = mergeAndCountSplit(leftInversions.data, rightInversions.data);
			h.data = mergedNums.data;
			h.inversions = leftInversions.inversions + rightInversions.inversions + mergedNums.inversions;
		}
		return h;
	}

	List<Integer> split(List<Integer> nums, int startIndex, int endIndex) {
		return new ArrayList<Integer>(nums.subList(startIndex, endIndex));
	}

	Holder mergeAndCountSplit(List<Integer> left, List<Integer> right) {
		Holder h = new Holder();
		if (left.isEmpty()) {
			h.data = right;
			h.inversions = 0;
		} else if (right.isEmpty()) {
			h.data = left;
			h.inversions = 0;
		} else {
			List<Integer> list = new ArrayList<>();

			final int leftSize = left.size();
			final int rightSize = right.size();
			
			int leftIndex = 0;
			int rightIndex = 0;

			while (leftIndex < leftSize && rightIndex < rightSize) {
				int leftData = left.get(leftIndex);
				int rightData = right.get(rightIndex);

				if (leftData <= rightData) {
					list.add(leftData);
					leftIndex++;
				} else {
					list.add(rightData);
					rightIndex++;
					h.inversions += (leftSize - leftIndex);
				}
			}
			while (leftIndex < leftSize) {
				list.add(left.get(leftIndex));
				leftIndex++;
			}
			while (rightIndex < rightSize) {
				list.add(right.get(rightIndex));
				rightIndex++;
			}
			h.data = list;
		}
		return h;
	}

	private static class Holder {
		List<Integer> data = null;
		long inversions = 0;
	}

}