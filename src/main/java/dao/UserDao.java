package dao;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    int addClient(Client client);

    List<Client> getAllClients();

    int deleteClient(Long id);

    int editClient(Client client);

    Optional<Client> getClientById(Long id);

    Optional<Client> getClientByLogin(String login);
}
