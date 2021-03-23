package repository.client;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.util.List;
import java.util.Random;

public class ClientRepositoryMockTest {

    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMock());
        JDBConnectionWrapper connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(true);
    }

    @Before
    public void setup() {
        clientRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Client> allClients = clientRepository.findAll();

        Assert.assertTrue(allClients.isEmpty());

        int nrInserted = 5;
        for (int i = 0; i < nrInserted; i++) {
            clientRepository.create(new ClientBuilder().setId(new Random().nextLong()).build());
        }

        List<Client> newClients = clientRepository.findAll();

        Assert.assertEquals(nrInserted, newClients.size());
    }

    @Test
    public void findById() throws EntityNotFoundException {

        long notToBeFound = -1;
        Client notFound = clientRepository.findById(notToBeFound);
        Assert.assertNull(notFound);

        long idToBeFound = 1;
        String clientName = "whatever";
        Client toBeFound =  new ClientBuilder()
                .setId(idToBeFound)
                .setName("whatever")
                .setSsn("0189137")
                .setAddress("cluj")
                .build();

        clientRepository.create(toBeFound);

        Client found = clientRepository.findById(idToBeFound);
        Assert.assertNotNull(found);
        Assert.assertEquals(clientName, found.getName());
    }

    @Test
    public void create() throws EntityNotFoundException {

        long id = -5L;
        Assert.assertTrue(clientRepository.create(new ClientBuilder()
                .setId(id)
                .setName("whatever")
                .setSsn("0189137")
                .setAddress("cluj")
                .build())
        );
        Assert.assertNotNull(clientRepository.findById(id));

    }

    @Test
    public void deleteAll() {
        clientRepository.deleteAll();
        Assert.assertTrue(clientRepository.findAll().isEmpty());
    }

}