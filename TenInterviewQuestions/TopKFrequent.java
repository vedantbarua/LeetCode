import java.util.*;

/**
 * Top K Frequent Elements
 * DS: HashMap counts + min-heap of size k.
 * Algo: count frequencies, push (freq,val) to heap, trim to k.
 * Thought: heap keeps k most frequent without sorting all entries.
 * Time: O(n log k). Space: O(n).
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [freq, val]
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            minHeap.offer(new int[]{e.getValue(), e.getKey()});
            if (minHeap.size() > k) minHeap.poll();
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) res[i] = minHeap.poll()[1];
        return res;
    }

    public static void main(String[] args) {
        TopKFrequent solver = new TopKFrequent();
        System.out.println(Arrays.toString(solver.topKFrequent(new int[]{1,1,1,2,2,3}, 2))); // [1,2]
    }
}
