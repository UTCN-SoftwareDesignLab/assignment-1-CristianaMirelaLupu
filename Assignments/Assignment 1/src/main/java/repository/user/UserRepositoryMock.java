package repository.user;

import model.User;
import model.validation.Notification;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMock implements UserRepository {

    private final List <User> users;

    public UserRepositoryMock() {
        users = new ArrayList<>();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public boolean create(User user) {
        return users.add(user);
    }

    @Override
    public boolean update(User user) {
        User oldUser = findById(user.getId());
        if (oldUser == null ){
            return false;
        }
        users.remove(oldUser);
        users.add(user);
        return true;
    }

    @Override
    public void delete(Long id) {
        users.remove(findById(id));
    }

    @Override
    public void deleteAll(){
        users.clear();
    }
}
