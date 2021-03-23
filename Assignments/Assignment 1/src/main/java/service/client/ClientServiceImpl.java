package service.client;

import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }

    @Override
    public Client findBySsn(String ssn) throws EntityNotFoundException {
        return clientRepository.findBySsn(ssn);
    }

    @Override
    public boolean create(Client client) {
        return clientRepository.create(client);
    }

    @Override
    public boolean update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }
}
