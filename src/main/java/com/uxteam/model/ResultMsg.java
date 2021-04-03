package com.uxteam.model;

import java.util.HashMap;
import java.util.Map;

public class ResultMsg {
    private boolean status = true;
    private Map result = new HashMap();
    private String message;

    public ResultMsg() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map getResult() {
        return result;
    }

    public void setResult(Map result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String nextUrl) {
        this.message = nextUrl;
    }

    public ResultMsg status(boolean code){
        setStatus(status);
        return this;
    }
    public ResultMsg message(String message){
        setMessage(message);
        return this;
    }
    public ResultMsg item(String K,Object V){
        this.result.put(K,V);
        return this;
    }

}
