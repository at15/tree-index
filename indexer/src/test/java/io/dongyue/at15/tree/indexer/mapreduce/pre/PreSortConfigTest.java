package io.dongyue.at15.tree.indexer.mapreduce.pre;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 16-1-1.
 */
public class PreSortConfigTest {
    @Test
    public void testArray() {
        PreSortConfig config = new PreSortConfig();
        config.setInput("/user/at15/warehouse/imdb/src");
        config.setOutput("/user/at15/warehouse/imdb/pre");
        config.setKeyColumn(1);

        String[] args = config.toArray();
        PreSortConfig config2 = new PreSortConfig();
        config2.fromArray(args);

        Assert.assertEquals(config.getInput(), config2.getInput());
        Assert.assertEquals(config.getOutput(), config2.getOutput());
        Assert.assertEquals(config.getKeyColumn(), config2.getKeyColumn());
    }

    @Test
    public void testBasePath() {
        PreSortConfig config = new PreSortConfig();
        config.fromBasePath("/user/at15/warehouse/imdb");

        Assert.assertEquals("0", config.getKeyColumnAsString());
        Assert.assertEquals("/user/at15/warehouse/imdb/src",config.getInput());
        Assert.assertEquals("/user/at15/warehouse/imdb/pre-sort/out",config.getOutput());
    }
}
