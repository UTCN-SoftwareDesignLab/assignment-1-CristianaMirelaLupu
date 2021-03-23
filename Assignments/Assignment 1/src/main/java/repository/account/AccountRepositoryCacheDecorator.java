package repository.account;

import model.Account;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class AccountRepositoryCacheDecorator extends AccountRepositoryDecorator{

    private final Cache<Account> cache;

    public AccountRepositoryCacheDecorator(AccountRepository accountRepository) {
        super(accountRepository);
        cache = new Cache<>();
    }

    @Override
    public List<Account> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Account> allAccounts = decoratedRepository.findAll();
        cache.save(allAccounts);
        return allAccounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean create(Account account) {
        boolean somethingChanged = decoratedRepository.create(account);
        if (somethingChanged) {
            cache.invalidateCache();
        }
        return somethingChanged;
    }

    @Override
    public boolean update(Account account) {
        cache.invalidateCache();
        return decoratedRepository.update(account);
    }

    @Override
    public void deleteAll() {
        cache.invalidateCache();
        decoratedRepository.deleteAll();
    }

    @Override
    public void delete(Long id) {
        cache.invalidateCache();
        decoratedRepository.delete(id);
    }
}
