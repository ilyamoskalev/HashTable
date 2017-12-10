package hashtable;

import org.jetbrains.annotations.Nullable;

public class OpenAddressHashTableDH {
    private static final int START_CAPACITY = 8;
    private static final double REHASH = 0.75;
    private int size;
    private int capacity;
    HashTableNode[] table;

    OpenAddressHashTableDH() {
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
            System.out.println("rehash");
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
                final int stepSize = 6 - table[i].getKey() % 6;
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
            hash += stepSize; // add the step
            hash %= capacity; // for wraparound
        }
    }

    public int hashFunc1(int key) {
        return key % capacity;
    }

    public int hashFunc2(int key) {
        return 6 - key % 6;
    }

    public void print() {
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
        System.out.println(description.toString());
    }

    public static void main(String[] args) {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        table.add(1, 11);
        table.add(2, 22);
        table.add(0, 33);
        table.add(17, 44);
        table.add(1, 88);
        table.add(9, 99);
        System.out.println(table.search(9));
        table.print();
        table.add(5, 5);
        table.add(6, 6);
        table.print();
    }

}
