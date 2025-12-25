/**
 * LeetCode 347 - Top K Frequent Elements
 * Approach: Frequency map then bucket sort by frequency to collect top k.
 * Time: O(n)
 * Space: O(n)
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        int[] result = new int[k];
        int idx = 0;
        for (int i = buckets.size() - 1; i >= 0 && idx < k; i--) {
            for (int num : buckets.get(i)) {
                result[idx++] = num;
                if (idx == k) {
                    break;
                }
            }
        }
        return result;
    }
}
