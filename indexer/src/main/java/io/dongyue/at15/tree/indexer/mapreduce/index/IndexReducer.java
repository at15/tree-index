package io.dongyue.at15.tree.indexer.mapreduce.index;

import io.dongyue.at15.tree.common.format.Meta;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.mellowtech.core.bytestorable.CBInt;
import org.mellowtech.core.bytestorable.CBString;
import org.mellowtech.core.collections.tree.BTree;
import org.mellowtech.core.collections.tree.BTreeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by at15 on 16-1-1.
 */
public class IndexReducer extends
        Reducer<Text, Text, Text, Text> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexReducer.class);
    public static final String LOCAL_INDEX_CONFIG_NAME = "index.local.folder";
    public static final String REMOTE_INDEX_CONFIG_NAME = "index.remote.folder";
    private String localIndex;
    private String remoteIndex;

    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        Configuration configuration = context.getConfiguration();
        localIndex = configuration.get(LOCAL_INDEX_CONFIG_NAME);
        remoteIndex = configuration.get(REMOTE_INDEX_CONFIG_NAME);
        LOGGER.info("local index folder is " + localIndex);
        LOGGER.info("remote index folder is " + remoteIndex);
    }

    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // create local index folder
        File localIndexFolder = new File(localIndex);
        // TODO: check if dir is created
        localIndexFolder.mkdir();

        LOGGER.info("start reading file " + key.toString());
        Path partitionPath = new Path("hdfs://" + key.toString());
        FileSystem fs = FileSystem.get(context.getConfiguration());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(partitionPath)));
        SortedFileIterator iterator = new SortedFileIterator(br);

        String indexFileName = new String(Base64.encodeBase64(key.getBytes()));
        String indexFilePathPrefix = localIndex + "/" + indexFileName;
        String indexFilePath = localIndex + "/" + indexFileName + ".idx";

        LOGGER.info("start building index");
        BTree bt = new BTreeBuilder().valuesInMemory(true)
                .build(new CBInt(), new CBString(), indexFilePathPrefix);
        bt.createIndex(iterator);
        bt.save();
        bt.close();
        LOGGER.info("index building finished");


        LOGGER.info("uploading index file to HDFS");
        // TODO: should have .val file, why I only got .idx file
        Path idxFile = new Path(indexFilePath);
        String indexFileRemotePath = remoteIndex + "/" + indexFileName + ".idx";
        Path idxFileHDFS = new Path(indexFileRemotePath);
        fs.copyFromLocalFile(true, true, idxFile, idxFileHDFS);
        LOGGER.info("upload completed");

        // TODO: write meta data as well, the mapper may need more data
        // The values should only have one value, which is the meta row
        Text value = values.iterator().next();
        Meta meta = Meta.parse(value.toString());
        meta.setIndexPath(indexFileRemotePath);

        context.write(new Text(meta.getPartitionIdAsString()), new Text(meta.withOutPartitionId()));
    }
}