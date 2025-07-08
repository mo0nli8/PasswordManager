package com.example.passwordmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:passwords.db";
    public static void initDB(){
        String sql = """
            CREATE TABLE IF NOT EXISTS passwords (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                website TEXT NOT NULL,
                username TEXT NOT NULL,
                password TEXT NOT NULL
            )
        """;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
             stmt.execute(sql);
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void insert(appData data) {
        String sql = "INSERT INTO passwords(website, username, password) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data.getWebSiteName());
            pstmt.setString(2, data.getUserName());
            pstmt.setString(3, data.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<appData> getAllEntries() {
        List<appData> list = new ArrayList<>();
        String sql = "SELECT * FROM passwords";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String website = rs.getString("website");
                String username = rs.getString("username");
                String password = rs.getString("password");
                list.add(new appData(username, password, website));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
