package dao;

import model.Client;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;

public class ClientDaoHibImpl extends GenericDaoHibImpl<Client> implements ClientDao {

    private static final Logger logger = Logger.getLogger(ClientDaoHibImpl.class);

    public Optional<Client> getClientByLogin(String login) {
        try (Session session = HibernateSessionFactory
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            Query queryFindByLogin = session.createQuery("from Client where login = :login");
            queryFindByLogin.setParameter("login", login);
            List<Client> findUserByLogin = queryFindByLogin.list();
            transaction.commit();
            if (findUserByLogin.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(findUserByLogin.get(0));
            }
        } catch (Exception e) {
            logger.error("Can't getClientByLogin client by login " + login, e);
            return Optional.empty();
        }
    }
}
