public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    
    private boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();
        
        TreeNode root1 = solution.new TreeNode(2);
        root1.left = solution.new TreeNode(1);
        root1.right = solution.new TreeNode(3);
        
        System.out.println(solution.isValidBST(root1)); // Output: true
        
        TreeNode root2 = solution.new TreeNode(5);
        root2.left = solution.new TreeNode(1);
        root2.right = solution.new TreeNode(4);
        root2.right.left = solution.new TreeNode(3);
        root2.right.right = solution.new TreeNode(6);
        
        System.out.println(solution.isValidBST(root2)); // Output: false
    }
    
}
