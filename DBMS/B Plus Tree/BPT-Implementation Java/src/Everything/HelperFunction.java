package Everything;

import java.util.Arrays;
import java.util.Comparator;

import static Everything.BPT.m;
import static Everything.BPT.root;

public class HelperFunction {

    /*~~~~~~~~~~~~~~~~ HELPER FUNCTIONS ~~~~~~~~~~~~~~~~*/

    /**
     * This method performs a standard binary search on a sorted
     * Everything.KeyValuePair[] and returns the index of the dictionary pair
     * with target key t if found. Otherwise, this method returns a negative
     * value.
     * @param dps: list of dictionary pairs sorted by key within leaf node
     * @param t: target key value of dictionary pair being searched for
     * @return index of the target value if found, else a negative value
     */
    public static int binarySearch(KeyValuePair[] dps, int numPairs, String t) {
        Comparator<KeyValuePair> c = new Comparator<KeyValuePair>() {
            @Override
            public int compare(KeyValuePair o1, KeyValuePair o2) {
                return o1.english.compareTo(o2.english);
            }
        };
        return Arrays.binarySearch(dps, 0, numPairs, new KeyValuePair(t, ""), c);
    }

    /**
     * This method starts at the root of the B+ tree and traverses down the
     * tree via key comparisons to the corresponding leaf node that holds 'key'
     * within its dictionary.
     * @param key: the unique key that lies within the dictionary of a Everything.LeafNode object
     * @return the Everything.LeafNode object that contains the key within its dictionary
     */
    public static LeafNode findLeafNode(String key) {

        // Initialize keys and index variable
        String[] keys = root.keys;
        int i;

        // Find next node on path to appropriate leaf node
        for (i = 0; i < root.degree - 1; i++) {
            if (key.compareTo(keys[i]) < 0) { break; }
        }

        //2 3  5 6     4

		/* Return node if it is a LeafNode object,
		   otherwise repeat the search function a level down */
        Node child = root.childPointers[i];
        if (child instanceof LeafNode) {
            return (LeafNode)child;
        } else {
            return findLeafNode((InternalNode) child, key);
        }
    }

    public static LeafNode findLeafNode(InternalNode node, String key) {

        // Initialize keys and index variable
        String[] keys = node.keys;
        int i;

        // Find next node on path to appropriate leaf node
        for (i = 0; i < node.degree - 1; i++) {
            if (key.compareTo(keys[i]) < 0) { break; }
        }

		/* Return node if it is a Everything.LeafNode object,
		   otherwise repeat the search function a level down */
        Node childNode = node.childPointers[i];
        if (childNode instanceof LeafNode) {
            return (LeafNode)childNode;
        } else {
            return findLeafNode((InternalNode)childNode, key);
        }
    }

    /**
     * Given a list of pointers to Everything.Node objects, this method returns the index of
     * the pointer that points to the specified 'node' Everything.LeafNode object.
     * @param pointers: a list of pointers to Everything.Node objects
     * @param node: a specific pointer to a Everything.LeafNode
     * @return (int) index of pointer in list of pointers
     */
    public static int findIndexOfPointer(Node[] pointers, LeafNode node) {
        int i;
        for (i = 0; i < pointers.length; i++) {
            if (pointers[i] == node) { break; }
        }
        return i;
    }

    /**
     * This is a simple method that returns the midpoint (or lower bound
     * depending on the context of the method invocation) of the max degree m of
     * the B+ tree.
     * @return (int) midpoint/lower bound
     */
    public static int getMidpoint() {
        return (int)Math.ceil((m + 1) / 2.0) - 1;
    }



    /**
     * This method performs a standard linear search on a sorted
     * Everything.KeyValuePair[] and returns the index of the first null entry found.
     * Otherwise, this method returns a -1. This method is primarily used in
     * place of binarySearch() when the target t = null.
     * @param dps: list of dictionary pairs sorted by key within leaf node
     * @return index of the target value if found, else -1
     */
    public static int linearNullSearch(KeyValuePair[] dps) {
        for (int i = 0; i <  dps.length; i++) {
            if (dps[i] == null) { return i; }
        }
        return -1;
    }

    /**
     * This method performs a standard linear search on a list of Everything.Node[] pointers
     * and returns the index of the first null entry found. Otherwise, this
     * method returns a -1. This method is primarily used in place of
     * binarySearch() when the target t = null.
     * @param pointers: list of Everything.Node[] pointers
     * @return index of the target value if found, else -1
     */
    public static int linearNullSearch(Node[] pointers) {
        for (int i = 0; i <  pointers.length; i++) {
            if (pointers[i] == null) { return i; }
        }
        return -1;
    }


