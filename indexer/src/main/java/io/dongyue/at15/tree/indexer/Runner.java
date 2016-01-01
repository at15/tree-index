package io.dongyue.at15.tree.indexer;


import io.dongyue.at15.tree.indexer.mapreduce.pre.PreSortConfig;
import org.apache.commons.cli.*;
import org.apache.commons.configuration.PropertiesConfiguration;

import io.dongyue.at15.tree.indexer.mapreduce.pre.PreSortDriver;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by at15 on 16-1-1.
 */
public class Runner {
    public static void main(String[] args) throws Exception {
        HelpFormatter helpFormatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("job", true, "which job to run");
        options.addOption("base", true, "base path");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException err) {
            System.err.println("unable to parse arguments");
            helpFormatter.printHelp("tree-index indexer runner", options);
            return;
        }

        if (!cmd.hasOption("job")) {
            System.err.println("job is required");
            helpFormatter.printHelp("tree-index indexer runner", options);
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

        System.out.println("unsupported job or action" + args[0]);
    }
}
