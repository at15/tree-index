package io.dongyue.at15.tree.server.controllers;


import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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

    @RequestMapping("/{table}/meta")
    public String meta(@PathVariable String table) {
        return table;
    }
}
