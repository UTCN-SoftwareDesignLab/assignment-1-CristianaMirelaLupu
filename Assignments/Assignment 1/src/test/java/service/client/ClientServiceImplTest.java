package service.client;

import model.Account;
import model.Client;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;

import repository.client.ClientRepositoryMock;

import static org.junit.Assert.*;

public class ClientServiceImplTest {

    private ClientService clientService;

    @Before
    public void setup() {
        clientService = new ClientServiceImpl(new ClientRepositoryMock());
    }

    @Test
    public void findAll() {
        assertEquals(0, clientService.findAll().size());
    }

    @Test
    public void findByIdEx() throws Exception {
        clientService.findById(1L);
    }

    @Test
    public void create() {
        assertTrue(clientService.create(new Client()));
    }

    @Test
    public void deleteAll(){
        clientService.deleteAll();
    }
}