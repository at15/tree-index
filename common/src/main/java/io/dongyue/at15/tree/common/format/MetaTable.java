package io.dongyue.at15.tree.common.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by at15 on 16-1-2.
 */
public class MetaTable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaTable.class);
    private ArrayList<Meta> rows;

    public MetaTable() {
        rows = new ArrayList<Meta>();
    }

    public MetaTable(File file) throws IOException {
        this();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
//            LOGGER.debug(line);
            while (line != null) {
                rows.add(Meta.parse(line));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            throw new IOException("meta table file not found", ex);
        }
    }

    // TODO: should throw some exception
    public Meta getRow(Integer row) {
        return rows.get(row);
    }
}
