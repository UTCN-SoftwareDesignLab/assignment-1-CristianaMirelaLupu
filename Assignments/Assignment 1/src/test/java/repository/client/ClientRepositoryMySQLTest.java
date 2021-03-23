package repository.client;

import database.DatabaseConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.sql.Connection;
import java.util.List;

public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMySQL(connection));
    }

    @Before
    public void setup() {
        clientRepository.deleteAll();
    }

    @Test
    public void findAll() {
        List<Client> noClients = clientRepository.findAll();
        Assert.assertTrue(noClients.isEmpty());
    }

    @Test
    public void findById() throws EntityNotFoundException {

        Client client1 = new ClientBuilder()
                .setName("Alin")
                .setSsn("0189138")
                .setAddress("cluj-napoca")
                .build();

        Client client2 = new ClientBuilder()
                .setName("Alina")
                .setSsn("014567889138")
                .setAddress("cluj")
                .build();

        Client client3 = new ClientBuilder()
                .setName("Ana")
                .setSsn("01898765138")
                .setAddress("cj")
                .build();

        Client client = null;
        clientRepository.create(client1);
        clientRepository.create(client2);
        clientRepository.create(client3);

        List<Client> noClients = clientRepository.findAll();
        Client c0 = noClients.get(0);

        for (Client c: noClients) {
            if(c.getId().equals(clientRepository.findById(c0.getId()).getId())) {
                client = c;
            }
        }
    }

    @Test
    public void create() {
        Client clientNo = new ClientBuilder()
                .setName("whatever")
                .setSsn("0189138")
                .setAddress("cluj-napoca")
                .build();

        Assert.assertTrue(clientRepository.create(clientNo));
    }

    @Test
    public void update() throws EntityNotFoundException {
        Client client1 = new ClientBuilder()
                .setName("Alin")
                .setSsn("0189138")
                .setAddress("cluj-napoca")
                .build();

        Client client2 = new ClientBuilder()
                .setName("Alina")
                .setSsn("014567889138")
                .setAddress("cluj")
                .build();

        Client client3 = new ClientBuilder()
                .setName("Ana")
                .setSsn("01898765138")
                .setAddress("cj")
                .build();

        Client client = null;
        clientRepository.create(client1);
        clientRepository.create(client2);
        clientRepository.create(client3);

        List<Client> noClients = clientRepository.findAll();
        Client c0 = noClients.get(0);

        for (Client c: noClients) {
            if(c.getId().equals(clientRepository.findById(c0.getId()).getId())) {
                client = c;
            }
        }

        client.setAddress("ADDDDDDDDRESSSSSSS");

        Assert.assertTrue(clientRepository.update(client));
    }

    @Test
    public void deleteAll() {
        clientRepository.deleteAll();
    }
}