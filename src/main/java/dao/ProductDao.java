package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    int addProduct(Product product);

    List<Product> getAllProducts();

    int deleteProduct(Long id);

    int editProduct(Product product);

    Optional<Product> getProductById(Long id);
}
