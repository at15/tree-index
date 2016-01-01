package io.dongyue.at15.tree.indexer.mapreduce.index;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 16-1-1.
 *
 * Build index in local fs then upload to HDFS
 */
public class IndexDriver extends Configured implements Tool {
    public static final Logger LOGGER = LoggerFactory.getLogger(IndexDriver.class);
    public static final Integer NUM_REDUCER = 5;

    public int run(String args[]) throws Exception {
        IndexConfig config = new IndexConfig();
        config.fromArray(args);

        // job
        Job job = Job.getInstance(getConf());
        job.setJobName("index");
        job.setJarByClass(IndexDriver.class);

        Path inputPath = new Path(config.getInput());
        Path outputPath = new Path(config.getOutput());
        Path remoteIndexPath = new Path(config.getRemoteIndex());

        // set mapper
        job.getConfiguration().set(IndexMapper.PREVIOUS_SORT_OUTPUT_CONFIG_NAME, config.getPreviousSortOutput());
        job.setMapperClass(IndexMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, inputPath);

        // set the reducer
        job.getConfiguration().set(IndexReducer.LOCAL_INDEX_CONFIG_NAME, config.getLocalIndex());
        job.getConfiguration().set(IndexReducer.REMOTE_INDEX_CONFIG_NAME, remoteIndexPath.toString());
        job.setNumReduceTasks(NUM_REDUCER);
        job.setReducerClass(IndexReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        TextOutputFormat.setOutputPath(job, outputPath);


        // clean up the old output path
        outputPath.getFileSystem(job.getConfiguration()).delete(outputPath, true);
        // create the folder for remote index
        remoteIndexPath.getFileSystem(job.getConfiguration()).mkdirs(remoteIndexPath);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
