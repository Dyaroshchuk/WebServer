package dao;

import model.BuyCodeConfirmation;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CodeGenericDao implements GenericDao<BuyCodeConfirmation> {

    private static final Logger LOGGER = Logger.getLogger(CodeGenericDao.class);
    private static final SessionFactory SESSION_FACTORY = HibernateSessionFactory.getSessionFactory();

    @Override
    public int delete(Long id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Transaction tx1 = session.beginTransaction();
            session.delete(get(id).get());
            tx1.commit();
            return 1;
        } catch (Exception e) {
            LOGGER.error("Can't delete code " + id, e);
            return 0;
        }
    }

    @Override
    public int edit(BuyCodeConfirmation buyCodeConfirmation) {
        try (Session session = SESSION_FACTORY.openSession()) {

            Transaction tx1 = session.beginTransaction();
            session.update(buyCodeConfirmation);
            tx1.commit();
            session.flush();
            return 1;
        } catch (HibernateException e) {
            LOGGER.error("Can't editing code" + buyCodeConfirmation.getId(), e);
            return 0;
        }
    }

    @Override
    public Optional<BuyCodeConfirmation> get(Long id) {
        try (Session session = SESSION_FACTORY.openSession()) {

            Optional<BuyCodeConfirmation> codeConfirmation = Optional.of(session
                    .get(BuyCodeConfirmation.class, id));
            return codeConfirmation;
        } catch (HibernateException e) {
            LOGGER.error("Can't getByLogin code by id " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<BuyCodeConfirmation> get(String value) {
        try (Session session = SESSION_FACTORY.openSession()) {

            Query queryFindByValue = session.
                    createQuery("From BBuyCodeConfirmation Where value = :value");
            queryFindByValue.setParameter("value", value);
            List<BuyCodeConfirmation> findCodeByValue = queryFindByValue.list();
            if (findCodeByValue.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(findCodeByValue.get(0));
            }
        } catch (Exception e) {
            LOGGER.error("Can't getByLogin code by login " + value, e);
            return Optional.empty();
        }
    }
}
