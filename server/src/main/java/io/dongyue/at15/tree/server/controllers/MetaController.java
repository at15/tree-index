package io.dongyue.at15.tree.server.controllers;


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
//@RequestMapping("/meta")
public class MetaController {
    protected Configuration conf;
    protected FileSystem hdfs;

    //    @RequestMapping("/")
    @RequestMapping("/meta")
    public String index() throws IOException {
        // use raw hdfs
        conf = new Configuration();
        try {
            hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
            return hdfs.getHomeDirectory().toString();
        } catch (URISyntaxException ex) {
            throw new IOException(ex);
        }
//        return "please specify which meta you need";
    }

    @RequestMapping("/{table}/meta")
    public String meta(@PathVariable String table) {
        return table;
    }
}
