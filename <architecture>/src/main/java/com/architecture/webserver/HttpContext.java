package com.architecture.webserver;

public interface HttpContext {
    String getPathParam(String param);
    void json(Object obj);
    void status(int code);
}