package repository.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
    User findById(Long id) throws EntityNotFoundException;
    Notification<User> findByUsernameAndPassword(String username, String password);
    boolean create(User user);
    boolean update(User user);
    void delete(Long id);
    void deleteAll();

}
