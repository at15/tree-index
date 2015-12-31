package io.dongyue.at15.tree.indexer.format;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-12-26.
 *
 * test custom input parser
 */
public class InputParserTest {
    @Test
    public void testParse() {
        InputParser parser = new InputParser();
        String s = "196\t242\t3\t881250949";
        parser.parse(s);
        Assert.assertEquals((long) 196, (long) parser.getKey());
        Assert.assertEquals("242\t3\t881250949", parser.getValue());
    }

    @Test
    public void testSep() {
        InputParser parser = new InputParser(",");
        String csv = "1,2,3,jack";
        parser.parse(csv);
        Assert.assertEquals((long) 1, (long) parser.getKey());
        Assert.assertEquals("2,3,jack", parser.getValue());
    }
}
