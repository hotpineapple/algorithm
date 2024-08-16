/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        return dfs(root, key);
    }

    private TreeNode dfs(TreeNode node, int key) {
        if (node == null)
            return null;
        else if (node.val > key)
            return new TreeNode(node.val, dfs(node.left, key), node.right);
        else if (node.val < key)
            return new TreeNode(node.val, node.left, dfs(node.right, key));
        else {
            if (node.right == null && node.left == null) {
                return null;
            } else if (node.right == null) {
                return node.left;
            } else if (node.left == null) {
                return node.right;
            } else {
                // find min value of right subtree
                TreeNode leftSubTree = new TreeNode(node.left.val, node.left.left, node.left.right);
                TreeNode parent = node;
                TreeNode child = node.right;

                if (child.left == null) {
                    return new TreeNode(child.val, leftSubTree, node.right.right);
                }
                while (true) {
                    if (child.left == null) {
                        parent.left = child.right;
                        return new TreeNode(child.val, leftSubTree, node.right);
                    }
                    parent = child;
                    child = child.left;
                }
            }
        }
    }
}