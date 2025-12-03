package com.architecture;

import com.google.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArchitectureService {
    private final Connection connection;

    @Inject
    public ArchitectureService(Connection connection) {
        this.connection = connection;
        createTableIfNotExists(); // Гарантуємо, що таблиця існує
    }

    private void createTableIfNotExists() {
        // Створюємо таблицю models згідно з вашим завданням
        String sql = "CREATE TABLE IF NOT EXISTS models (" +
                     "modelid TEXT PRIMARY KEY, " +
                     "version INTEGER)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            // Додамо тестовий запис, якщо таблиця порожня
            stmt.execute("INSERT OR IGNORE INTO models (modelid, version) VALUES ('M-101', 1)");
            stmt.execute("INSERT OR IGNORE INTO models (modelid, version) VALUES ('SkyScraper-A', 5)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для логування (з минулих завдань)
    public void logAction(String action, String details) {
        // ... (код логування можна залишити без змін)
    }

    // --- НОВИЙ МЕТОД: ЧИТАННЯ ДАНИХ ---
    public List<Model> getAllModels() {
        List<Model> models = new ArrayList<>();
        String sql = "SELECT modelid, version FROM models";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String id = resultSet.getString("modelid");
                int ver = resultSet.getInt("version");
                models.add(new Model(id, ver));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading models", e);
        }
        return models;
    }
}