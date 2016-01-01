package io.dongyue.at15.tree.indexer.mapreduce.sort;

import io.dongyue.at15.tree.indexer.mapreduce.pre.PreSortConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 16-1-1.
 */
public class SortConfigTest {
    @Test
    public void testBasePath() {
        String base = "/user/at15/warehouse/ml-100k";

        PreSortConfig preSortConfig = new PreSortConfig();
        preSortConfig.fromBasePath(base);

        SortConfig sortConfig = new SortConfig();
        sortConfig.fromBasePath(base);

        Assert.assertEquals(preSortConfig.getOutput(), sortConfig.getInput());
    }
}
