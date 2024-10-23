import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(root, new TreeNode()));

        Map<TreeNode, Integer> map = new HashMap<>();
        while(!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            Deque<Node> q2 = new ArrayDeque<>();
            for(int i = 0; i < size; i++) {
                Node n = q.poll();
                q2.offer(n);
                sum += n.node.val;
                if(n.node.left != null) {
                    q.offer(new Node(n.node.left, n.node));
                }
                if(n.node.right != null) {
                    q.offer(new Node(n.node.right, n.node));
                }
            }
            while(!q2.isEmpty()) {
                Node n2 =  q2.poll();
                TreeNode node2 = n2.node;
                TreeNode p2 = n2.parent;
                if(q2.isEmpty() || q2.peek().parent != p2) {
                    map.put(node2, sum - node2.val);
                }else if( q2.peek().parent == p2) {
                    TreeNode node3 = q2.poll().node;
                    int v = sum - node2.val - node3.val;
                    map.put(node2, v);
                    map.put(node3, v);

                }
            }

        }
        dfs(root, map);
        return root;
    }
    void dfs(TreeNode root, Map<TreeNode, Integer> map) {
        if(root == null) {
            return;
        }


        dfs(root.left, map);
        dfs(root.right, map);
        root.val = map.get(root);
    }
}

class Node {
    TreeNode node;
    TreeNode parent;

    Node(TreeNode node, TreeNode parent) {
        this.node = node;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + node +
                ", parent=" + parent +
                '}';
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}