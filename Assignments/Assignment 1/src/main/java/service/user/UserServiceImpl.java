package service.user;

import model.Role;
import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public UserServiceImpl(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id);
    }

    @Override
    public Notification <User> findByNameAndPassword(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

    @Override
    public boolean create(User user, String role_title) {
        Role role = rightsRolesRepository.findRoleByTitle(role_title);
        List <Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.create(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteAll(){userRepository.deleteAll();}
}
