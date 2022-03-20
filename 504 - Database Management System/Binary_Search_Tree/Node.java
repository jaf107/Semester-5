package CSE_504.Binary_Search_Tree;

public class Node {
    Node left ;
    Node right ;
    int data;

    Node()
    {
        left = null;
        right = null;
        data = -1;
    }

    public Node(int data) {
        super();
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + left +
                ", right=" + right +
                ", data=" + data +
                '}';
    }
}
