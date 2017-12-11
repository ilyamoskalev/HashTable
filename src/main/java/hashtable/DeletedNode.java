package hashtable;

/**
 * Created by ilamoskalev on 10.12.2017.
 */
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

