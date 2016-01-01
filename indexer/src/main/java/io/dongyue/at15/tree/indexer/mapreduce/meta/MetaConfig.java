package io.dongyue.at15.tree.indexer.mapreduce.meta;

import io.dongyue.at15.tree.indexer.mapreduce.Config;
import io.dongyue.at15.tree.indexer.mapreduce.index.IndexConfig;

/**
 * Created by at15 on 16-1-1.
 */
public class MetaConfig implements Config {
    private String input;
    private String output;

    public String[] toArray() {
        String[] args = {input, output};
        return args;
    }

    public void fromArray(String[] args) {
        setInput(args[0]);
        setOutput(args[1]);
    }

    public void fromBasePath(String base) {
        IndexConfig indexConfig = new IndexConfig();
        indexConfig.fromBasePath(base);

        setInput(indexConfig.getOutput());
        setOutput(base + "/meta/out");
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
}
