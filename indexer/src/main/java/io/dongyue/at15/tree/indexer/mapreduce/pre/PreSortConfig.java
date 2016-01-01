package io.dongyue.at15.tree.indexer.mapreduce.pre;

import io.dongyue.at15.tree.indexer.mapreduce.Config;

/**
 * Created by at15 on 16-1-1.
 * <p/>
 * pre sort map reduce path configs
 *
 * @TODO Add separator config
 */
public class PreSortConfig implements Config {
    private String input;
    private String output;
    private Integer keyColumn = 0;

    public String[] toArray() {
        String[] args = {input, output, String.valueOf(keyColumn)};
        return args;
    }

    public void fromArray(String[] args) {
        setInput(args[0]);
        setOutput(args[1]);
        setKeyColumn(args[2]);
    }

    public void fromBasePath(String base){
        setInput(base + "/src");
        setOutput(base + "/pre-sort/out");
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

    public Integer getKeyColumn() {
        return keyColumn;
    }

    public String getKeyColumnAsString() {
        return String.valueOf(keyColumn);
    }

    public void setKeyColumn(String keyColumn) {
        setKeyColumn(Integer.valueOf(keyColumn));
    }

    public void setKeyColumn(Integer keyColumn) {
        this.keyColumn = keyColumn;
    }
}
