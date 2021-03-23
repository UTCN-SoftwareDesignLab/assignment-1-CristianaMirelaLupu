package service.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById (Long id) throws EntityNotFoundException;
    Notification<User> findByNameAndPassword(String name, String password);
    boolean create (User user, String role_title);
    boolean update (User user);
    void delete(Long id);
    void deleteAll();

}
