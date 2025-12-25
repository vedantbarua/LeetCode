import java.util.*;

/**
 * 3Sum
 * DS: array (sorted in-place).
 * Algo: sort then two-pointer for each fixed index, skipping duplicates.
 * Thought: sorting allows linear search for complements while de-duping results.
 * Time: O(n^2). Space: O(1) extra (ignoring output).
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++; r--;
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum solver = new ThreeSum();
        System.out.println(solver.threeSum(new int[]{-1,0,1,2,-1,-4}));
        // [[-1, -1, 2], [-1, 0, 1]]
    }
}
