# Ten Interview Questions – Java Explanations and Solutions

Each section includes the chosen data structures, algorithm, high-level thinking, Java solution, time/space complexity, and alternative approaches with trade-offs.

## 1) Merge Intervals
**Data structures & algo:** Array of intervals, custom `Interval` class or int[] pairs; sort by start then greedy merge. We sort so overlapping intervals become adjacent; we maintain a growing merged list and append or merge depending on overlap.

```java
import java.util.*;

class MergeIntervals {
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
}
```

- Time: `O(n log n)` for sort.  
- Space: `O(n)` for output list (in-place aside from output references).
- Alternatives we skipped: interval tree or sweep-line with events (heavier setup), bucketed timeline (needs bounded range); chosen approach is simpler and optimal for typical constraints.

## 2) Longest Palindromic Substring
**Data structures & algo:** Work on string directly; expand-around-center two-pointer. For every center (char or gap), expand while chars match. Captures longest palindrome without extra state.

```java
class LongestPalSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);       // odd
            int len2 = expand(s, i, i + 1);   // even
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return r - l - 1;
    }
}
```

- Time: `O(n^2)` (two expansions per position).  
- Space: `O(1)`.
- Alternatives we skipped: DP `O(n^2)` time/space (more memory), Manacher’s `O(n)` time (complex to implement and easy to bug). Center expansion balances simplicity and performance.

## 3) Top K Frequent Elements
**Data structures & algo:** HashMap for frequencies, min-heap of size `k` storing (freq, value). Keep heap trimmed to k highest frequencies.

```java
import java.util.*;

class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [freq, val]
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            minHeap.offer(new int[]{e.getValue(), e.getKey()});
            if (minHeap.size() > k) minHeap.poll();
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) res[i] = minHeap.poll()[1];
        return res;
    }
}
```

- Time: `O(n log k)`; heap ops dominate.  
- Space: `O(n)` for counts + heap.
- Alternatives we skipped: bucket sort `O(n)` time/space (fast but needs bucket array sized by max frequency), full sort of entries `O(n log n)` (slower), quickselect on frequencies `O(n)` average but more code/edge handling. Heap keeps code small and performant.

## 4) Number of Islands
**Data structures & algo:** Grid char[][], boolean visited (or mutate grid). DFS (recursive or stack) to mark connected land. Treat grid as implicit graph; explore each unvisited land to count islands.

```java
class NumberOfIslands {
    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        boolean[][] seen = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1' && !seen[r][c]) {
                    dfs(grid, seen, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] g, boolean[][] seen, int r, int c) {
        int m = g.length, n = g[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n || g[r][c] != '1' || seen[r][c]) return;
        seen[r][c] = true;
        for (int[] d : DIRS) dfs(g, seen, r + d[0], c + d[1]);
    }
}
```

- Time: `O(mn)` visiting each cell once.  
- Space: `O(mn)` visited array; recursion adds `O(mn)` in worst case stack.
- Alternatives we skipped: Union-Find (clean iterative but adds parent array and path compression code), BFS queue (similar complexity, iterative), in-place marking without visited (saves space but mutates input). DFS + visited is clear and side-effect free.

## 5) Right View of Binary Tree
**Data structures & algo:** Binary tree; BFS level-order capturing last node per level. Queue ensures we see nodes left-to-right per level; the last polled is visible from the right.

```java
import java.util.*;

class RightViewTree {
    static class TreeNode { int val; TreeNode left, right; TreeNode(int v){val=v;} }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                if (i == size - 1) res.add(node.val); // last in this level
            }
        }
        return res;
    }
}
```

- Time: `O(n)`.  
- Space: `O(n)` queue at widest level.
- Alternatives we skipped: DFS pre-order (right-first) with depth tracking `O(h)` stack (clean and recursive), reverse level-order using deque (extra work). BFS is iterative and easy to reason about.

## 6) Kth Smallest Element in BST
**Data structures & algo:** BST + stack; iterative inorder traversal yields sorted order. Stop after k pops.

```java
import java.util.*;

class KthSmallestBST {
    static class TreeNode { int val; TreeNode left, right; TreeNode(int v){val=v;} }

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }
        throw new IllegalArgumentException("k out of bounds");
    }
}
```

