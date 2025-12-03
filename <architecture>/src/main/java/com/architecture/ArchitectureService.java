package com.architecture;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArchitectureService {
    private final Connection connection;

    @Inject
    public ArchitectureService(Connection connection) {
        this.connection = connection;
    }

    // Метод для сохранения действий в базу
    public void logAction(String action, String details) {
        String sql = "INSERT INTO project_logs (action, details) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, action);
            stmt.setString(2, details);
            stmt.executeUpdate();
            System.out.println("[DB LOG] Запись сохранена: " + action + " - " + details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}