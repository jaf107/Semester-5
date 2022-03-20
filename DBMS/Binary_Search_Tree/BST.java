package CSE_504.Binary_Search_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    Node root;

    public BST() {
        root = null;
    }

    public BST(Node root) {
        this.root = root;
    }


    private void add(Node data) {
        if(root == null)
            root = data;
        else
            add(this.root,data);
    }

    private void add(Node root, Node node) {
        if(node.data> root.data)
        {
            if(root.right == null)
                root.right = node;
            else
                add(root.right, node);
        }else
        {
            if(root.left == null)
                root.left = node;
            else
                add(root.left, node);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node temp = q.poll();
                if(temp == null)
                {
                    sb.append("0").append("\t");
                }
                else
                {
                    sb.append(temp.data).append("\t");
                    q.add(temp.left);
                    q.add(temp.right);

                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        BST bst = new BST();

        int[] arr = {12,4,1,5,3,23,13,53};

        for (int i:arr) {
            Node q = new Node(i);
            bst.add(q);
        }
        System.out.println(bst.toString());

    }

}
