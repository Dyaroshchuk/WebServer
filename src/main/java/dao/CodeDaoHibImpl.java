package dao;

import model.BuyCodeConfirmation;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;

public class CodeDaoHibImpl implements DaoHibImpl<BuyCodeConfirmation> {

    @Override
    public int add(BuyCodeConfirmation buyCodeConfirmation) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(buyCodeConfirmation);
            tx1.commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public List<BuyCodeConfirmation> getAll() {
        List<BuyCodeConfirmation> codes = (List<BuyCodeConfirmation>) HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .createQuery("From BuyCodeConfirmation")
                .list();
        return codes;
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
    public int edit(BuyCodeConfirmation buyCodeConfirmation) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(buyCodeConfirmation);
            tx1.commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public Optional<BuyCodeConfirmation> get(Long id) {
        Optional<BuyCodeConfirmation> codeConfirmation = Optional.of(HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .get(BuyCodeConfirmation.class, id));
        return codeConfirmation;
    }

    @Override
    public Optional<BuyCodeConfirmation> get(String value) {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(BuyCodeConfirmation.class);
            BuyCodeConfirmation buyCodeConfirmation = (BuyCodeConfirmation) criteria
                    .add(Restrictions.eq("value", value)).uniqueResult();
            return Optional.of(buyCodeConfirmation);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
