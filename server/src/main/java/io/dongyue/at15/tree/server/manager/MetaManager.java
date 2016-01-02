package io.dongyue.at15.tree.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by at15 on 16-1-2.
 */
public class MetaManager {
    public static final String localMetaFolder = "/tmp/tree/meta";
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaManager.class);
    private static boolean booted = false;

    public static void init() {
        if (booted) {
            LOGGER.info("meta manager already booted");
            return;
        }
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
