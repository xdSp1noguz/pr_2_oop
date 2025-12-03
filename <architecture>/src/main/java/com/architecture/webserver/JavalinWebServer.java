package com.architecture.webserver;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavalinWebServer implements WebServer {

    @Override
    public Javalin start(int port) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
            // ДОДАЄМО CORS (щоб Live Server міг бачити дані)
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost()));
        });

        System.out.println("Starting Web Server on port " + port + "...");
        return app.start(port);
    }
}