package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDaoHibImpl<T> implements GenericDao<T> {

    @Override
    public int add(T t) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
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

            Transaction transaction = session.beginTransaction();
            List<T> objects = (List<T>) session
                    .createQuery("From " + entityClass.getName())
                    .list();
            transaction.commit();
            return objects;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public int delete(Class<T> entityClass, Long id) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {

            Transaction transaction = session.beginTransaction();
            session.delete(get(entityClass, id).get());
            transaction.commit();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(T t) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {

            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public Optional<T> get(Class<T> entityClass, Long id) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            Optional<T> object = Optional.of(session.get(entityClass, id));
            transaction.commit();
            return object;
        } catch (HibernateException e) {
            return Optional.empty();
        }
    }
}
