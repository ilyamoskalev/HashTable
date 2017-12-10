package hashtable;

public class Chain {
    private int key;
    private int value;
    private Chain next;

    Chain(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
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

    public Chain getNext() {
        return next;
    }

    public void setNext(Chain next) {
        this.next = next;
    }
}
