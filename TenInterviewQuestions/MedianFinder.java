import java.util.*;

/**
 * Find Median from Data Stream
 * DS: two heaps (max-heap lower half, min-heap upper half).
 * Algo: insert into appropriate heap, then rebalance sizes so diff <= 1.
 * Thought: ensures all low <= all high; median is top(s).
 * Time: O(log n) per add, O(1) find. Space: O(n).
 */
public class MedianFinder {
    private PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    private PriorityQueue<Integer> high = new PriorityQueue<>(); // min-heap

    public void addNum(int num) {
        if (low.isEmpty() || num <= low.peek()) low.offer(num);
        else high.offer(num);
        if (low.size() > high.size() + 1) high.offer(low.poll());
        else if (high.size() > low.size()) low.offer(high.poll());
    }

    public double findMedian() {
        if (low.size() == high.size()) return (low.peek() + high.peek()) / 2.0;
        return low.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0
    }
}
