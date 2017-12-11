package hashtable;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("MissortedModifiers")
public class OpenAddressHashTableDH {
    private static final int START_CAPACITY = 8;
    private static final double REHASH = 0.75;
    private int size;
    private int capacity;
    HashTableNode[] table;

    public OpenAddressHashTableDH() {
        capacity = START_CAPACITY;
        table = new HashTableNode[capacity];
        size = 0;
        for (int i = 0; i < capacity; i++)
            table[i] = null;
    }

    @Nullable
    public Integer search(int key) {
        int hash = hashFunc1(key);
        final int stepSize = hashFunc2(key);
        while (table[hash] != null) {
            if (table[hash].getKey() == key) {
                return table[hash].getValue();
            }
            hash += stepSize;
            hash %= capacity;
        }
        return null;
    }

    public void add(int key, int value) {
        if (REHASH <= (size * 1.0 / capacity)) {
            rehash();
        }
        int hash = hashFunc1(key);
        final int stepSize = hashFunc2(key);
        while (table[hash] != null && table[hash] != DeletedNode.getUniqueDeletedNode()) {
            if (table[hash].getKey() == key) {
                table[hash].setValue(value);
                return;
            }
            hash += stepSize;
            hash %= capacity;
        }
        table[hash] = new HashTableNode(key, value);
        ++size;
    }

    private void rehash() {
        final int newCapacity = capacity * 2;
        final HashTableNode[] newTable = new HashTableNode[newCapacity];
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && table[i] != DeletedNode.getUniqueDeletedNode()) {
                int hash = (table[i].getKey() % newCapacity);
                final int stepSize = 7 - table[i].getKey() % 7;
                while (newTable[hash] != null) {
                    hash += stepSize;
                    hash %= capacity;
                }
                newTable[hash] = new HashTableNode(table[i].getKey(), table[i].getValue());
            }
        }
        capacity = newCapacity;
        table = newTable;
    }

    public void delete(int key) {
        int hash = hashFunc1(key);
        final int stepSize = hashFunc2(key);
        while (table[hash] != null) {
            if (table[hash].getKey() == key) {
                table[hash] = DeletedNode.getUniqueDeletedNode();
                --size;
                return;
            }
            hash += stepSize;
            hash %= capacity;
        }
    }

    public int hashFunc1(int key) {
        return (key * 47) % capacity;
    }

    public int hashFunc2(int key) {
        int hash = ((key * 97) % (capacity - 1)) ;
        if(hash % 2 == 0){
            ++hash;
        }
        return hash;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Nullable
    public Integer min() {
        if (isEmpty()) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && table[i] != DeletedNode.getUniqueDeletedNode()) {
                if (table[i].getValue() < min) {
                    min = table[i].getValue();
                }
            }
        }
        return min;
    }

    @Nullable
    public Integer max() {
        if (isEmpty()) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && table[i] != DeletedNode.getUniqueDeletedNode()) {
                if (table[i].getValue() > max) {
                    max = table[i].getValue();
                }
            }
        }
        return max;
    }

    public String print() {
        final StringBuilder description = new StringBuilder("Hash table: [ ");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                description.append("__  ");
            } else if (table[i] == DeletedNode.getUniqueDeletedNode()) {
                description.append("D ");
            } else {
                description.append(String.format("%d  ", table[i].getValue()));
            }
        }
        description.append(']');
        return description.toString();
    }

}
