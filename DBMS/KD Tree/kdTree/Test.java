package CSE_504.kdTree;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        KDTree tree = new KDTree(2);

        ArrayList<Integer> point1 = new ArrayList<>();
        ArrayList<Integer> point2 = new ArrayList<>();
        ArrayList<Integer> point3 = new ArrayList<>();
        ArrayList<Integer> point4 = new ArrayList<>();
        ArrayList<Integer> point5 = new ArrayList<>();
        ArrayList<Integer> point6 = new ArrayList<>();
        ArrayList<Integer> point7 = new ArrayList<>();


        point1.add(50);
        point1.add(5);

        point2.add(30);
        point2.add(34);

        point3.add(70);
        point3.add(60);

        point4.add(43);
        point4.add(24);

        point5.add(77);
        point5.add(63);

        point6.add(90);
        point6.add(24);

        point7.add(40);
        point7.add(69);


        tree.addNode(new Node(new Point(point1)));
        tree.addNode(new Node(new Point(point2)));
        tree.addNode(new Node(new Point(point3)));
        tree.addNode(new Node(new Point(point4)));
        tree.addNode(new Node(new Point(point5)));
        tree.addNode(new Node(new Point(point6)));
        tree.addNode(new Node(new Point(point7)));


        System.out.println(tree);

        System.out.println("Searching for (70,60)");

        ArrayList<Integer> searchPoint = new ArrayList<>();
        searchPoint.add(70);
        searchPoint.add(60);

        System.out.println(tree.nearestNeighbor(new Point(searchPoint)));


    }
}
