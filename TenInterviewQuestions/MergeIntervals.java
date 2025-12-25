import java.util.*;

/**
 * Merge Intervals
 * DS: array of int[2] intervals.
 * Algo: sort by start then greedy merge overlaps.
 * Thought: sorting makes potential overlaps adjacent; keep current interval and merge when overlapping.
 * Time: O(n log n) for sort. Space: O(n) for result references.
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][0];
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curr[1]) {           // overlap
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                merged.add(curr);
                curr = intervals[i];
            }
        }
        merged.add(curr);
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] res = mi.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        System.out.println(Arrays.deepToString(res)); // [[1, 6], [8, 10], [15, 18]]
    }
}
