import java.util.*;

/**
 * Use TwoSumConcurrent for a concurrent solution of the same algorithm
 *
 */
@Deprecated
public class TwoSum {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		TwoSum twoSum = new TwoSum();
		twoSum.process(args[0]);
	}

	boolean debug = false;
	boolean info = true;

	void process(String inputFile) throws Exception {
		// Get the numbers with occurrences
		Map<Long, Long> nums = InputParser.parseInputFile(inputFile);
		d(nums);

		HashSet<Integer> targets = new HashSet<>();
		for (int t = -10_000; t <= 10_000; t++) {
			for (Long x : nums.keySet()) {
				Long y = t - x;
				boolean found = false;
				if (x == y) {
					if (nums.get(x) > 1) {
						found = true;
					}
				} else if (nums.containsKey(y)) {
					found = true;
				}
				if (found) {
					targets.add(t);
					d("x: " + x + " + y: " + y + " = t: " + t);
					break;
				}
			}
			i("Computed " + t);
		}
		System.out.println(targets.size());
	}

	void d(Object o) {
		if (debug) {
			System.out.println(o);
		}
	}

	void i(Object o) {
		if (info) {
			System.out.println(o);
		}
	}

}