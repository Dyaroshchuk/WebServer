package dao;

import model.Client;

import java.util.List;
import java.util.Optional;

public class HibernateUserDao implements UserDao {
    @Override
    public int addClient(Client client) {
        return 0;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public int deleteClient(Long id) {
        return 0;
    }

    @Override
    public int editClient(Client client) {
        return 0;
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> getClientByLogin(String login) {
        return Optional.empty();
    }
}
