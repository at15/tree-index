package io.dongyue.at15.tree.server.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;

/**
 * Created by at15 on 16-1-2.
 */
@RestController
//@RequestMapping("/meta")
public class MetaController {
    @Autowired
    private FsShell shell;

    //    @RequestMapping("/")
    @RequestMapping("/meta")
    public String index() {
        // TODO: should list all meta
        shell.ls("/user");
        return "please specify which meta you need";
    }

    @RequestMapping("/{table}/meta")
    public String meta(@PathVariable String table) {
        return table;
    }
}
