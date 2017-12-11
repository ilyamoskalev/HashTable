package hashtable;

public final class DeletedNode extends HashTableNode {
    private static DeletedNode node = null;

    private DeletedNode() {
        super(-1, -1);
    }

    public static DeletedNode getUniqueDeletedNode() {
        if (node == null)
            node = new DeletedNode();
        return node;
    }
}

