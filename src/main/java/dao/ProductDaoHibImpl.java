package dao;

import model.Product;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;

public class ProductDaoHibImpl implements DaoHibImpl<Product> {

    @Override
    public int add(Product product) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(product);
            tx1.commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = (List<Product>) HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .createQuery("From Product")
                .list();
        return products;
    }

    @Override
    public int delete(Long id) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(get(id).get());
            tx1.commit();
            session.close();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int edit(Product product) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(product);
            tx1.commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        Optional<Product> product = Optional.of(HibernateSessionFactory.getSessionFactory().openSession().get(Product.class, id));
        return product;
    }

    @Override
    public Optional<Product> get(String name) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Product.class);
            Product product = (Product) criteria
                    .add(Restrictions.eq("name", name)).uniqueResult();
            return Optional.of(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
