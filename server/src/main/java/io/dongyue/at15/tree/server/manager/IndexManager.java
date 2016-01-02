package io.dongyue.at15.tree.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by at15 on 16-1-2.
 * <p/>
 * manage local index
 */
public class IndexManager {
    public static final String localIndexFolder = "/tmp/tree/index";
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexManager.class);
    private static boolean booted = false;

    public static void init() {
        if (booted) {
            LOGGER.info("meta manager already booted");
            return;
        }
        // create local folder
        File indexFolder = new File(localIndexFolder);
        if (!indexFolder.exists()) {
            if (!indexFolder.mkdirs()) {
                LOGGER.warn("can not create local index folder " + localIndexFolder);
            } else {
                LOGGER.info("local index folder " + localIndexFolder + " created ");
            }
        } else {
            LOGGER.info("local index folder " + localIndexFolder + " exists");
        }

    }
}
