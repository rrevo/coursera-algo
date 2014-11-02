public abstract class QuickSortAlgo {

	String name;

	QuickSortAlgo(String name) {
		this.name = name;
	}

	long sort(int[] nums) {
		return sort(nums, 0, nums.length);
	}

	long sort(int[] nums, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return 0l;
		}
		long comparisons = endIndex - startIndex - 1;

		int pivotIndex = pivotIndex(nums, startIndex, endIndex);

		// Get the pivot to the start
		swap(nums, startIndex, pivotIndex);
		pivotIndex = partition(nums, startIndex, endIndex);

		comparisons += sort(nums, startIndex, pivotIndex);
		comparisons += sort(nums, pivotIndex + 1, endIndex);

		return comparisons;
	}

	/**
	 * Assume that pivot element has been moved to startIndex
	 */
	int partition(int[] nums, int startIndex, int endIndex) {
		int pivot = nums[startIndex];
		int i = startIndex + 1;
		for (int j = i; j < endIndex; j++) {
			if (nums[j] < pivot) {
				swap(nums, i, j);
				i = i + 1;
			}
		}
		swap(nums, startIndex, i - 1);
		return (i - 1);
	}

	void swap(int[] nums, int a, int b) {
		if (a == b) {
			return;
		}
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	abstract int pivotIndex(int[] nums, int startIndex, int endIndex);

}