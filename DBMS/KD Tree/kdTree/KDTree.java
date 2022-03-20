package CSE_504.kdTree;

import java.util.LinkedList;
import java.util.Queue;

public class KDTree {
    final int NUM_DIM;
    Node root = null;

    public KDTree(int numberOfDimensions) {
        this.NUM_DIM = numberOfDimensions;
    }

    public KDTree(Node root) {
        this.root = root;
        this.NUM_DIM = root.point.size();
    }

    public void addNode(Node point)
    {
        if(root == null)
            root = point;
        else
            root.add(point);
    }

    Node nearestNeighbor(Point target){
        return nearestNeighbor(root, target, 0);
    }

    private Node nearestNeighbor(Node root, Point target, int depth) {
        if(root == null)
            return null;

        Node next; //= null;
        Node other;// = null;

        if(target.getValueDimension(depth) < root.point.getValueDimension(depth))
        {
            next = root.left;
            other = root.right;
        } else
        {
            next = root.right;
            other = root.left;
        }

        Node temp = nearestNeighbor(next, target, depth + 1);
        Node best = closest(temp, root, target);

        long radiusSquared = distSquared(target, best.point);
        long dist = target.getValueDimension(depth) - root.point.getValueDimension(depth);

        if(radiusSquared >= square(dist)){
            temp = nearestNeighbor(other, target, depth +1);
            best = closest(temp, best, target);
        }

        return best;
    }

    private long square(long dist) {
        return dist * dist;
    }

    private long distSquared(Point target, Point point) {
        long total = 0;
        int numOfDimension = target.values.size();

        for (int i = 0; i < numOfDimension; i++) {
            int dif = Math.abs(target.getValueDimension(i) - point.getValueDimension(i));
            total += Math.pow(dif, 2);
        }
        return total;
    }

    private Node closest(Node no1, Node no2, Point target) {
        if(no1 == null)
            return no2;
        if(no2 == null)
            return no1;

        long d1 = distSquared(no1.point, target);
        long d2 = distSquared(no2.point, target);

        if(d1 < d2)
            return no1;
        return no2;
    }


    @Override
    public String toString() {
        StringBuffer val = new StringBuffer();
        Queue<Node> q = new LinkedList<>();
        q.add(this.root);

        while (!q.isEmpty())
        {
            int size = q.size();
            for(int i =0 ; i < size; i++)
            {
                Node node = q.poll();
                if(node == null)
                {
                    val.append(("null, "));
                }
                else
                {
                    val.append(node.point).append(", ");
                    q.add(node.left);
                    q.add(node.right);
                }
            }
            val.append("\n");
        }
        return val.toString();
    }
}
