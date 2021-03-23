package repository.account;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMock implements AccountRepository {

    private List<Account> accounts;

    public AccountRepositoryMock() {
        accounts = new ArrayList<>();
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        return accounts.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public boolean create(Account account) {
        return accounts.add(account);
    }

    @Override
    public boolean update(Account account) {
        Account old = findById(account.getId());

        if (old == null ){
            return false;
        }
        return update(account);
    }

    @Override
    public void deleteAll() {
        accounts.clear();
    }

    @Override
    public void delete(Long id) {
        accounts.clear();
    }
}
