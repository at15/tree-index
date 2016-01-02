package io.dongyue.at15.tree.server.web.controllers;


import io.dongyue.at15.tree.common.format.MetaTable;
import io.dongyue.at15.tree.server.manager.MetaManager;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Created by at15 on 16-1-2.
 */
@RestController
public class MetaController {
    @Autowired
    private FsShell shell;

    @RequestMapping("/meta")
    public String index() throws IOException {
        for (FileStatus s : shell.lsr("/tmp")) {
            System.out.println("> " + s.getPath());
        }
        return "lalala";
    }

    @RequestMapping(value = "/{table}/meta", headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MetaTable> meta(@PathVariable String table) throws IOException {
        // try to load from memory
        // try to load from hdfs and then into memory
        // use a ugly singleton
//        if (MetaManager.inMem(table)) {
//            return "got " + table + " in memory";
//        } else {
//            MetaManager.load(table);
//            return "load " + table + " for the first time";
//        }
        if (!MetaManager.inMem(table)) {
            MetaManager.loadFromHDFS(table);
        }
        return new ResponseEntity<MetaTable>(MetaManager.getTable(table), HttpStatus.OK);
    }
}
