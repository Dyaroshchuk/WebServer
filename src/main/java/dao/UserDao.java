package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private static final Connection connection = DbConnector.connect();

    public boolean addClient(Client client) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO users(name, password) VALUES ('"
                    + client.getLogin() + "','" + client.getPassword() + "')";
            return !statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // checking client by login and password; log and pass match to db values - true, and false if not
    public boolean checkClient(Client client) {
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE name = '" + client.getLogin()
                    + "' AND password = '" + client.getPassword() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String checkLogin = resultSet.getString("name");
                String checkPassword = resultSet.getString("password");
                if (checkLogin.equals(client.getLogin()) && checkPassword.equals(client.getPassword())) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
