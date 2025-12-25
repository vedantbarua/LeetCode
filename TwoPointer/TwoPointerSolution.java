public class TwoPointerSolution {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int target = 32;
        int[] pair = findPair(arr, target);
        if (pair == null) {
            System.out.println("No pair found");
        } else {
            System.out.println("Pair found: (" + pair[0] + ", " + pair[1] + ")");
        }
    }

    // Two-pointer on sorted array: move ends inward based on sum comparison
    public static int[] findPair(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) return new int[]{arr[left], arr[right]};
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
