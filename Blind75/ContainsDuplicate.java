package Blind75;
import java.util.HashSet;

public class ContainsDuplicate {
    // Question: Given an integer array nums, return true if any value appears
    // at least twice in the array, and return false if every element is distinct.
    
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();
        int[] nums = {1, 2, 3, 1};
        System.out.println(solution.containsDuplicate(nums)); // Output: true

        int[] nums1 = {1, 2, 3, 4};
        System.out.println(solution.containsDuplicate(nums1)); // Output: false
    }
}
