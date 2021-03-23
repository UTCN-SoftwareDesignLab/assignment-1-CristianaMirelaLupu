package service.account;

import model.Account;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMock;

import static org.junit.Assert.*;

public class AccountServiceImplTest {

    private AccountService accountService;

    @Before
    public void setup() {
        accountService = new AccountServiceImpl(new AccountRepositoryMock());
    }

    @Test
    public void findAll() {
        assertEquals(0, accountService.findAll().size());
    }

    @Test
    public void findByIdEx() throws Exception {
       accountService.findById(1L);
    }

    @Test
    public void save() {
        assertTrue(accountService.create(new Account()));
    }

    @Test
    public void deleteAll(){
        accountService.deleteAll();
    }
}