package CSE_504.kdTree;

public class Node {
    Node left = null;
    Node right = null;

    final Point point;

    public Node(Point point) {
        this.point = point;
    }

    public void add(Node point) {
        this.add(point, 0);
    }

    private void add(Node node, int presentDimension) {
        if(node.point.getValueDimension(presentDimension) < point.getValueDimension(presentDimension))
        {
            if(left == null)
                left = node;
            else
                left.add(node,presentDimension + 1);
        } else
        {
            if(right == null)
                right = node;
            else
                right.add(node, presentDimension + 1);
        }

    }

    @Override
    public String toString() {
        return "(" + point +
                ')';
    }
}
