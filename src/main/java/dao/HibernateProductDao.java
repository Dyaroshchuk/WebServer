package dao;

import model.Product;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernateProductDao implements ProductDao {
    @Override
    public int addProduct(Product product) {
        return 0;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> users = (List<Product>)  HibernateSessionFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public int deleteProduct(Long id) {
        return 0;
    }

    @Override
    public int editProduct(Product product) {
        return 0;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }
}
