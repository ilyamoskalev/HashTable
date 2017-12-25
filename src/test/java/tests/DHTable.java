package tests;

import hashtable.OpenAddressHashTableDH;
import hashtable.OpenAddressHashTableLP;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.Random;

/**
 * Created by ilamoskalev on 11.12.2017.
 */
public class DHTable {
    @Test
    public void testSimpleAddAndSearch() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        table.add(1, 3);
        final Integer value = 3;
        Assert.assertEquals(table.search(1), value);
    }

    @Test
    public void testSimpleDelete() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        table.add(1, 3);
        table.delete(1);
        Assert.assertNull(table.search(1));
    }

    @Test
    public void testNoKeySearch() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        Assert.assertNull(table.search(1));
    }

    @Test
    public void testMinAndMax() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        table.add(10, 88789);
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
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        Assert.assertNull(table.max());
        Assert.assertNull(table.min());
        table.add(Integer.MAX_VALUE, 100);
        Assert.assertNotNull(table.min());
        Assert.assertNotNull(table.max());
        Assert.assertEquals(table.max(), table.min());
    }

    @Test
    public void testPrint() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
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
        Assert.assertEquals(table.print(), "Hash table: [ 77  __  __  245  -123  888  777  333  __  __  666  __  8  __  633  -12345  ]");
    }

    @Test
    public void testDelete() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
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
        table.delete(10);
        table.delete(3);
        table.delete(4);
        table.delete(5);
        table.delete(6);
        table.delete(7);
        table.delete(19);
        table.delete(16);
        table.delete(71);
        table.delete(20);
        Assert.assertEquals(table.print(), "Hash table: [ D __  __  D D D D D __  __  D __  D __  D D ]");
    }

    @Test
    public void speedTestAdd() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
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
