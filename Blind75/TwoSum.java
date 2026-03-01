package Blind75;

import java.util.HashMap;

public class TwoSum {
    // Question: Given an array of integers nums and an integer target, return
    // indices of the two numbers such that they add up to target. You may assume
    // that each input would have exactly one solution, and you may not use the
    // same element twice. You can return the answer in any order.
    
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> prevMap = new HashMap<>(); 
        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            if(prevMap.containsKey(diff)){
                return new int[] {prevMap.get(diff), i};
            }
            prevMap.put(nums[i], i);
        }
        return new int[]{};
    }
    
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]); // Output: Indices: 0, 1
}
}
