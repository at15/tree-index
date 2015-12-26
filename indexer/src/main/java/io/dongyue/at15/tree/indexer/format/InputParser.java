package io.dongyue.at15.tree.indexer.format;

/**
 * Created by at15 on 15-12-26.
 */
public class InputParser {
    protected final String separator;
    protected String cacheInput;
    protected String[] cache;

    public InputParser() {
        separator = "\t";
    }

    public InputParser(String sep) {
        this.separator = sep;
    }

    public void parse(String input) {
        // TODO: should use more efficient one
        cacheInput = input;
        cache = input.split(separator);
    }

    public Integer getKey() {
        return Integer.parseInt(cache[0]);
    }

    public String getValue() {
        return cacheInput.substring(cache[0].length() + separator.length());
    }

//    public String[] getValues() {
//
//    }
}
