package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

    private static final String dbUrl = "jdbc:mysql://localhost:3306/madb";
    private static final String name = "root";
    private static final String password = "6531";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, name, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
