package repository.account;

import database.DatabaseConnectionFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AccountRepositoryMySQLTest {

    private static AccountRepository accountRepository;
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        accountRepository = new AccountRepositoryCacheDecorator(new AccountRepositoryMySQL(connection));
        clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMySQL(connection));
    }

    @Before
    public void setup() {
        clientRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    public void findAll() {
        List<Account> noAccounts = accountRepository.findAll();
        Assert.assertTrue(noAccounts.isEmpty());
    }

    @Test
    public void findById() throws EntityNotFoundException {

        Client client1 = new ClientBuilder()
                .setId(1L)
                .setName("Alin")
                .setSsn("0189138")
                .setAddress("cluj-napoca")
                .build();

        clientRepository.create(client1);

        Account account1 = new AccountBuilder()
                .setType("debit")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        Account account2 = new AccountBuilder()
                .setType("CREDIT")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        Account account3 = new AccountBuilder()
                .setType("CREDIT")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        accountRepository.create(account1);
        accountRepository.create(account2);
        accountRepository.create(account3);

        Account accounts = null;

        List<Account> noAccounts = accountRepository.findAll();
        Account a0 = noAccounts.get(0);

        for (Account a: noAccounts) {
            if(a.getId().equals(accountRepository.findById(a0.getId()).getId())) {
                accounts = a;
            }
        }
        accountRepository.deleteAll();
    }

    @Test
    public void create() {

        Client client1 = new ClientBuilder()
                .setId(1L)
                .setName("Alin")
                .setSsn("0189138")
                .setAddress("cluj-napoca")
                .build();

        clientRepository.create(client1);

        Account accountNo = new AccountBuilder()
                .setType("CREDIT")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        Assert.assertTrue(accountRepository.create(accountNo));
    }

    @Test
    public void update() throws EntityNotFoundException {

        Client client1 = new ClientBuilder()
                .setId(1L)
                .setName("Alin")
                .setSsn("0189138")
                .setAddress("cluj-napoca")
                .build();

        clientRepository.create(client1);

        Account account1 = new AccountBuilder()
                .setId(1L)
                .setType("debit")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        Account account2 = new AccountBuilder()
                .setId(2L)
                .setType("CREDIT")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        Account account3 = new AccountBuilder()
                .setId(3L)
                .setType("CREDIT")
                .setAmount((float) 29876.50)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(client1.getId())
                .build();

        accountRepository.create(account1);
        accountRepository.create(account2);
        accountRepository.create(account3);

        Account accounts = null;

        List<Account> noAccounts = accountRepository.findAll();
        Account a0 = noAccounts.get(0);

        for (Account a: noAccounts) {
            if(a.getId().equals(accountRepository.findById(a0.getId()).getId())) {
                accounts = a;
            }
        }

        accounts.setType("DEBITTT");
        Assert.assertTrue(accountRepository.update(accounts));
        accountRepository.deleteAll();
    }

    @Test
    public void deleteAll() {
        accountRepository.deleteAll();
        Assert.assertTrue(accountRepository.findAll().isEmpty());
    }
}
