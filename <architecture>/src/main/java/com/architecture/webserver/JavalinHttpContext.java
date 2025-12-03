package com.architecture.webserver;

import io.javalin.http.Context;

public class JavalinHttpContext implements HttpContext {
    private final Context ctx;

    public JavalinHttpContext(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public String getPathParam(String param) {
        return ctx.pathParam(param);
    }

    @Override
    public void json(Object obj) {
        ctx.json(obj);
    }

    @Override
    public void status(int code) {
        ctx.status(code);
    }
}