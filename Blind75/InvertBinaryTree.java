import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // Swap the left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert the left and right subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(2);
        root.right = solution.new TreeNode(3);
        root.left.left = solution.new TreeNode(4);
        root.left.right = solution.new TreeNode(5);

        TreeNode invertedRoot = solution.invertTree(root);

        // Function to print the tree in level order to verify the inversion
        printLevelOrder(invertedRoot);
    }
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
    
}
