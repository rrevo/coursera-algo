
public class QuickSortMain {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		QuickSortMain main = new QuickSortMain();
		main.process(new QuickSortAlgo("StartIndexPivot") {
			@Override
			int pivotIndex(int[] nums, int startIndex, int endIndex) {
				return startIndex;
			}
		}, args[0]);
		main.process(new QuickSortAlgo("EndIndexPivot") {
			@Override
			int pivotIndex(int[] nums, int startIndex, int endIndex) {
				return endIndex - 1;
			}
		}, args[0]);
		main.process(new QuickSortAlgo("MedianPivot") {
			@Override
			int pivotIndex(int[] nums, int startIndex, int endIndex) {
				int midIndex = (startIndex + endIndex - 1) / 2;
				return medianIndex(nums, startIndex, midIndex, endIndex - 1);
			}

			// NOTE Quiz problems have unique values.
			private int medianIndex(int[] nums, int leftIndex, int midIndex, int rightIndex) {
				if (nums[leftIndex] < nums[midIndex]) {
					if (nums[midIndex] < nums[rightIndex]) {
						return midIndex;
					} else {
						if (nums[leftIndex] > nums[rightIndex]) {
							return leftIndex;
						} else {
							return rightIndex;
						}
					}
				} else {
					if (nums[leftIndex] < nums[rightIndex]) {
						return leftIndex;
					} else {
						if (nums[midIndex] > nums[rightIndex]) {
							return midIndex;
						} else {
							return rightIndex;
						}
					}
				}
			}
		}, args[0]);
	}

	void process(QuickSortAlgo algo, String inputFileName) throws Exception {
		int[] nums = InputParser.asArray(InputParser.parseInputFile(inputFileName));
		long comparisons = algo.sort(nums);
		validate(nums);
		System.out.println(algo.name + " " + comparisons);
	}

	void validate(int[] nums) {
		validate(nums, 0, nums.length);
	}

	void validate(int[] nums, int startIndex, int endIndex) {
		if ((endIndex - startIndex) < 2) {
			return;
		}
		int prev = startIndex;
		for (int i = startIndex + 1; i < endIndex; i++) {
			int current = nums[i];
			if (current < prev) {
				throw new RuntimeException(current + " < " + prev + " at index " + i);
			}
			prev = current;
		}
	}
}

