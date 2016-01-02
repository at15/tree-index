package io.dongyue.at15.tree.server.web.controllers;


import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Created by at15 on 16-1-2.
 */
@RestController
//@CrossOrigin()
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

    @RequestMapping("/{table}/meta")
    public String meta(@PathVariable String table) {
        // if the meta table doest not exist, we load it from HDFS

        return table;
    }
}
