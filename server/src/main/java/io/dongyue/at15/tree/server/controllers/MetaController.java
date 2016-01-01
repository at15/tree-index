package io.dongyue.at15.tree.server.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by at15 on 16-1-2.
 */
@RestController
//@RequestMapping("/meta")
public class MetaController {
    //    @RequestMapping("/")
    @RequestMapping("/meta")
    public String index() {
        // TODO: should list all meta
        return "please specify which meta you need";
    }

    @RequestMapping("/{table}/meta")
    public String meta(@PathVariable String table) {
        return table;
    }
}
