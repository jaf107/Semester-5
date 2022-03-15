package Everything;

public class InternalNode extends Node {
    int maxDegree;
    int minDegree;
    int degree;
    InternalNode leftSibling;
    InternalNode rightSibling;
    String[] keys;
    Node[] childPointers;

    /**
     * Constructor
     * @param m: the max degree of the Everything.InternalNode
     * @param keys: the list of keys that Everything.InternalNode is initialized with
     */
    public InternalNode(int m, String[] keys) {
        this.maxDegree = m-1;
        this.minDegree = (int)Math.ceil((m-1)/2.0);
        this.degree = 0;
        this.keys = keys;
        this.childPointers = new Node[this.maxDegree+1];
    }

    /**
     * Constructor
     * @param m: the max degree of the Everything.InternalNode
     * @param keys: the list of keys that Everything.InternalNode is initialized with
     * @param pointers: the list of pointers that Everything.InternalNode is initialized with
     */
    public InternalNode(int m, String[] keys, Node[] pointers) {
        this.maxDegree = m;
        this.minDegree = (int)Math.ceil(m/2.0);
        this.degree = HelperFunction.linearNullSearch(pointers);
        this.keys = keys;
        this.childPointers = pointers;
    }

    /**
     * This method appends 'pointer' to the end of the childPointers
     * instance variable of the Everything.InternalNode object. The pointer can point to
     * an Everything.InternalNode object or a Everything.LeafNode object since the formal
     * parameter specifies a Everything.Node object.
     * @param pointer: Everything.Node pointer that is to be appended to the
     *                    childPointers list
     */
    public void appendChildPointer(Node pointer) {
        this.childPointers[degree] = pointer;
        this.degree++;
    }

    /**
     * Given a Everything.Node pointer, this method will return the index of where the
     * pointer lies within the childPointers instance variable. If the pointer
     * can't be found, the method returns -1.
     * @param pointer: a Everything.Node pointer that may lie within the childPointers
     *                     instance variable
     * @return the index of 'pointer' within childPointers, or -1 if
     * 'pointer' can't be found
     */
    public int findIndexOfPointer(Node pointer) {
        for (int i = 0; i < childPointers.length; i++) {
            if (childPointers[i] == pointer) { return i; }
        }
        return -1;
    }

    /**
     * Given a pointer to a Everything.Node object and an integer index, this method
     * inserts the pointer at the specified index within the childPointers
     * instance variable. As a result of the insert, some pointers may be
     * shifted to the right of the index.
     * @param pointer: the Everything.Node pointer to be inserted
     * @param index: the index at which the insert is to take place
     */
    public void insertChildPointer(Node pointer, int index) {
        for (int i = degree - 1; i >= index ;i--) {
            childPointers[i + 1] = childPointers[i];
        }
        this.childPointers[index] = pointer;
        this.degree++;
    }

    /**
     * This simple method determines if the Everything.InternalNode is deficient or not.
     * An Everything.InternalNode is deficient when its current degree of children falls
     * below the allowed minimum.
     * @return a boolean indicating whether the Everything.InternalNode is deficient
     * or not
     */
    public boolean isDeficient() {
        return this.degree < this.minDegree;
    }

    /**
     * This simple method determines if the Everything.InternalNode is capable of
     * lending one of its dictionary pairs to a deficient node. An Everything.InternalNode
     * can give away a dictionary pair if its current degree is above the
     * specified minimum.
     * @return a boolean indicating whether or not the Everything.InternalNode has
     * enough dictionary pairs in order to give one away.
     */
    public boolean isLendable() { return this.degree > this.minDegree; }

    /**
     * This simple method determines if the Everything.InternalNode is capable of being
     * merged with. An Everything.InternalNode can be merged with if it has the minimum
     * degree of children.
     * @return a boolean indicating whether or not the Everything.InternalNode can be
     * merged with
     */
    public boolean isMergeable() { return this.degree == this.minDegree; }

    /**
     * This simple method determines if the Everything.InternalNode is considered overfull,
     * i.e. the Everything.InternalNode object's current degree is one more than the
     * specified maximum.
     * @return a boolean indicating if the Everything.InternalNode is overfull
     */
    public boolean isOverfull() {
        return this.degree == maxDegree + 1;
    }

    /**
     * Given a pointer to a Everything.Node object, this method inserts the pointer to
     * the beginning of the childPointers instance variable.
     * @param pointer: the Everything.Node object to be prepended within childPointers
     */
    public void prependChildPointer(Node pointer) {
        for (int i = degree - 1; i >= 0 ;i--) {
            childPointers[i + 1] = childPointers[i];
        }
        this.childPointers[0] = pointer;
        this.degree++;
    }

    /**
     * This method sets keys[index] to null. This method is used within the
     * parent of a merging, deficient Everything.LeafNode.
     * @param index: the location within keys to be set to null
     */
    public void removeKey(int index) { this.keys[index] = null; }

    /**
     * This method sets childPointers[index] to null and additionally
     * decrements the current degree of the Everything.InternalNode.
     * @param index: the location within childPointers to be set to null
     */
    public void removePointer(int index) {
        this.childPointers[index] = null;
        this.degree--;
    }

    /**
     * This method removes 'pointer' from the childPointers instance
     * variable and decrements the current degree of the Everything.InternalNode. The
     * index where the pointer node was assigned is set to null.
     * @param pointer: the Everything.Node pointer to be removed from childPointers
     */
    public void removePointer(Node pointer) {
        for (int i = 0; i < childPointers.length; i++) {
            if (childPointers[i] == pointer) { this.childPointers[i] = null; }
        }
        this.degree--;
    }

}
