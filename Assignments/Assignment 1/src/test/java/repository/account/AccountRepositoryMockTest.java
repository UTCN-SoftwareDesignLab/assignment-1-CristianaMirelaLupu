package repository.account;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class AccountRepositoryMockTest {
    private static AccountRepository accountRepository;

   @BeforeClass
   public static void setupClass() {
       accountRepository = new AccountRepositoryCacheDecorator(new AccountRepositoryMock());
       JDBConnectionWrapper connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(true);
   }

    @Before
    public void setup() {
        accountRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Account> allAccounts = accountRepository.findAll();

        Assert.assertTrue(allAccounts.isEmpty());

        int nrInserted = 5;
        for (int i = 0; i < nrInserted; i++) {
            accountRepository.create(new AccountBuilder().setId(new Random().nextLong()).build());
        }

        List<Account> newAccounts = accountRepository.findAll();

        Assert.assertEquals(nrInserted, newAccounts.size());
    }

    @Test
    public void findById() throws EntityNotFoundException {

        long notToBeFound = -1;
        Account notFound = accountRepository.findById(notToBeFound);
        Assert.assertNull(notFound);

        long idToBeFound = 1;
        String accountType = "whatever";
        Account toBeFound =  new AccountBuilder()
                .setId(idToBeFound)
                .setType("whatever")
                .setAmount((float) 2.30)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(-1L)
                .build();

        accountRepository.create(toBeFound);

        Account found = accountRepository.findById(idToBeFound);
        Assert.assertNotNull(found);
        Assert.assertEquals(accountType, found.getType());
    }

    @Test
    public void create() throws EntityNotFoundException {

        long id = -5L;
        Assert.assertTrue(accountRepository.create(new AccountBuilder()
                .setId(id)
                .setType("debit")
                .setAmount((float) 2.30)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(-1L)
                .build())
        );
        Assert.assertNotNull(accountRepository.findById(id));
    }

    @Test
    public void update() {
        String accountType = "whatever";
        Account toBeFound =  new AccountBuilder()
                .setType("whatever")
                .setAmount((float) 2.30)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClient(-1L)
                .build();

        accountRepository.create(toBeFound);
        toBeFound.setType("changed");
    }

    @Test
    public void deleteAll() {
       accountRepository.deleteAll();
       Assert.assertTrue(accountRepository.findAll().isEmpty());
    }
}