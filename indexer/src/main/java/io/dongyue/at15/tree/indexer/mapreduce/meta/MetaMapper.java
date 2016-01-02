package io.dongyue.at15.tree.indexer.mapreduce.meta;

import io.dongyue.at15.tree.common.format.Meta;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by at15 on 16-1-1.
 */
public class MetaMapper extends
        Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        Meta meta = Meta.parse(line);
        context.write(new Text(meta.getPartitionIdAsString()), new Text(meta.withOutPartitionId()));
    }
}
