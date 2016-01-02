package io.dongyue.at15.tree.indexer.mapreduce.sort;

import io.dongyue.at15.tree.indexer.mapreduce.Config;

/**
 * Created by at15 on 16-1-1.
 *
 * @TODO test
 */
public class SortConfig implements Config {
    private String input;
    private String output;
    private String partition;
    private String meta;

    public String[] toArray() {
        String[] args = {input, output, partition, meta};
        return args;
    }

    public void fromArray(String[] args) {
        setInput(args[0]);
        setOutput(args[1]);
        setPartition(args[2]);
        setMeta(args[3]);
    }

    public void fromBasePath(String base) {
        setInput(base + "/pre-sort/out");
        setOutput(base + "/sort/out");
        setPartition(base + "/part.lst");
        setMeta(base + "/sort/meta");
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }



}
