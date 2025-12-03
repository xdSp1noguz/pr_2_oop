package com.architecture.webserver;

import io.javalin.Javalin;

public interface WebServer {
    Javalin start(int port);
}