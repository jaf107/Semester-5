package Everything;

public class KeyValuePair  implements Comparable<KeyValuePair> {
    public String english;
    public String bengali;

    public KeyValuePair(String english, String bengali){
        this.english=english;
        this.bengali=bengali;
    }


    /**
     * This is a method that allows comparisons to take place between
     * DictionaryPair objects in order to sort them later on
     * @param o the one to compare
     * @return negative if first one is smaller 0 if equal and + if bigger
     */

    public int compareTo(KeyValuePair o) {
        return english.compareTo(o.english);
    }
}
