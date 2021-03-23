package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();
    Account findById(Long id) throws EntityNotFoundException;
    boolean create(Account account);
    boolean update(Account account);
    void deleteAll();
    void delete(Long id);
}
