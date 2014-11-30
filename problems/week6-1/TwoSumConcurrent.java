import java.util.*;
import java.util.concurrent.*;

public class TwoSumConcurrent {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		TwoSumConcurrent twoSum = new TwoSumConcurrent();
		twoSum.process(args[0]);
	}

	static boolean debug = false;
	static boolean info = true;

	void process(String inputFile) throws Exception {
		// Get the numbers with occurrences
		ConcurrentHashMap<Long, Long> nums = new ConcurrentHashMap<>(InputParser.parseInputFile(inputFile));
		d(nums);

		ForkJoinPool pool = new ForkJoinPool();

		List<SumTask> tasks = new ArrayList<>();
		for (int t = -10_000; t <= 10_000; t++) {
			SumTask task = new SumTask(t, nums);
			tasks.add(task);
			pool.execute(task);
		}
        i(String.format("Parallelism: %d", pool.getParallelism()));
        i(String.format("Active Threads: %d", pool.getActiveThreadCount()));
        i(String.format("Task Count: %d", pool.getQueuedTaskCount()));
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
		int count = 0;
		for (SumTask task : tasks) {
			if (task.getRawResult()) {
				count++;
			}
		}
		System.out.println(count);
	}

	static void d(Object o) {
		if (debug) {
			System.out.println(o);
		}
	}

	static void i(Object o) {
		if (info) {
			System.out.println(o);
		}
	}

	static class SumTask extends RecursiveTask<Boolean> {

		final long targetSum;
		final Map<Long, Long> nums;

		SumTask(long targetSum, Map<Long, Long> nums) {
			this.targetSum = targetSum;
			this.nums = nums;
		}

		@Override
		public Boolean compute() {
			boolean found = false;
			for (Long x : nums.keySet()) {
				Long y = targetSum - x;
				if (x == y) {
					if (nums.get(x) > 1) {
						found = true;
					}
				} else if (nums.containsKey(y)) {
					found = true;
				}
				if (found) {
					d("x: " + x + " + y: " + y + " = t: " + targetSum);
					break;
				}
			}
			i("Computed t: " + targetSum + "=" + found);
			return found;
		}

	}

}