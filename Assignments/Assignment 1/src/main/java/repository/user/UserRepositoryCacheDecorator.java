package repository.user;

import model.User;
import model.validation.Notification;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class UserRepositoryCacheDecorator extends UserRepositoryDecorator{
    private final Cache<User> cache;

    public UserRepositoryCacheDecorator(UserRepository userRepository) {
        super(userRepository);
        cache = new Cache<>();
    }

    @Override
    public List<User> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<User> allUsers = decoratedRepository.findAll();
        cache.save(allUsers);
        return allUsers;
    }

    @Override
    public User findById(Long id)  {
        try {
            return decoratedRepository.findById(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        return decoratedRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean create(User user) {

        cache.invalidateCache();
        return decoratedRepository.create(user);
    }

    @Override
    public boolean update(User user) {

        cache.invalidateCache();
        return decoratedRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        cache.invalidateCache();
        decoratedRepository.delete(id);
    }

    @Override
    public void deleteAll(){
        cache.invalidateCache();
        decoratedRepository.deleteAll();
    }
}
