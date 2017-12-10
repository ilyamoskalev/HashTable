package hashtable;

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
        table = new Chain[this.capacity];
        this.capacity = capacity;
        size = 0;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = null;
        }
    }

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
                return -1;
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

    public void print() {
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
        System.out.println(description.toString());
    }


    public static void main(String[] args) {
        final ChainHashTable table = new ChainHashTable();
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
