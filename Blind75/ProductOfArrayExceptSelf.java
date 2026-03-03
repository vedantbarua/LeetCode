package Blind75;

public class ProductOfArrayExceptSelf {
    // Question: Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i]. 
    // The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer. You must write an algorithm that runs in O(n) time and without using the division operation.
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;

        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return res;
        
    }
    
    public static void main(String[] args) {
        ProductOfArrayExceptSelf paes = new ProductOfArrayExceptSelf();
        int[] nums = {1, 2, 3, 4};
        int[] result = paes.productExceptSelf(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
