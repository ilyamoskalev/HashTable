package tests;

import hashtable.OpenAddressHashTableLP;
import org.junit.Test;

import java.time.Clock;
import java.util.Random;

/**
 * Created by ilamoskalev on 11.12.2017.
 */
public class LPTable {
    @Test
    public void speedTestAdd() {
        final OpenAddressHashTableLP table = new OpenAddressHashTableLP();
        final Random random = new Random();
        final Clock clock = Clock.systemDefaultZone();
        long before = clock.millis();
        for (int i = 0; i < 100000; ++i) {
            table.add(random.nextInt(1000), random.nextInt(1000));
        }
        final long after = clock.millis();
        System.out.println(after - before);
        for (int i = 0; i < 100000; ++i) {
            table.search(random.nextInt(1000));
        }
        before = clock.millis();
        System.out.println(before - after);
        System.out.println(table.print());
    }
}
