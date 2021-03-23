package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator{
    private final Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository) {
        super(clientRepository);
        cache = new Cache<>();
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> allClients = decoratedRepository.findAll();
        cache.save(allClients);
        return allClients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public Client findByName(String name) throws EntityNotFoundException {
        return decoratedRepository.findByName(name);
    }

    @Override
    public Client findBySsn(String ssn) throws EntityNotFoundException {
        return decoratedRepository.findBySsn(ssn);
    }

    @Override
    public boolean create(Client client) {
        cache.invalidateCache();
        return decoratedRepository.create(client);
    }

    @Override
    public boolean update(Client client) {
        cache.invalidateCache();
        return decoratedRepository.update(client);
    }

    @Override
    public void deleteAll() {
        cache.invalidateCache();
        decoratedRepository.deleteAll();
    }
}
