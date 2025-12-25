class TwoPointerSolutionOldApproach {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int target = 5;
        int[] pair = findPairWithHash(arr, target);
        if (pair == null) {
            System.out.println("No pair found");
        } else {
            System.out.println("Pair found: (" + pair[0] + ", " + pair[1] + ")");
        }
    }

    // Hash-set approach works on unsorted arrays but uses extra space.
    public static int[] findPairWithHash(int[] arr, int target) {
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) return new int[]{complement, num};
            seen.add(num);
        }
        return null;
    }
}
