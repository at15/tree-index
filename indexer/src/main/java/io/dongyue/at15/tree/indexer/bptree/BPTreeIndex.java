package io.dongyue.at15.tree.indexer.bptree;

import java.util.Iterator;

/**
 * Created by at15 on 15-12-26.
 */
public interface BPTreeIndex {
    /**
     * @param indexPath index file path, it's ok to have other extension like .idx, just don't let user know it
     * @return boolean
     */
    boolean open(String indexPath);

    /**
     * @param indexPath
     * @return boolen
     */
    boolean create(String indexPath);

    /**
     * Create index using bulk loading
     *
     * @param iterator source to iterate
     * @return boolean
     */
    boolean bulkLoading(Iterator iterator);

    void put(Integer key, String value);

    String get(Integer key);

    /**
     * Get the path of the index file
     *
     * @return String
     */
    String getPath();

    void removeIfExists(String indexPath);
}
