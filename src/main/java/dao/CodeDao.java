package dao;

import model.BuyCodeConfirmation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeDao {

    private static final Connection connection = DbConnector.connect();

    public static int addProduct(BuyCodeConfirmation buyCodeConfirmation) {
        try {
                PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO buy_codes(value, clientName, product_id, creation_date, expiry_date) " +
                            "VALUES (?, ?, ?, NOW(), NOW() + INTERVAL 2 minute)");
            statement.setString(1, buyCodeConfirmation.getValue());
            statement.setString(2, buyCodeConfirmation.getClientName());
            statement.setLong(3, buyCodeConfirmation.getProductId());
            int status = statement.executeUpdate();
            statement.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean checkCode(BuyCodeConfirmation buyCodeConfirmation) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM buy_codes WHERE clientName = ?");
            statement.setString(1, buyCodeConfirmation.getClientName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String valueFromDb = resultSet.getString("value");
                Long productCodeFromDb = resultSet.getLong("product_id");
                BuyCodeConfirmation checkingBuyCodeConfirmation = new BuyCodeConfirmation(valueFromDb, buyCodeConfirmation.getClientName(), productCodeFromDb);
                if (checkingBuyCodeConfirmation.equals(buyCodeConfirmation)) {
                    return result = true;
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
