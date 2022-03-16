package Everything;

import java.util.Arrays;

import static Everything.HelperFunction.*;

public class BPT {

    public static int m;
    public static InternalNode root;
    public static LeafNode firstLeaf;


    public BPT(int m) {
        this.m = m;
        this.root = null;
    }


    /**
     * This is a simple method that determines if the B+ tree is empty or not.
     * @return a boolean indicating if the B+ tree is empty or not
     */

    public static boolean isEmpty() {
        return firstLeaf == null;
    }


    /**
     * Given an integer key and floating point value, this method inserts a
     * dictionary pair accordingly into the B+ tree.
     * @param key: an integer key to be used in the dictionary pair
     * @param value: a floating point number to be used in the dictionary pair
     */
    public void insert(String key, String value){
        if (isEmpty()) {

            /* Flow of execution goes here only when first insert takes place */

            // Create leaf node as first node in B plus tree (root is null)
            // Set as first leaf node (can be used later for in-order leaf traversal)
            firstLeaf = new LeafNode(this.m, new KeyValuePair(key, value));

        }
        else {
            // Find leaf node to insert into
            LeafNode ln = (root == null) ? firstLeaf : findLeafNode(key);

            // Insert into leaf node fails if node becomes overfull
            if (!ln.insert(new KeyValuePair(key, value))) {

                // Sort all the dictionary pairs with the included pair to be inserted
                ln.pairs[ln.numPairs] = new KeyValuePair(key, value);
                ln.numPairs++;
                sortDictionary(ln.pairs);

                // Split the sorted pairs into two halves
                int midpoint = getMidpoint();
                KeyValuePair[] halfDict = splitDictionary(ln, midpoint);

                if (ln.parent == null) {

                    /* Flow of execution goes here when there is 1 node in tree */

                    // Create internal node to serve as parent, use dictionary midpoint key
                    String[] parent_keys = new String[m];
                    parent_keys[0] = halfDict[0].english;
                    InternalNode parent = new InternalNode(m, parent_keys);
                    ln.parent = parent;
                    parent.appendChildPointer(ln);

                }
                else {
                    /* Flow of execution goes here when parent exists */
                    // Add new key to parent for proper indexing
                    String newParentKey = halfDict[0].english;
                    ln.parent.keys[ln.parent.degree - 1] = newParentKey;
                    Arrays.sort(ln.parent.keys, 0, ln.parent.degree);
                }

                // Create new LeafNode that holds the other half
                LeafNode newLeafNode = new LeafNode(this.m, halfDict, ln.parent);

                // Update child pointers of parent node
                int pointerIndex = ln.parent.findIndexOfPointer(ln) + 1;
                ln.parent.insertChildPointer(newLeafNode, pointerIndex);

                // Make leaf nodes siblings of one another
                newLeafNode.rightSibling = ln.rightSibling;/////// a->ln->nln->b
                if (newLeafNode.rightSibling != null) {
                    newLeafNode.rightSibling.leftSibling = newLeafNode;
                }
                ln.rightSibling = newLeafNode;
                newLeafNode.leftSibling = ln;

                if (root == null) {
                    // Set the root of B+ tree to be the parent
                    root = ln.parent;

                }
                else {
					/* If parent is overfull, repeat the process up the tree,
			   		   until no deficiencies are found */
                    InternalNode in = ln.parent;
                    while (in != null) {
                        if (in.isOverfull()) {
                            splitInternalNode(in);
                        } else {
                            break;
                        }
                        in = in.parent;
                    }
                }
            }
        }
    }

    /**
     * Given a key, this method returns the value associated with the key
     * within a dictionary pair that exists inside the B+ tree.
     * @param key: the key to be searched within the B+ tree
     * @return the floating point value associated with the key within the B+ tree
     */
    public String search(String key) {

        // If B+ tree is completely empty, simply return null
        if (isEmpty()) { return null; }

        // Find leaf node that holds the dictionary key
        LeafNode ln = (this.root == null) ? this.firstLeaf : findLeafNode(key);

        // Perform binary search to find index of key within dictionary
        KeyValuePair[] dps = ln.pairs;
        int index = binarySearch(dps, ln.numPairs, key);

        // If index negative, the key doesn't exist in B+ tree
        if (index < 0) {
            return null;
        } else {
            return dps[index].bengali;
        }
    }

//    /**
//     * This method traverses the doubly linked list of the B+ tree and records
//     * all values whose associated keys are within the range specified by
//     * lowerBound and upperBound.
//     * @param lowerBound: (int) the lower bound of the range
//     * @param upperBound: (int) the upper bound of the range
//     * @return an ArrayList<Double> that holds all values of dictionary pairs
//     * whose keys are within the specified range
//     */
//    public ArrayList<Double> search(int lowerBound, int upperBound) {
//
//        // Instantiate Double array to hold values
//        ArrayList<Double> values = new ArrayList<Double>();
//
//        // Iterate through the doubly linked list of leaves
//        LeafNode currNode = this.firstLeaf;
//        while (currNode != null) {
//
//            // Iterate through the dictionary of each node
//            KeyValuePair dps[] = currNode.dictionary;
//            for (KeyValuePair dp : dps) {
//
//				/* Stop searching the dictionary once a null value is encountered
//				   as this the indicates the end of non-null values */
//                if (dp == null) { break; }
//
//                // Include value if its key fits within the provided range
//                if (lowerBound <= dp.key && dp.key <= upperBound) {
//                    values.add(dp.value);
//                }
//            }
//
//			/* Update the current node to be the right sibling,
//			   leaf traversal is from left to right */
//            currNode = currNode.rightSibling;
//
//        }
//
//        return values;
//    }


}
