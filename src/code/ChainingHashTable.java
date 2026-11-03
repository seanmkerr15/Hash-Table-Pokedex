package code;
// Adapted from the chaining code supplied with this assignment.
// Keys and values must be non-null. Capacity stays fixed for this lab.
public class ChainingHashTable<K, V> {
    private ChainingHashTableItem<K, V>[] table;

    public ChainingHashTable() { this(11); }

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        table = (ChainingHashTableItem<K, V>[]) new ChainingHashTableItem<?, ?>[initialCapacity];
    }

    // Returns a non-negative hash code for the specified key.
    private int hash(K key) {
        long keyHash = key.hashCode();
        // Java's hashCode() method may return a negative number.
        if (keyHash < 0) {
            keyHash += 2147483648L;
        }
        return (int) keyHash;
    }

    // Inserts a key/value pair, or replaces the value for an existing key.
    public boolean insert(K key, V value) {
        int bucketIndex = hash(key) % table.length;
        ChainingHashTableItem<K, V> currentItem = table[bucketIndex];
        ChainingHashTableItem<K, V> previousItem = null;
        while (currentItem != null) {
            if (key.equals(currentItem.key)) {
                currentItem.value = value;
                return true;
            }
            previousItem = currentItem;
            currentItem = currentItem.next;
        }
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new ChainingHashTableItem<>(key, value);
        } else {
            previousItem.next = new ChainingHashTableItem<>(key, value);
        }
        return true;
    }

    // Returns the value for a key, or null when the key is missing.
    public V get(K key) {
        int bucketIndex = hash(key) % table.length;
        ChainingHashTableItem<K, V> item = table[bucketIndex];
        while (item != null) {
            if (key.equals(item.key)) {
                return item.value;
            }
            item = item.next;
        }
        return null;
    }

    // Removes a key/value pair. Returns false when the key is missing.
    public boolean remove(K key) {
        int bucketIndex = hash(key) % table.length;
        ChainingHashTableItem<K, V> currentItem = table[bucketIndex];
        ChainingHashTableItem<K, V> previousItem = null;
        while (currentItem != null) {
            if (key.equals(currentItem.key)) {
                if (previousItem == null) {
                    table[bucketIndex] = currentItem.next;
                } else {
                    previousItem.next = currentItem.next;
                }
                return true;
            }
            previousItem = currentItem;
            currentItem = currentItem.next;
        }
        return false;
    }

    // Print every bucket index and the keys in its chain, in order.
    public void printTable() {
        // TODO 4: Traverse each bucket and print its keys.
        System.out.println("TODO: printTable");
    }
}
