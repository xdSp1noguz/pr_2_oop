package com.architecture;

// --- Додані імпорти для веб-сервера ---
import com.architecture.webserver.JavalinWebServer;
import com.architecture.webserver.WebServer;
// ---------------------------------------

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ArchitectureModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class)
            .annotatedWith(Names.named("JDBC URL"))
            .toInstance("jdbc:sqlite:target/architecture.db");
    }

    // --- НОВИЙ МЕТОД: Впровадження веб-сервера ---
    @Provides
    @Singleton
    WebServer provideWebServer() {
        return new JavalinWebServer();
    }
    // ---------------------------------------------

    @Provides
    @Singleton
    Connection provideConnection(@Named("JDBC URL") String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            createTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to DB", e);
        }
    }

    private void createTableIfNotExists(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS project_logs (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "action TEXT NOT NULL, " +
                     "details TEXT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}