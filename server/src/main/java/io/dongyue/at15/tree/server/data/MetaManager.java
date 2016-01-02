package io.dongyue.at15.tree.server.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        // create folder

    }
}
