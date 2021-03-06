package tests;

import hashtable.OpenAddressHashTableDH;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.Random;

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
        table.add(10, 228);
        table.add(20, -12345);
        table.add(3, 245);
        table.add(4, -123);
        table.add(5, 888);
        table.add(6, 777);
        table.add(7, 333);
        Assert.assertTrue(table.max() == -12345);
        Assert.assertTrue(table.min() == 245);
    }

    @Test
    public void testMinAndMaxEmpty() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable();
        Assert.assertNull(table.max());
        Assert.assertNull(table.min());
        table.add(Integer.MAX_VALUE, 100);
        Assert.assertNotNull(table.min());
        Assert.assertNotNull(table.max());
        Assert.assertEquals(table.max(), table.min());
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

    @Test
    public void speedTestAdd() {
        final hashtable.ChainHashTable table = new hashtable.ChainHashTable(1024 * 512);
        final Random random = new Random();
        final Clock clock = Clock.systemDefaultZone();
        long before = clock.millis();
        for (int i = 0; i < 1000000; ++i) {
            table.add(random.nextInt(1000000), random.nextInt(1000));
        }
        long after = clock.millis();
        System.out.println("Add time: " + String.valueOf(after - before));
        for (int i = 0; i < 1000000; ++i) {
            table.search(random.nextInt(1000000));
        }
        before = clock.millis();
        System.out.println("Search time: " + String.valueOf(before - after));
        for (int i = 0; i < 1000000; ++i) {
            table.delete(random.nextInt(1000000));
        }
        after = clock.millis();
        System.out.println("Delete time: " + String.valueOf(after - before));
    }
}
