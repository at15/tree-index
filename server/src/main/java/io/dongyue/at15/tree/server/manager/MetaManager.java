package io.dongyue.at15.tree.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.fs.FsShell;

import java.io.File;

/**
 * Created by at15 on 16-1-2.
 */
public class MetaManager {
    public static final String localMetaFolder = "/tmp/tree/meta";
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaManager.class);
    private static boolean booted = false;
    private static FsShell shell;

    public static void init(FsShell shell) {
        if (booted) {
            LOGGER.info("meta manager already booted");
            return;
        }
        MetaManager.shell = shell;
        // create local folder
        File metaFolder = new File(localMetaFolder);
        if (!metaFolder.exists()) {
            if (!metaFolder.mkdirs()) {
                LOGGER.warn("can not create local meta folder " + localMetaFolder);
            } else {
                LOGGER.info("local meta folder " + localMetaFolder + " created ");
            }
        } else {
            LOGGER.info("local meta folder " + localMetaFolder + " exists ");
        }
    }


}
