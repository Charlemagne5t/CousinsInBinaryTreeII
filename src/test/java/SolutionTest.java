import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        TreeNode root = new TreeNode(49);
        root.right = new TreeNode(35);
        root.left = new TreeNode(40);
        root.left.left = new TreeNode(42);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(50);
        root.left.right.right = new TreeNode(44);
        root.left.right.right.right = new TreeNode(27);
        root.left.right.right.right.left = new TreeNode(21);

        TreeNode res = new Solution().replaceValueInTree(root);

    }

    void dfs(TreeNode node) {
        if(node == null) {
            return;
        }

        dfs(node.left);
        System.out.println(node.val);
        dfs(node.right);
    }
}
