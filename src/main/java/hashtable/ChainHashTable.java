package hashtable;

import org.jetbrains.annotations.Nullable;

public class ChainHashTable implements HashTable {
    private static final int START_CAPACITY = 16;
    private Chain[] table;
    private int capacity;
    private int size;

    public ChainHashTable() {
        capacity = START_CAPACITY;
        table = new Chain[capacity];
        size = 0;
        for (int i = 0; i < capacity; ++i) {
            table[i] = null;
        }
    }

    public ChainHashTable(int capacity) {
        table = new Chain[capacity];
        this.capacity = capacity;
        size = 0;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = null;
        }
    }

    @Override
    public void add(int key, int value) {
        final int hash = (key % capacity);
        if (table[hash] == null) {
            table[hash] = new Chain(key, value);
        } else {
            Chain entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                entry = entry.getNext();
            }
            if (entry.getKey() == key) {
                entry.setValue(value);
            } else {
                entry.setNext(new Chain(key, value));
            }
        }
    }

    @Override
    public void delete(int key) {
        final int hash = (key % capacity);
        if (table[hash] != null) {
            Chain prevEntry = null;
            Chain entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                prevEntry = entry;
                entry = entry.getNext();
            }
            if (entry.getKey() == key) {
                if (prevEntry == null) {
                    table[hash] = entry.getNext();
                } else {
                    prevEntry.setNext(entry.getNext());
                }
            }
        }
    }

    @Override
    public Integer search(int key) {
        final int hash = (key % capacity);
        if (table[hash] == null)
            return null;
        else {
            Chain entry = table[hash];
            while (entry != null && entry.getKey() != key) {
                entry = entry.getNext();
            }
            if (entry == null) {
                return null;
            } else {
                return entry.getValue();
            }
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < capacity; ++i) {
            final Chain entry = table[i];
            if (entry != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer min() {
        if (isEmpty()) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < capacity; ++i) {
            Chain entry = table[i];
            if (entry != null) {
                do {
                    if (entry.getValue() < min) {
                        min = entry.getValue();
                    }
                    entry = entry.getNext();
                } while (entry != null);
            }
        }
        return min;
    }

    @Override
    public Integer max() {
        if (isEmpty()) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < capacity; ++i) {
            Chain entry = table[i];
            if (entry != null) {
                do {
                    if (entry.getValue() > max) {
                        max = entry.getValue();
                    }
                    entry = entry.getNext();
                } while (entry != null);
            }
        }
        return max;
    }

    @Override
    public String print() {
        final StringBuilder description = new StringBuilder("Hash table: [ ");
        for (int i = 0; i < capacity; i++) {
            description.append("{  ");
            if (table[i] != null) {
                Chain entry = table[i];
                do {
                    description.append(String.format("%d  ", entry.getValue()));
                    entry = entry.getNext();
                } while (entry != null);
            }
            description.append("} ");
        }
        description.append(']');
        return description.toString();
    }

}
