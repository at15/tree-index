package io.dongyue.at15.tree.indexer.mapreduce.sort;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import org.apache.hadoop.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 16-1-1.
 */
public class SortDriver extends Configured implements Tool {
    public static final Logger LOGGER = LoggerFactory.getLogger(SortDriver.class);

    // default partition is 5 TODO: should estimate based on input file size
    public static final Integer NUM_REDUCER = 5;

    public int run(String[] args) throws Exception {
        SortConfig config = new SortConfig();
        config.fromArray(args);

        Job job = Job.getInstance(getConf());
        job.setJobName("sort");
        job.setJarByClass(SortDriver.class);

        // define the path
        Path inputPath = new Path(config.getInput());
        Path partitionFilePath = new Path(config.getPartition());
        Path outputPath = new Path(config.getOutput());
        Path metaPath = new Path(config.getMeta());
        LOGGER.info("use " + inputPath.toString() + " as sort input");
        LOGGER.info("use " + partitionFilePath.toString() + " as partition");
        LOGGER.info("use " + outputPath.toString() + " as sort output");
        LOGGER.info("use " + metaPath.toString() + " as meta output");

        // define the mapper
        // use the identity mapper, which is the default implementation
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(SequenceFileInputFormat.class);
        SequenceFileInputFormat.setInputPaths(job, inputPath);

        // define the reducer
        job.getConfiguration().set(SortReducer.META_BASE_PATH, metaPath.toString());
        job.setReducerClass(SortReducer.class);
        job.setNumReduceTasks(NUM_REDUCER);
        // use text for debug, use sequence is faster I guess
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        TextOutputFormat.setOutputPath(job, outputPath);

        // set partition
        job.setPartitionerClass(TotalOrderPartitioner.class);
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), partitionFilePath);

        // set the sampler
        InputSampler.writePartitionFile(job, new InputSampler.RandomSampler(
                1, 10000));

        // set multiple output
        MultipleOutputs.addNamedOutput(job, "meta", TextOutputFormat.class,
                IntWritable.class, Text.class);

        // clean up the old output path
        outputPath.getFileSystem(job.getConfiguration()).delete(outputPath, true);
        metaPath.getFileSystem(job.getConfiguration()).delete(metaPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
