import java.util.*;

/**
 * Course Schedule (cycle detection)
 * DS: adjacency list + indegree array + queue.
 * Algo: Kahn's topological sort; process zero-indegree nodes and remove outgoing edges.
 * Thought: if all courses processed, no cycle; else cycle exists.
 * Time: O(V + E). Space: O(V + E).
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] p : prerequisites) {
            int course = p[0], pre = p[1];
            adj.get(pre).add(course);
            indeg[course]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) if (indeg[i] == 0) q.offer(i);
        int taken = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            taken++;
            for (int nei : adj.get(cur)) {
                if (--indeg[nei] == 0) q.offer(nei);
            }
        }
        return taken == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule solver = new CourseSchedule();
        System.out.println(solver.canFinish(2, new int[][]{{1,0}})); // true
        System.out.println(solver.canFinish(2, new int[][]{{1,0},{0,1}})); // false
    }
}
