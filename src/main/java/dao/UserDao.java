package dao;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private static final Connection connection = DbConnector.connect();

    public static int addClient(Client client) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(name, password, email, role_id) VALUES (?, ?, ?, ?)");
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            statement.setString(3, client.getEmail());
            statement.setLong(4, 2);
            int status = statement.executeUpdate();
            statement.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // checking client by login and password; if log and pass match to db values - true, and false if not
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
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE name = ?");
            statement.setString(1, name);
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int editClient(Client client) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET password = ? WHERE name = ?");
            statement.setString(1, client.getPassword());
            statement.setString(2, client.getLogin());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Optional<Client> getClientByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT users.password, users.email, roles.role FROM users inner join " +
                            "roles on users.role_id = roles.id WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Client newClient;
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                newClient = new Client(name, password, email, Client.Role.valueOf(role));
                return Optional.of(newClient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
