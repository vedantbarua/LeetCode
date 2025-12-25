/**
 * LeetCode 1 - Two Sum
 * Approach: HashMap from value to index; check complement while scanning once.
 * Time: O(n)
 * Space: O(n)
 */
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByVal = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexByVal.containsKey(complement)) {
                return new int[] {indexByVal.get(complement), i};
            }
            indexByVal.put(nums[i], i);
        }
        return new int[0];
    }
}