    /**
     * This is a specialized sorting method used upon lists of KeyValuePairs
     * that may contain interspersed null values.
     * @param dictionary: a list of Everything.KeyValuePair objects
     */
    public static void sortDictionary(KeyValuePair[] dictionary) {
        Arrays.sort(dictionary, new Comparator<KeyValuePair>() {
            @Override
            public int compare(KeyValuePair o1, KeyValuePair o2) {
                if (o1 == null && o2 == null) { return 0; }
                if (o1 == null) { return 1; }
                if (o2 == null) { return -1; }
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * This method modifies the Everything.InternalNode 'in' by removing all pointers within
     * the childPointers after the specified split. The method returns the removed
     * pointers in a list of their own to be used when constructing a new
     * Everything.InternalNode sibling.
     * @param in: an Everything.InternalNode whose childPointers will be split
     * @param split: the index at which the split in the childPointers begins
     * @return a Everything.Node[] of the removed pointers
     */
    public static Node[] splitChildPointers(InternalNode in, int split) {

        Node[] pointers = in.childPointers;
        Node[] halfPointers = new Node[m + 1];

        // Copy half of the values into halfPointers while updating original keys
        for (int i = split + 1; i < pointers.length; i++) {
            halfPointers[i - split - 1] = pointers[i];
            in.removePointer(i);
        }

        return halfPointers;
    }

    /**
     * This method splits a single dictionary into two dictionaries where all
     * dictionaries are of equal length, but each of the resulting dictionaries
     * holds half of the original dictionary's non-null values. This method is
     * primarily used when splitting a node within the B+ tree. The dictionary of
     * the specified Everything.LeafNode is modified in place. The method returns the
     * remainder of the KeyValuePairs that are no longer within ln's dictionary.
     * @param ln: list of KeyValuePairs to be split
     * @param split: the index at which the split occurs
     * @return Everything.KeyValuePair[] of the two split dictionaries
     */
    public static KeyValuePair[] splitDictionary(LeafNode ln, int split) {

        KeyValuePair[] dictionary = ln.pairs;

		/* Initialize two dictionaries that each hold half of the original
		   dictionary values */
        KeyValuePair[] halfDict = new KeyValuePair[m];

        // Copy half of the values into halfDict
        for (int i = split; i < dictionary.length; i++) {
            halfDict[i - split] = dictionary[i];
            ln.deleteKeyValuePair(i);
        }

        return halfDict;
    }

    /**
     * When an insertion into the B+ tree causes an overfull node, this method
     * is called to remedy the issue, i.e. to split the overfull node. This method
     * calls the sub-methods of splitKeys() and splitChildPointers() in order to
     * split the overfull node.
     * @param in: an overfull Everything.InternalNode that is to be split
     */
    public static void splitInternalNode(InternalNode in) {

        // Acquire parent
        InternalNode parent = in.parent;

        // Split keys and pointers in half
        int midpoint = getMidpoint();
        String newParentKey = in.keys[midpoint];
        String[] halfKeys = splitKeys(in.keys, midpoint);
        Node[] halfPointers = splitChildPointers(in, midpoint);

        // Change degree of original Everything.InternalNode in
        in.degree = linearNullSearch(in.childPointers);

        // Create new sibling internal node and add half of keys and pointers
        InternalNode sibling = new InternalNode(m, halfKeys, halfPointers);
        for (Node pointer : halfPointers) {
            if (pointer != null) { pointer.parent = sibling; }
        }

        // Make internal nodes siblings of one another
        sibling.rightSibling = in.rightSibling;
        if (sibling.rightSibling != null) {
            sibling.rightSibling.leftSibling = sibling;
        }
        in.rightSibling = sibling;
        sibling.leftSibling = in;

        if (parent == null) {

            // Create new root node and add midpoint key and pointers
            String[] keys = new String[m];
            keys[0] = newParentKey;
            InternalNode newRoot = new InternalNode(m, keys);
            newRoot.appendChildPointer(in);
            newRoot.appendChildPointer(sibling);
            root = newRoot;

            // Add pointers from children to parent
            in.parent = newRoot;
            sibling.parent = newRoot;

        }
        else {
            // Add key to parent
            parent.keys[parent.degree - 1] = newParentKey;
            Arrays.sort(parent.keys, 0, parent.degree);

            // Set up pointer to new sibling
            int pointerIndex = parent.findIndexOfPointer(in) + 1;
            parent.insertChildPointer(sibling, pointerIndex);
            sibling.parent = parent;
        }
    }

    /**
     * This method modifies a list of Integer-typed objects that represent keys
     * by removing half of the keys and returning them in a separate Integer[].
     * This method is used when splitting an Everything.InternalNode object.
     * @param keys: a list of Integer objects
     * @param split: the index where the split is to occur
     * @return Integer[] of removed keys
     */
    public static String[] splitKeys(String[] keys, int split) {

        String[] halfKeys = new String[m];

        // Remove split-indexed value from keys
        keys[split] = null;

        // Copy half of the values into halfKeys while updating original keys
        for (int i = split + 1; i < keys.length; i++) {
            halfKeys[i - split - 1] = keys[i];
            keys[i] = null;
        }

        return halfKeys;
    }


}
