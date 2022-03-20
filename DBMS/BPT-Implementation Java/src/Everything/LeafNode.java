package Everything;

import java.util.Arrays;

import static Everything.HelperFunction.linearNullSearch;

public class LeafNode extends Node {
    int maxNumPairs;
    int minNumPairs;
    int numPairs;
    LeafNode leftSibling;
    LeafNode rightSibling;
    KeyValuePair[] pairs;

    /**
     * Constructor
     * @param m: order of B+ tree that is used to calculate maxNumPairs and
     *           minNumPairs
     * @param dp: first dictionary pair insert into new node
     */
    public LeafNode(int m, KeyValuePair dp) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.pairs = new KeyValuePair[m];
        this.numPairs = 0;
        this.insert(dp);
    }

    /**
     * Constructor
     * @param dps: list of Everything.KeyValuePair objects to be immediately inserted
     *             into new Everything.LeafNode object
     * @param m: order of B+ tree that is used to calculate maxNumPairs and
     * 		     minNumPairs
     * @param parent: parent of newly created child Everything.LeafNode
     */
    public LeafNode(int m, KeyValuePair[] dps, InternalNode parent) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.pairs = dps;
        this.numPairs = linearNullSearch(dps);
        this.parent = parent;
    }

    /**
     * Given an index, this method sets the dictionary pair at that index
     * within the dictionary to null.
     * @param index: the location within the dictionary to be set to null
     */
    public void deleteKeyValuePair(int index) {

        // Delete dictionary pair from leaf
        this.pairs[index] = null;

        // Decrement numPairs
        numPairs--;
    }

    /**
     * This method attempts to insert a dictionary pair within the dictionary
     * of the Everything.LeafNode object. If it succeeds, numPairs increments, the
     * dictionary is sorted, and the boolean true is returned. If the method
     * fails, the boolean false is returned.
     * @param dp: the dictionary pair to be inserted
     * @return a boolean indicating whether or not the insert was successful
     */
    public boolean insert(KeyValuePair dp) {
        if (this.isFull()) {
            /* Flow of execution goes here when numPairs == maxNumPairs */
            return false;
        }
        else {

            // Insert dictionary pair, increment numPairs, sort dictionary
            this.pairs[numPairs] = dp;
            numPairs++;
            Arrays.sort(this.pairs, 0, numPairs);

            return true;
        }
    }

    /**
     * This simple method determines if the Everything.LeafNode is deficient, i.e.
     * the numPairs within the Everything.LeafNode object is below minNumPairs.
     * @return a boolean indicating whether or not the Everything.LeafNode is deficient
     */
    public boolean isDeficient() { return numPairs < minNumPairs; }

    /**
     * This simple method determines if the Everything.LeafNode is full, i.e. the
     * numPairs within the Everything.LeafNode is equal to the maximum number of pairs.
     * @return a boolean indicating whether or not the Everything.LeafNode is full
     */
    public boolean isFull() { return numPairs == maxNumPairs; }

    /**
     * This simple method determines if the Everything.LeafNode object is capable of
     * lending a dictionary pair to a deficient leaf node. The Everything.LeafNode
     * object can lend a dictionary pair if its numPairs is greater than
     * the minimum number of pairs it can hold.
     * @return a boolean indicating whether or not the Everything.LeafNode object can
     * give a dictionary pair to a deficient leaf node
     */
    public boolean isLendable() { return numPairs > minNumPairs; }

    /**
     * This simple method determines if the Everything.LeafNode object is capable of
     * being merged with, which occurs when the number of pairs within the
     * Everything.LeafNode object is equal to the minimum number of pairs it can hold.
     * @return a boolean indicating whether or not the Everything.LeafNode object can
     * be merged with
     */
    public boolean isMergeable() {
        return numPairs == minNumPairs;
    }

}
