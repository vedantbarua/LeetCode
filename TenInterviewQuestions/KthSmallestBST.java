import java.util.*;

/**
 * Kth Smallest in BST
 * DS: BST + stack.
 * Algo: iterative inorder traversal; stop at kth node.
 * Thought: inorder yields sorted order in BST; stacking left chain simulates recursion.
 * Time: O(h + k). Space: O(h) stack.
 */
public class KthSmallestBST {
    static class TreeNode { int val; TreeNode left, right; TreeNode(int v){ val = v; } }

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

    public static void main(String[] args) {
        KthSmallestBST solver = new KthSmallestBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        System.out.println(solver.kthSmallest(root, 3)); // 3
    }
}
