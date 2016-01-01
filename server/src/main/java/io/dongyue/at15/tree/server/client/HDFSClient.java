package io.dongyue.at15.tree.server.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by at15 on 16-1-1.
 */
public class HDFSClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(HDFSClient.class);
    protected Configuration conf;
    protected FileSystem hdfs;

    public HDFSClient() {
    }

    public void start() throws IOException {
        conf = new Configuration();
        try {
            hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
        } catch (URISyntaxException ex) {
            throw new IOException(ex);
        }
    }

    public void stop() throws IOException {
        hdfs.close();
    }

    public Configuration getConf() {
        return conf;
    }

    public FileSystem getHDFS() {
        return hdfs;
    }

    public void copyToLocal(String remote, String local) throws IOException {
        LOGGER.info("start copy " + remote + " to " + local);
        hdfs.copyToLocalFile(false, new Path(remote), new Path(local));
        LOGGER.info("end copy " + remote + " to " + local);
    }
}
