package hashtable;

public class HashTableNode {
    private int key;
    private int value;

    HashTableNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final HashTableNode other = (HashTableNode) object;

        return key == other.key && value == other.value;
    }
}
