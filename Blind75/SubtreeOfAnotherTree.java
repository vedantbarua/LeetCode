public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
        SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();
        
        TreeNode s = solution.new TreeNode(3);
        s.left = solution.new TreeNode(4);
        s.right = solution.new TreeNode(5);
        s.left.left = solution.new TreeNode(1);
        s.left.right = solution.new TreeNode(2);
        
        TreeNode t = solution.new TreeNode(4);
        t.left = solution.new TreeNode(1);
        t.right = solution.new TreeNode(2);
        
        boolean result = solution.isSubtree(s, t);
        System.out.println(result); // Output: true
    }
    
}
