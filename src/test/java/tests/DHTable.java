package tests;

import hashtable.OpenAddressHashTableDH;
import org.junit.Test;

import java.time.Clock;
import java.util.Random;

/**
 * Created by ilamoskalev on 11.12.2017.
 */
public class DHTable {
    @Test
    public void speedTestAdd() {
        final OpenAddressHashTableDH table = new OpenAddressHashTableDH();
        final Random random = new Random();
        final Clock clock = Clock.systemDefaultZone();
        long before = clock.millis();
        for (int i = 0; i < 100000; ++i) {
            table.add(random.nextInt(1000), random.nextInt(1000));
        }
        long after = clock.millis();
        System.out.println("Add time: "+String.valueOf(after - before));
        for (int i = 0; i < 100000; ++i) {
            table.search(random.nextInt(1000));
        }
        before = clock.millis();
        System.out.println("Search time: "+String.valueOf(before - after));
        for (int i = 0; i < 100000; ++i) {
            table.delete(random.nextInt(1000));
        }
        after = clock.millis();
        System.out.println("Delete time: "+String.valueOf(after - before));
    }
}
