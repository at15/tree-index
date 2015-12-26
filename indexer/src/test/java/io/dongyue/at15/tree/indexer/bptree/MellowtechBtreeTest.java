package io.dongyue.at15.tree.indexer.bptree;

import io.dongyue.at15.tree.indexer.bptree.mellowtech.MellowBPTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-12-26.
 */
public class MellowtechBtreeTest {
    @Test
    public void testOpen() {
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        Assert.assertEquals(true, bpTreeIndex.open("/tmp/mellowbptreeindex"));
    }

    @Test
    public void testCreate() {
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        Assert.assertEquals(true, bpTreeIndex.create("/tmp/mellowbptreeindex"));
    }

    @Test
    public void testRemove(){
        BPTreeIndex bpTreeIndex = new MellowBPTree();
        bpTreeIndex.removeIfExists("/tmp/mellowbptreeindex");
    }
}
