package io.dongyue.at15.tree.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by at15 on 16-1-1.
 * <p/>
 * Index
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "Hi, you got me";
    }
}
