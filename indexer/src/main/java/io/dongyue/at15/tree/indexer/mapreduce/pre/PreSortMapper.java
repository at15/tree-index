package io.dongyue.at15.tree.indexer.mapreduce.pre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by at15 on 16-1-1.
 */
public class PreSortMapper extends
        Mapper<LongWritable, Text, IntWritable, Text> {
    private Integer columnIndex = 0;
    private String separator = "\t";
    private IntWritable keyVal;
    public static final String COLUMN_INDEX_CONFIG_NAME = "presort.column.index";
    public static final String COLUMN_SEP_CONFIG_NAME = "presort.column.separator";
    public static final Logger LOGGER = LoggerFactory.getLogger(PreSortMapper.class);

    // get the column and separator from configuration
    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        Configuration configuration = context.getConfiguration();
        columnIndex = Integer.parseInt(configuration.get(COLUMN_INDEX_CONFIG_NAME, "0"));
        separator = configuration.get(COLUMN_SEP_CONFIG_NAME, separator);
        LOGGER.info("column index is " + columnIndex);
        keyVal = new IntWritable();
    }

    // output specific column as key
    // TODO: may use the input parser class
    // TODO: try catch to avoid crash on wrong format data
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] columns = line.split(separator);
        keyVal.set(Integer.parseInt(columns[columnIndex]));
        context.write(keyVal, value);
    }
}