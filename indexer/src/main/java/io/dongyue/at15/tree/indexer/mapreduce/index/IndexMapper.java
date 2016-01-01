package io.dongyue.at15.tree.indexer.mapreduce.index;

import io.dongyue.at15.tree.indexer.format.Meta;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by at15 on 16-1-1.
 */
public class IndexMapper extends
        Mapper<LongWritable, Text, Text, Text> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexMapper.class);
    public static final String PREVIOUS_SORT_OUTPUT_CONFIG_NAME = "index.sort.output";
    private String previousSortOutput;

    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        Configuration configuration = context.getConfiguration();
        previousSortOutput = configuration.get(PREVIOUS_SORT_OUTPUT_CONFIG_NAME);
        LOGGER.info("previous sort output is " + previousSortOutput);
    }

    // generate the partition file path from meta
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        // need to split the text value
        NumberFormat nf = new DecimalFormat("00000");
        String line = value.toString();
        Meta meta = Meta.parse(line);
        Integer partitionId = meta.getPartitionId();
        String partitionFile = previousSortOutput + "/" + "part-r-" + nf.format(partitionId);
//        LOGGER.info("partition file is " + partitionFile);
        context.write(new Text(partitionFile), value);
    }
}
