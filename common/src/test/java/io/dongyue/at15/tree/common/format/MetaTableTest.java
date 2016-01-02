package io.dongyue.at15.tree.common.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by at15 on 16-1-3.
 */
public class MetaTableTest {
    @Test
    public void testReadFile() throws IOException {
        MetaTable table = new MetaTable(new File("../data/ml-100k/meta.txt"));
        Assert.assertEquals((long) 0, (long) table.getRow(0).getPartitionId());
        Assert.assertEquals("/user/at15/warehouse/ml-100k/index/idx/L3VzZXIvYXQxNS93YXJlaG91c2UvbWwtMTAway9zb3J0L291dC9wYXJ0LXItMDAwMDA=.idx"
                , table.getRow(0).getIndexPath());
    }

    @Test
    public void testJson() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        MetaTable table = new MetaTable(new File("../data/ml-100k/meta.txt"));
        System.out.println(mapper.writeValueAsString(table));
    }
}
