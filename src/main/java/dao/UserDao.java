package dao;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final Connection connection = DbConnector.connect();

    public static int addClient(Client client) {
        int status = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(name, password) VALUES (?,?)");
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            status = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // checking client by login and password; log and pass match to db values - true, and false if not
    public static boolean checkClient(Client client) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE name = ?");
            statement.setString(1, client.getLogin());
            ResultSet resultSet = statement.executeQuery();
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

    public static List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Client getClient = new Client();
                getClient.setLogin(resultSet.getString("name"));
                getClient.setPassword(resultSet.getString("password"));
                clients.add(getClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public static int deleteClient(String name) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE name = ?");
            statement.setString(1, name);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int editClient(Client client) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET password = ? WHERE name = ?");
            statement.setString(1, client.getPassword());
            statement.setString(2, client.getLogin());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Client getClientByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE name = ? ");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Client newClient = new Client();
            while (resultSet.next()) {
                String password = resultSet.getString("password");
                newClient = new Client(name, password);
            }
            return newClient;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
