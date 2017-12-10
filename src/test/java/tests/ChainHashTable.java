package tests;

import org.junit.Assert;
import org.junit.Test;

public class ChainHashTable {
    @Test
    public void testSimpleAddAndSearch() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable();
        table.add(1, 3);
        final Integer value = 3;
        Assert.assertEquals(table.search(1), value);
    }

    @Test
    public void testNoKeySearch() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable();
        Assert.assertNull(table.search(1));
    }

    @Test
    public void testMinAndMax() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable();
        final int max = 12345;
        table.add(10, max);
        final int min = -12345;
        table.add(20, -12345);
        table.add(3, 245);
        table.add(4, -123);
        table.add(5, 888);
        table.add(6, 777);
        table.add(7, 333);
        Assert.assertTrue(table.max() == max);
        Assert.assertTrue(table.min() == min);
    }
}
