package io.dongyue.at15.tree.server.manager;

import io.dongyue.at15.tree.common.format.MetaTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.fs.FsShell;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by at15 on 16-1-2.
 * <p/>
 * manage all the meta info
 */
public class MetaManager {
    public static final String localMetaFolder = "/tmp/tree/meta";
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaManager.class);
    private static boolean booted = false;
    private static FsShell shell;
    private static Map<String, MetaTable> metaTableMap;

    public static void init(FsShell shell) {
        if (booted) {
            LOGGER.info("meta manager already booted");
            return;
        }
        MetaManager.shell = shell;
        metaTableMap = new HashMap<>();
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

    public static boolean inMem(String tableName) {
        return metaTableMap.containsKey(tableName);
    }

    public static void loadFromHDFS(String tableName) {
        // copy to local
        shell.copyToLocal();
        metaTableMap.put(tableName, new MetaTable());
    }

}
