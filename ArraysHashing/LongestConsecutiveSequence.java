/**
 * LeetCode 128 - Longest Consecutive Sequence
 * Approach: HashSet to detect sequence starts; expand only from starts.
 * Time: O(n)
 * Space: O(n)
 */
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int best = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) { // start of a sequence
                int length = 1;
                int current = n;
                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }
                best = Math.max(best, length);
            }
        }
        return best;
    }
}
