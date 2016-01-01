package io.dongyue.at15.tree.indexer.mapreduce.index;

import io.dongyue.at15.tree.indexer.mapreduce.Config;
import io.dongyue.at15.tree.indexer.mapreduce.sort.SortConfig;

/**
 * Created by at15 on 16-1-1.
 */
public class IndexConfig implements Config {


    private String input;
    private String output;
    private String previousSortOutput;
    private String localIndex = "/tmp/index";
    private String remoteIndex;

    public String[] toArray() {
        String[] args = {input, output, previousSortOutput, localIndex, remoteIndex};
        return args;
    }

    public void fromArray(String[] args) {
        setInput(args[0]);
        setOutput(args[1]);
        setPreviousSortOutput(args[2]);
        setLocalIndex(args[3]);
        setRemoteIndex(args[4]);
    }

    public void fromBasePath(String base) {
        SortConfig sortConfig = new SortConfig();
        sortConfig.fromBasePath(base);

        setInput(sortConfig.getMeta());
        // TODO: maybe this should be renamed to meta
        setOutput(base + "/index/out");
        setPreviousSortOutput(sortConfig.getOutput());
        setRemoteIndex(base + "/index/idx");
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

    public String getPreviousSortOutput() {
        return previousSortOutput;
    }

    public void setPreviousSortOutput(String previousSortOutput) {
        this.previousSortOutput = previousSortOutput;
    }

    public String getLocalIndex() {
        return localIndex;
    }

    public void setLocalIndex(String localIndex) {
        this.localIndex = localIndex;
    }

    public String getRemoteIndex() {
        return remoteIndex;
    }

    public void setRemoteIndex(String remoteIndex) {
        this.remoteIndex = remoteIndex;
    }

}
