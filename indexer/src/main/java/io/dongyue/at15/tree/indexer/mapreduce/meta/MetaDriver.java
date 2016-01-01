package io.dongyue.at15.tree.indexer.mapreduce.meta;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 16-1-1.
 * <p/>
 * merge all meta to one file
 */
public class MetaDriver extends Configured implements Tool {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDriver.class);
    private static final Integer NUM_REDUCER = 1;

    public int run(String[] args) throws Exception {
        MetaConfig config = new MetaConfig();
        config.fromArray(args);

//        LOGGER.info(config.getInput());
//        LOGGER.info(config.getOutput());

        Job job = Job.getInstance(getConf());
        job.setJobName("meta");
        job.setJarByClass(MetaDriver.class);

        // define the path
        Path mapInputPath = new Path(config.getInput());
        Path mapOutputPath = new Path(config.getOutput());

        // define the mapper
        job.setMapperClass(MetaMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, mapInputPath);

        // define the reducer, identity reducer
        job.setNumReduceTasks(NUM_REDUCER);

        // output
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        TextOutputFormat.setOutputPath(job, mapOutputPath);

        // clean the old output
        mapOutputPath.getFileSystem(job.getConfiguration()).delete(mapOutputPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}