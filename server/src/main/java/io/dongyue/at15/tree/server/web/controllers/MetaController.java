package io.dongyue.at15.tree.server.web.controllers;


import io.dongyue.at15.tree.common.format.MetaTable;
import io.dongyue.at15.tree.server.manager.MetaManager;
import io.dongyue.at15.tree.server.web.ResponseWrapper;
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

    @RequestMapping(value = "/{table}/meta")
    public ResponseEntity<ResponseWrapper> meta(@PathVariable String table) throws IOException {
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            if (!MetaManager.inMem(table)) {
                MetaManager.loadFromHDFS(table);
            }
            wrapper.setData(MetaManager.getTable(table));
            wrapper.setCode(HttpStatus.OK);
            return new ResponseEntity<ResponseWrapper>(wrapper, wrapper.getCode());
        } catch (IOException ex) {
            wrapper.setMsg(ex.getMessage());
            wrapper.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            if (ex.getMessage().contains("not exists")) {
                wrapper.setCode(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ResponseWrapper>(wrapper, wrapper.getCode());
        }
    }
}
