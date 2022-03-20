package CSE_504.kdTree;

import java.util.ArrayList;

public class Point {
    ArrayList<Integer> values;

    public Point(ArrayList<Integer> values) {
        this.values = values;
    }

    int getValueDimension(int depth)
    {
        return values.get(depth % values.size());
    }

    int size()
    {
        return values.size();
    }

    @Override
    public String toString() {
        return "(" + values + ")";
    }
}
