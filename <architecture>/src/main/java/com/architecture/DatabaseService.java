package com.architecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {

    // Название файла базы данных
    private static final String DB_URL = "jdbc:sqlite:bim_system.db";

    public void initDatabase() {
        String createArchitects = "CREATE TABLE IF NOT EXISTS architects (" +
                "id TEXT PRIMARY KEY, " +
                "name TEXT)";

        String createModels = "CREATE TABLE IF NOT EXISTS models (" +
                "modelid TEXT PRIMARY KEY, " +
                "version INTEGER, " +
                "architect_id TEXT, " +
                "FOREIGN KEY (architect_id) REFERENCES architects(id) ON DELETE CASCADE)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createArchitects);
            stmt.execute(createModels);
            System.out.println("База даних та таблиці перевірені/створені.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveArchitect(Architect architect) {
        String sqlArch = "INSERT OR REPLACE INTO architects (id, name) VALUES (?, ?)";
        String sqlModel = "INSERT OR REPLACE INTO models (modelid, version, architect_id) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);

            // --- Сохраняем Архитектора ---
            try (PreparedStatement ps = conn.prepareStatement(sqlArch)) {
                ps.setString(1, architect.getId());
                ps.setString(2, architect.getName());
                ps.executeUpdate();
            }

            // --- Сохраняем Модель ---
            Model model = architect.getCurrentModel();
            if (model != null) {
                try (PreparedStatement ps = conn.prepareStatement(sqlModel)) {
                    ps.setString(1, model.getModelid());
                    
                    // !!! ИСПРАВЛЕНИЕ ЗДЕСЬ !!!
                    ps.setInt(2, model.getVersion()); // Используем setInt для чисел
                    
                    ps.setString(3, architect.getId());
                    ps.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("Дані успішно збережено в файл bim_system.db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}