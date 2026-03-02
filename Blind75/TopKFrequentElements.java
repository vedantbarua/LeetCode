package Blind75;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    // Question: Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
    
      public int[] topKFrequent(int[] nums, int k) {
       Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        pq.addAll(count.entrySet());

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements tkfe = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = tkfe.topKFrequent(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
