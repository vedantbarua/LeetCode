import java.util.*;

/**
 * Right View of Binary Tree
 * DS: binary tree nodes + queue.
 * Algo: BFS level-order, record last node value per level.
 * Thought: processing left-to-right per level means final node is visible from right.
 * Time: O(n). Space: O(n) queue.
 */
public class RightViewTree {
    static class TreeNode { int val; TreeNode left, right; TreeNode(int v){ val = v; } }

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

    public static void main(String[] args) {
        RightViewTree solver = new RightViewTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        System.out.println(solver.rightSideView(root)); // [1, 3, 4]
    }
}
