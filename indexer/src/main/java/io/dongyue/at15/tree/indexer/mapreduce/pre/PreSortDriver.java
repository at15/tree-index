package io.dongyue.at15.tree.indexer.mapreduce.pre;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-12-31.
 * <p/>
 * input, table column text separated by tab
 * output,<int,string> use specific column value as key, line as value
 * the key is cast into IntWritable, and use the first column by default
 */
public class PreSortDriver extends Configured implements Tool {
    public static final Logger LOGGER = LoggerFactory.getLogger(PreSortDriver.class);
    // we don't need any reducer
    public static final Integer NUM_REDUCER = 0;

    public int run(String[] args) throws Exception {
        PreSortConfig config = new PreSortConfig();
        config.fromArray(args);

        Job job = Job.getInstance(getConf());
        job.setJobName("pre-sort");
        job.setJarByClass(PreSortDriver.class);

        Path mapInputPath = new Path(config.getInput());
        Path mapOutputPath = new Path(config.getOutput());
        LOGGER.info("use " + mapInputPath.toString() + " as pre-sort input ");
        LOGGER.info("use " + mapOutputPath.toString() + " as pre-sort output ");

        // define the mapper
        job.getConfiguration().set(PreSortMapper.COLUMN_INDEX_CONFIG_NAME, config.getKeyColumnAsString());
        job.setMapperClass(PreSortMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, mapInputPath);

        // define reducer
        job.setNumReduceTasks(NUM_REDUCER);

        // define the output, NOTE: we do not have reducer
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        SequenceFileOutputFormat.setOutputPath(job, mapOutputPath);

        // clean up the output folder
        mapOutputPath.getFileSystem(job.getConfiguration()).delete(mapOutputPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
