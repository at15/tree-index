package io.dongyue.at15.tree.indexer.mapreduce;

/**
 * Created by at15 on 16-1-1.
 */
public interface Config {
    String[] toArray();

    void fromArray(String[] args);

    void fromBasePath(String base);
}