- Time: `O(h + k)` where `h` is tree height (we traverse until kth).  
- Space: `O(h)` stack.
- Alternatives we skipped: maintaining subtree sizes or order-statistic tree (faster repeated queries but requires augmented nodes), storing inorder list `O(n)` memory, using max-heap of size k `O(n log k)` (slower). Iterative inorder is simplest and optimal for one query.

## 7) LRU Cache
**Data structures & algo:** Doubly linked list (most-recently used at head) + HashMap from key to node. On `get`, move node to head. On `put`, insert/move node, evict tail when over capacity. Provides `O(1)` for both operations.

```java
import java.util.*;

class LRUCache {
    private static class Node {
        int key, val;
        Node prev, next;
        Node(int k, int v){ key=k; val=v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0,0); // pseudo-head
    private final Node tail = new Node(0,0); // pseudo-tail

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail; tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            Node fresh = new Node(key, value);
            map.put(key, fresh);
            addAfterHead(fresh);
            if (map.size() > capacity) {
                Node lru = popTail();
                map.remove(lru.key);
            }
        }
    }

    private void moveToHead(Node node) {
        remove(node);
        addAfterHead(node);
    }
    private void addAfterHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private Node popTail() {
        Node lru = tail.prev;
        remove(lru);
        return lru;
    }
}
```

- Time: `O(1)` for `get` and `put`.  
- Space: `O(capacity)` for map + nodes.
- Alternatives we skipped: Java `LinkedHashMap` (simpler but hides mechanics), ordered map/tree (log time), array-based ring buffer (hard to move arbitrary nodes). Custom list + map gives tight `O(1)` guarantees.

## 8) Course Schedule (Cycle Detection)
**Data structures & algo:** Directed graph adjacency list; Kahn’s topological sort (BFS). Compute indegrees, push zero-indegree nodes, pop and decrement neighbors. If all nodes processed, no cycle.

```java
import java.util.*;

class CourseSchedule {
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
}
```

- Time: `O(V + E)` for vertices and edges.  
- Space: `O(V + E)` adjacency + indegree + queue.
- Alternatives we skipped: DFS cycle detection/topological ordering (also `O(V+E)` but recursion depth risk), using edge-removal simulation (similar to Kahn but heavier). BFS topological sort is iterative and clear.

## 9) Find Median from Data Stream
**Data structures & algo:** Two heaps: max-heap for lower half, min-heap for upper half. Keep sizes balanced (difference <= 1) and all lower <= upper. Median is top(s) depending on size.

```java
import java.util.*;

class MedianFinder {
    private PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    private PriorityQueue<Integer> high = new PriorityQueue<>(); // min-heap

    public void addNum(int num) {
        if (low.isEmpty() || num <= low.peek()) low.offer(num);
        else high.offer(num);
        // balance
        if (low.size() > high.size() + 1) high.offer(low.poll());
        else if (high.size() > low.size()) low.offer(high.poll());
    }

    public double findMedian() {
        if (low.size() == high.size()) return (low.peek() + high.peek()) / 2.0;
        return low.peek();
    }
}
```

- Time: `O(log n)` per insertion; `O(1)` median query.  
- Space: `O(n)` for stored numbers.
- Alternatives we skipped: balanced BST / order-statistic tree (supports deletions but more code), maintaining sorted list (insertion `O(n)`), two arrays with periodic rebalance (complex). Two-heaps is standard and minimal.

## 10) 3Sum
**Data structures & algo:** Array sorted ascending; two-pointer scan for each fixed index. Sorting enables linear search for complements while skipping duplicates to ensure uniqueness.

```java
import java.util.*;

class ThreeSum {
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
}
```

- Time: `O(n^2)`; dominated by two-pointer sweeps.  
- Space: `O(1)` extra beyond output (sorting in-place).
- Alternatives we skipped: hash-set per anchor `O(n^2)` but uses extra space and duplicate handling; brute force `O(n^3)` too slow; using counting + combinatorics works only for bounded ranges. Sorted two-pointer is clean and efficient.
