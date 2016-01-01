package io.dongyue.at15.tree.indexer;


import io.dongyue.at15.tree.indexer.mapreduce.index.IndexConfig;
import io.dongyue.at15.tree.indexer.mapreduce.index.IndexDriver;
import io.dongyue.at15.tree.indexer.mapreduce.meta.MetaConfig;
import io.dongyue.at15.tree.indexer.mapreduce.meta.MetaDriver;
import io.dongyue.at15.tree.indexer.mapreduce.pre.PreSortConfig;
import io.dongyue.at15.tree.indexer.mapreduce.sort.SortConfig;
import io.dongyue.at15.tree.indexer.mapreduce.sort.SortDriver;
import org.apache.commons.cli.*;

import io.dongyue.at15.tree.indexer.mapreduce.pre.PreSortDriver;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 16-1-1.
 * <p/>
 * Run map reduce job from command line
 */
public class Runner {
    public static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws Exception {
        HelpFormatter helpFormatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("job", true, "which job to run");
        options.addOption("base", true, "base path");
        // see https://github.com/at15/tree-index/issues/1
        // Exception in thread "main" java.lang.IllegalAccessError: tried to access method org.apache.commons.cli.Options.getOptionGroups()Ljava/util/Collection; from class org.apache.commons.cli.DefaultParser
        CommandLineParser parser = new GnuParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException err) {
            System.err.println("unable to parse arguments");
            helpFormatter.printHelp("indexer", options);
            return;
        }

        if (!cmd.hasOption("job")) {
            System.err.println("job is required");
            helpFormatter.printHelp("indexer", options);
            return;
        }

        String job = cmd.getOptionValue("job");

        if (job.equals("pre-sort") || job.equals("presort")) {
            PreSortConfig config = new PreSortConfig();
            if (cmd.hasOption("base")) {
                config.fromBasePath(cmd.getOptionValue("base"));
            }
            int exitCode = ToolRunner.run(new PreSortDriver(), config.toArray());
            System.exit(exitCode);
        }

        if (job.equals("sort")) {
            SortConfig config = new SortConfig();
            if (cmd.hasOption("base")) {
                config.fromBasePath(cmd.getOptionValue("base"));
            }
            int exitCode = ToolRunner.run(new SortDriver(), config.toArray());
            System.exit(exitCode);
        }

        if (job.equals("index")) {
            IndexConfig config = new IndexConfig();
            if (cmd.hasOption("base")) {
                config.fromBasePath(cmd.getOptionValue("base"));
            }
            int exitCode = ToolRunner.run(new IndexDriver(), config.toArray());
            System.exit(exitCode);
        }

        if (job.equals("meta")) {
            MetaConfig config = new MetaConfig();
            if (cmd.hasOption("base")) {
                config.fromBasePath(cmd.getOptionValue("base"));
            }
//            LOGGER.info("input " + config.getInput());
//            LOGGER.info("output " + config.getOutput());
            int exitCode = ToolRunner.run(new MetaDriver(), config.toArray());
            System.exit(exitCode);
//            System.exit(10086);
        }
        System.out.println("unsupported job or action" + args[0]);
    }
}
