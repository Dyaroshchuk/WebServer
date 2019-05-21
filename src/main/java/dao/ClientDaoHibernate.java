package dao;

import java.util.Optional;

public interface ClientDaoHibernate<Client> {

    Optional<Client> getByLogin(String login);
}
