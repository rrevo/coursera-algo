import java.util.*;

public class Median {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Pass the input file as an argument");
		}
		Median median = new Median();
		median.process(args[0]);
	}

	boolean debug = false;

	void process(String inputFile) throws Exception {
		List<Integer> nums = InputParser.parseInputFile(inputFile);

		PriorityQueue<Integer> highs = new PriorityQueue<>();
		PriorityQueue<Integer> lows = new PriorityQueue<>(7, new ReverseIntComparator());

		List<Integer> medians = new ArrayList<>();
		int i = 1;
		for (Integer num : nums) {
			Integer median = processMedian(highs, lows, num);
			d(i + ": " + median);
			medians.add(median);
			i++;
		}

		int sumOfMedians = 0;
		for (Integer median : medians) {
			sumOfMedians += median;
		}
		d("Sum Of Medians: " + sumOfMedians);
		System.out.println(sumOfMedians % 10000);
	}

	int processMedian(PriorityQueue<Integer> highs, PriorityQueue<Integer> lows, Integer number) {
		Integer smallestHigh = highs.peek();
		Integer biggestLow = lows.peek();

		if (smallestHigh == null && biggestLow == null) {
			highs.add(number);
			return number;
		} else {
			if (smallestHigh == null) {
				lows.add(number);
			} else if (biggestLow == null) {
				highs.add(number);
			} else {
				if (number <= biggestLow) {
					lows.add(number);
				} else {
					highs.add(number);
				}
			}
			rebalance(highs, lows);

			int highsSize = highs.size();
			int lowsSize = lows.size();
			if (highsSize == lowsSize) {
				return lows.peek();
			} else if ((highsSize - lowsSize) == 1) {
				return highs.peek();
			} else if ((lowsSize - highsSize) == 1) {
				return lows.peek();
			} else {
				throw new RuntimeException("Queues are not balanced");
			}
		}
	}

	void rebalance(PriorityQueue<Integer> highs, PriorityQueue<Integer> lows) {
		int highsSize = highs.size();
		int lowsSize = lows.size();

		if ((highsSize - lowsSize) == 2) {
			lows.add(highs.poll());
		} else if ((lowsSize - highsSize) == 2) {
			highs.add(lows.poll());
		}
	}

	void d(Object o) {
		if (debug) {
			System.out.println(o);
		}
	}

	static class ReverseIntComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer a, Integer b) {
			return b.compareTo(a);
		}
	}

}