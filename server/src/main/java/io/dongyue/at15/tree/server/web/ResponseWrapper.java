package io.dongyue.at15.tree.server.web;

import org.springframework.http.HttpStatus;

/**
 * Created by at15 on 16-1-3.
 */
public class ResponseWrapper {
    private String msg = "";
    private HttpStatus code = HttpStatus.OK;
    private Object data;

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
