package connector;

import java.sql.*;

public class DBConnector {
    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String url = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=Lab5;" +
                "user=sa;" +
                "password=123456;" +
                "encrypt=true;" +
                "trustServerCertificate=true;";

        return DriverManager.getConnection(url);
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}