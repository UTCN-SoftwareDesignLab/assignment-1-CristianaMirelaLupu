package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();
    Client findById(Long id) throws EntityNotFoundException;
    Client findByName (String name) throws EntityNotFoundException;
    Client findBySsn (String ssn)throws EntityNotFoundException;
    boolean create(Client client);
    boolean update(Client client);
    void deleteAll();
}
