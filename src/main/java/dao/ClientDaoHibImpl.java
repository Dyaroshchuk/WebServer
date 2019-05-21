package dao;

import model.Client;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HashPassword;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoHibImpl implements DaoHibImpl<Client> {

    private static final Logger LOGGER = Logger.getLogger(ClientDaoHibImpl.class);
    private static final SessionFactory SESSION_FACTORY = HibernateSessionFactory.getSessionFactory();

    @Override
    public int add(Client client) {
        try (Session session = SESSION_FACTORY.openSession()) {

            String password = client.getPassword();
            client.setPassword(HashPassword.getSecurePassword(password, client.getSalt()));
            Transaction tx1 = session.beginTransaction();
            session.save(client);
            tx1.commit();
            return 1;
        } catch (HibernateException e) {
            LOGGER.info("can't add client", e);
            return 0;
        }
    }

    @Override
    public List<Client> getAll() {
        try (Session session = SESSION_FACTORY.openSession()) {

            List<Client> clients = (List<Client>) session
                    .createQuery("From Client")
                    .list();
            return clients;
        } catch (HibernateException e) {
            LOGGER.error("Can't get list of client", e);
            return new ArrayList<>();
        }
    }

    @Override
    public int delete(Long id) {
        try (Session session = SESSION_FACTORY.openSession()) {

            LOGGER.info("Admin deleting client" + id);
            Transaction tx1 = session.beginTransaction();
            Client client = get(id).get();
            session.delete(client);
            tx1.commit();
            session.flush();
            return 1;
        } catch (Exception e) {
            LOGGER.error("Can't delete client " + id, e);
            return 0;
        }
    }

    @Override
    public int edit(Client client) {
        try (Session session = SESSION_FACTORY.openSession()) {

            LOGGER.info("Admin editing client" + client.getId());
            String password = client.getPassword();
            client.setPassword(HashPassword.getSecurePassword(password, client.getSalt()));
            Transaction tx1 = session.beginTransaction();
            session.update(client);
            tx1.commit();
            session.flush();
            return 1;
        } catch (HibernateException e) {
            LOGGER.error("Can't editing client" + client.getId(), e);
            return 0;
        }
    }

    @Override
    public Optional<Client> get(Long id) {
        try (Session session = SESSION_FACTORY.openSession()) {

            Optional<Client> client = Optional.of(session.get(Client.class, id));
            return client;
        } catch (HibernateException e) {
            LOGGER.error("Can't get client by id " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> get(String login) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query queryFindByLogin = session.createQuery("from Client where login = :login");
            queryFindByLogin.setParameter("login", login);
            List<Client> findUserByLogin = queryFindByLogin.list();
            if (findUserByLogin.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(findUserByLogin.get(0));
            }
        } catch (Exception e) {
            LOGGER.error("Can't get client by login " + login, e);
            return Optional.empty();
        }
    }
}
