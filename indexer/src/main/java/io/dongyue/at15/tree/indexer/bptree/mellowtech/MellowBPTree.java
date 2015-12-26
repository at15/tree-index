package io.dongyue.at15.tree.indexer.bptree.mellowtech;

import io.dongyue.at15.tree.common.util.FileUtils;
import io.dongyue.at15.tree.indexer.bptree.BPTreeIndex;
import org.mellowtech.core.bytestorable.CBInt;
import org.mellowtech.core.bytestorable.CBString;
import org.mellowtech.core.collections.tree.BTree;
import org.mellowtech.core.collections.tree.BTreeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by at15 on 15-12-26.
 */
public class MellowBPTree implements BPTreeIndex {


    private static final Logger LOGGER = LoggerFactory.getLogger(MellowBPTree.class);
    protected BTree<CBInt, CBString> bTree;
    protected String path;

    public boolean open(String indexPath) {
        try {
            // TODO: remove the trailing .idx
            bTree = new BTreeBuilder().build(new CBInt(), new CBString(), indexPath);
            path = indexPath;
            return true;
        } catch (IOException ex) {
            LOGGER.warn("cant open index", ex);
        }
        return false;
    }

    public boolean create(String indexPath) {
        return open(indexPath);
    }

    public boolean bulkLoading(Iterator iterator) {
        return false;
    }

    public void put(Integer key, String value) {
        try {
            bTree.put(new CBInt(key), new CBString(value));
        } catch (IOException ex) {
            LOGGER.warn("error put key " + key, ex);
        }
    }

    public void removeIfExists(String indexPath) {
        // check if the file exists
        // TODO: trim the .idx for indexPath
        FileUtils.deleteFileIfExists(indexPath + ".idx");
    }

    public String get(Integer key) {
        try {
            CBString s = bTree.get(new CBInt(key));
            return s.toString();
        } catch (IOException ex) {
            LOGGER.warn("error getting key " + key, ex);
            // TODO: maybe should throw a exception?
            return null;
        }
    }

    public String getPath() {
        return path;
    }
}
