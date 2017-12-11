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
    public void testSimpleDelete() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable();
        table.add(1, 3);
        table.delete(1);
        Assert.assertNull(table.search(1));
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

    @Test
    public void testPrint() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable();
        table.add(10, 666);
        table.add(20, -12345);
        table.add(3, 245);
        table.add(4, -123);
        table.add(5, 888);
        table.add(6, 777);
        table.add(7, 333);
        table.add(19, 8);
        table.add(16, 77);
        table.add(71, 633);
        Assert.assertEquals(table.print(), "Hash table: [ {  77  } {  } {  } {  245  8  } {  -12345  -123  } {  888  } {  777  } {  333  633  } {  } {  } {  666  } {  } {  } {  } {  } {  } ]");
    }
}
