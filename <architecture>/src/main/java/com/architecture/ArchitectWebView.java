package com.architecture;

import com.architecture.webserver.WebServer;
import com.google.inject.Inject;
import io.javalin.Javalin;

public class ArchitectWebView {

    private final WebServer webServer;
    private final ArchitectController controller;

    @Inject
    public ArchitectWebView(WebServer webServer, ArchitectController controller) {
        this.webServer = webServer;
        this.controller = controller;
    }

    public void start(int port) {
        Javalin app = webServer.start(port);

        // API Endpoint: Повертає список моделей у форматі JSON
        app.get("/api/models", ctx -> {
            ctx.json(controller.getAllModels());
        });

        // Головна сторінка
        app.get("/", ctx -> ctx.redirect("/index.html"));
        
        System.out.println("Web View started. Go to http://localhost:" + port);
    }
}