package io.dongyue.at15.tree.indexer.mapreduce.sort;

import io.dongyue.at15.tree.common.format.Meta;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by at15 on 16-1-1.
 * <p/>
 * Sort and partition
 */
public class SortReducer extends
        Reducer<IntWritable, Text, IntWritable, Text> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SortReducer.class);
    public static final String META_BASE_CONFIG_NAME = "sort.meta.base";
    private Integer minKey;
    private Integer maxKey;
    private Long count;
    private MultipleOutputs<IntWritable, Text> mos;
    private String metaPath;

    public void setup(Context context) {
        Configuration configuration = context.getConfiguration();
        count = 0L;
        mos = new MultipleOutputs<IntWritable, Text>(context);
        // TODO: throw exception when it is not set
        metaPath = configuration.get(META_BASE_CONFIG_NAME);
    }

    // write meat out in clean up stage
    // meta is
    // partitionId | start | end | count
    public void cleanup(Context context) throws IOException, InterruptedException {
        Integer partitionId = context.getTaskAttemptID().getTaskID().getId();
        LOGGER.info("min key " + minKey);
        LOGGER.info("max key " + maxKey);
        LOGGER.info("partition id is " + partitionId);
        String metaFileName = metaPath + "/" + partitionId;
        Meta meta = new Meta();
        meta.setPartitionId(partitionId);
        meta.setStart(minKey);
        meta.setEnd(maxKey);
        meta.setCount(count);
        mos.write("meta", new IntWritable(meta.getPartitionId()), new Text(meta.withOutPartitionId()),
                metaFileName);
        mos.close();
    }

    public void reduce(IntWritable key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // NOTE: the count is key count, not value count, it should be split
        count++;
        if (minKey == null || minKey > key.get()) {
            minKey = key.get();
        }
        if (maxKey == null || maxKey < key.get()) {
            maxKey = key.get();
        }
        // TODO: compress the values with the same key in secondary index mode
        for (Text v : values) {
            context.write(key, v);
        }
    }
}