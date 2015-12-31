package io.dongyue.at15.tree.indexer.format;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-12-31.
 * <p/>
 * Test if meta data is parsed properly
 */
public class MetaTest {

    @Test
    public void testParse() {
        String partitionAndRange = "0\t10\t20";
        Meta m1 = Meta.parse(partitionAndRange);
        Assert.assertEquals((long) 0, (long) m1.getPartitionId());

        String full = "0\t10\t20\t5\t/usr/at15/test.idx";
        Meta m2 = Meta.parse(full);
        Assert.assertEquals(5L, (long) m2.getCount());
        Assert.assertEquals("/usr/at15/test.idx", m2.getIndexPath());
    }
}
