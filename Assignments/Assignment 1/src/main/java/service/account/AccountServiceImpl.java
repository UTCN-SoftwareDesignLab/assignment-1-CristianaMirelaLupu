package service.account;

import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public boolean create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public boolean update(Account account) {
        return accountRepository.update(account);
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(id);
    }

    @Override
    public void deleteAll(){
        accountRepository.deleteAll();
    }
}
