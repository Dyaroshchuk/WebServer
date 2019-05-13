package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlProductDao implements ProductDao {

    private static final Connection connection = DbConnector.connect();

    @Override
    public int addProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO products(name, description, price) VALUES (?, ?, ?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            int status = statement.executeUpdate();
            statement.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product getProduct = new Product();
                getProduct.setId(resultSet.getLong("id"));
                getProduct.setName(resultSet.getString("name"));
                getProduct.setDescription(resultSet.getString("description"));
                getProduct.setPrice(resultSet.getDouble("price"));
                products.add(getProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int deleteProduct(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM products WHERE id = ?");
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int editProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE products SET name = ?, description = ?, price = ? WHERE id = ?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setLong(4, product.getId());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name, description, price FROM products WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Product newProduct = new Product();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                 newProduct = new Product(id, name, description, price);
            }
            return Optional.of(newProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
