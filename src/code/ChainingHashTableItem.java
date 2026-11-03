package code;
// A node in one bucket's linked list, matching the textbook structure.
public class ChainingHashTableItem<K, V> {
    public K key;
    public V value;
    public ChainingHashTableItem<K, V> next;

    public ChainingHashTableItem(K itemKey, V itemValue) {
        key = itemKey;
        value = itemValue;
        next = null;
    }
}
