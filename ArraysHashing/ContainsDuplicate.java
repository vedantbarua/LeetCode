/**
 * LeetCode 217 - Contains Duplicate
 * Approach: HashSet to track seen numbers; return true on first repeat.
 * Time: O(n)
 * Space: O(n)
 */
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (!seen.add(n)) {
                return true;
            }
        }
        return false;
    }
}
