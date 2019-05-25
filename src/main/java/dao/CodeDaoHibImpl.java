package dao;

import model.BuyCodeConfirmation;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;

public class CodeDaoHibImpl extends GenericDaoHibImpl<BuyCodeConfirmation> implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeDaoHibImpl.class);
    private static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public Optional<BuyCodeConfirmation> getByValue(Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            Query queryFindByValue = session.
                    createQuery("From BuyCodeConfirmation Where order_id = :order_id");
            queryFindByValue.setParameter("order_id", orderId);
            List<BuyCodeConfirmation> findCodeByValue = queryFindByValue.list();
            tx1.commit();
            if (findCodeByValue.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(findCodeByValue.get(0));
            }
        } catch (Exception e) {
            logger.error("Can't getByLogin code by login " + orderId, e);
            return Optional.empty();
        }
    }
}
