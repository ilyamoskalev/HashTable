package hashtable;

@SuppressWarnings("MissortedModifiers")
public class OpenAddressHashTableLP implements HashTable {
    private static final int START_CAPACITY = 8;
    private static final double REHASH = 0.75;
    private static final int HASH_PARAM = 37;
    private int size;
    private int capacity;
    HashTableNode[] table;

    public OpenAddressHashTableLP() {
        capacity = START_CAPACITY;
        table = new HashTableNode[capacity];
        size = 0;
        for (int i = 0; i < capacity; i++)
            table[i] = null;
    }

    @Override
    public Integer search(int key) {
        int hash = (key * HASH_PARAM) % capacity;
        while (table[hash] != null) {
            if (table[hash].getKey() == key) {
                return table[hash].getValue();
            }
            hash = (hash + 1) % capacity;
        }
        return null;
    }

    @Override
    public void add(int key, int value) {
        if (REHASH <= (size * 1.0 / capacity)) {
            rehash();
        }
        int hash = (key * HASH_PARAM) % capacity;
        while (table[hash] != null && !table[hash].equals(DeletedNode.getUniqueDeletedNode())) {
            if (table[hash].getKey() == key) {
                table[hash].setValue(value);
                return;
            }
            hash = (hash + 1) % capacity;
        }
        table[hash] = new HashTableNode(key, value);
        ++size;
    }

    private void rehash() {
        final int newCapacity = capacity * 2;
        final HashTableNode[] newTable = new HashTableNode[newCapacity];
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                int hash = (table[i].getKey() * HASH_PARAM) % newCapacity;
                while (newTable[hash] != null) {
                    hash = (hash + 1) % newCapacity;
                }
                newTable[hash] = new HashTableNode(table[i].getKey(), table[i].getValue());
            }
        }
        capacity = newCapacity;
        table = newTable;
    }

    @Override
    public void delete(int key) {
        int hash = (key * HASH_PARAM) % capacity;
        while (table[hash] != null) {
            if (table[hash].getKey() == key) {
                table[hash] = DeletedNode.getUniqueDeletedNode();
                --size;
                return;
            }
            hash = (hash + 1) % capacity;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer min() {
        if (isEmpty()) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                if (table[i].getKey() <= min) {
                    min = table[i].getKey();
                    result = table[i].getValue();
                }
            }
        }
        return result;
    }

    @Override
    public Integer max() {
        if (isEmpty()) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int result = 0;
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                if (table[i].getKey() >= max) {
                    max = table[i].getKey();
                    result = table[i].getValue();
                }
            }
        }
        return result;
    }

    @Override
    public String print() {
        final StringBuilder description = new StringBuilder("Hash table: [ ");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                description.append("__  ");
            } else if (table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                description.append("D ");
            } else {
                description.append(String.format("%d  ", table[i].getValue()));
            }
        }
        description.append(']');
        return description.toString();
    }
}

