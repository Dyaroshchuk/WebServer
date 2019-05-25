package dao;

import model.Client;

import java.util.Optional;

public interface ClientDao extends GenericDao<Client> {

    Optional<Client> getClientByLogin(String login);
}
