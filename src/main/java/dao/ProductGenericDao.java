package dao;

import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductGenericDao implements GenericDao<Product> {

    private static final Logger LOGGER = Logger.getLogger(ProductGenericDao.class);

    @Override
    public int delete(Long id) {
        try {
            Session session = HibernateSessionFactory
                    .getSessionFactory()
                    .openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(get(id).get());
            tx1.commit();
            session.close();
            return 1;
        } catch (Exception e) {
            LOGGER.error("Can't delete product " + id, e);
            return 0;
        }
    }

    @Override
    public int edit(Product product) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {

            Transaction tx1 = session.beginTransaction();
            session.update(product);
            tx1.commit();
            session.flush();
            return 1;
        } catch (HibernateException e) {
            LOGGER.error("Can't editing product " + product.getId(), e);
            return 0;
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {

            Optional<Product> product = Optional.of(session.get(Product.class, id));
            return product;
        } catch (HibernateException e) {
            LOGGER.error("Can't getByLogin product by id " + id, e);
            return Optional.empty();
        }
    }


//    public Optional<Product> get(String name) {
//        try (Session session = HibernateSessionFactory
//                .getSessionFactory()
//                .openSession()) {
//
//            Query queryFindByLogin = session
//                    .createQuery("From Product Where name = :name");
//            queryFindByLogin.setParameter("name", name);
//            List<Product> productsFromDB = queryFindByLogin.list();
//            if (productsFromDB.size() == 0) {
//                return  Optional.empty();
//            } else {
//                return Optional.of(productsFromDB.get(0));
//            }
//        } catch (Exception e) {
//            LOGGER.error("Can't getByLogin product by login " + name, e);
//            return Optional.empty();
//        }
//    }
}
