package dao;

import model.Client;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HashPassword;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;

public class ClientDaoHibImpl implements DaoHibImpl<Client> {

    @Override
    public int add(Client client) {
        try {
            String password = client.getPassword();
            client.setPassword(HashPassword.getSecurePassword(password, client.getSalt()));
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(client);
            tx1.commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = (List<Client>) HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .createQuery("From Client")
                .list();
        return clients;
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
    public int edit(Client client) {
        try {
            String password = client.getPassword();
            client.setPassword(HashPassword.getSecurePassword(password, client.getSalt()));
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(client);
            tx1.commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            return 0;
        }
    }

    @Override
    public Optional<Client> get(Long id) {
        Optional<Client> client = Optional.of(HibernateSessionFactory.getSessionFactory().openSession().get(Client.class, id));
        return client;
    }

    @Override
    public Optional<Client> get(String login) {
            Session session;
            try {
                session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
            } catch (Exception e) {
                session = HibernateSessionFactory.getSessionFactory().openSession();
            }
            try {
                Query queryFindByLogin = session.createQuery("from Client where login = :login");
                queryFindByLogin.setParameter("login", login);
                List<Client> findUserByLogin = queryFindByLogin.list();
                if (findUserByLogin.size() == 0) {
                    return Optional.empty();
                } else {
                    return Optional.of(findUserByLogin.get(0));
                }
            } catch (Exception e) {
                return Optional.empty();
            }
    }
}
