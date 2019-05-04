package dao;

import model.Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeDao {

    private static final Connection connection = DbConnector.connect();

    public static int addProduct(Code code) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO buy_codes(value, clientName, product_id) VALUES (?, ?, ?)");
            statement.setString(1, code.getValue());
            statement.setString(2, code.getClientName());
            statement.setLong(3, code.getProductId());
            int status = statement.executeUpdate();
            statement.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean checkCode(Code code) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM buy_codes WHERE clientName = ?");
            statement.setString(1, code.getClientName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String valueFromDb = resultSet.getString("value");
                Long productCodeFromDb = resultSet.getLong("product_id");
                Code checkingCode = new Code(valueFromDb, code.getClientName(), productCodeFromDb);
                if (checkingCode.equals(code)) {
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
