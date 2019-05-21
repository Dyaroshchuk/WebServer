package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericDaoHibImpl<T> implements GenericDao<T> {
    @Override
    public int add(T t) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(t);
            tx1.commit();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public List<T> getAll(Class<T> entityClass) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {

            List<T> products = (List<T>) session
                    .createQuery("From " + entityClass.getName())
                    .list();
            session.flush();
            return products;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int edit(T t) {
        return 0;
    }

    @Override
    public Optional<T> get(Long id) {
        return Optional.empty();
    }
}
