package io.dongyue.at15.tree.indexer.bptree;

import io.dongyue.at15.tree.indexer.bptree.mellowtech.MellowBPTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-12-26.
 */
public class MellowtechBtreeTest {
    private String path = "/tmp/mellowbptreeindex";

    @Test
    public void testOpen() {
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        Assert.assertEquals(true, bpTreeIndex.open(path));
    }

    @Test
    public void testCreate() {
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        Assert.assertEquals(true, bpTreeIndex.create(path));
    }

    @Test
    public void testPut() {
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        bpTreeIndex.create(path);
        bpTreeIndex.put(1, "jack");
        Assert.assertEquals("jack", bpTreeIndex.get(1));
    }

    @Test
    public void testRemove() {
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        bpTreeIndex.removeIfExists(path);
    }

    @After
    public void cleanUp(){
        // in case you just run one test in IDEA
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        bpTreeIndex.removeIfExists(path);
    }


}
