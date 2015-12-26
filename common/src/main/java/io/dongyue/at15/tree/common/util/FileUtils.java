package io.dongyue.at15.tree.common.util;

import java.io.File;

/**
 * Created by at15 on 15-12-26.
 */
public class FileUtils {
    public static boolean localFileExists(String path) {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    public static boolean deleteFileIfExists(String path) {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            return f.delete();
        }
        return false;
    }
}
