package service.user;


import model.Right;
import model.Role;
import model.User;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;

import repository.security.RightsRolesRepository;
import repository.user.UserRepositoryMock;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    private UserService userService;

    @Before
    public void setup() {
        userService = new UserServiceImpl(new UserRepositoryMock(), new RightsRolesRepository() {
            @Override
            public void addRole(String role) {

            }

            @Override
            public void addRight(String right) {

            }

            @Override
            public Role findRoleByTitle(String role) {
                return null;
            }

            @Override
            public Role findRoleById(Long id_role) {
                return null;
            }

            @Override
            public Right findRightByTitle(String right) {
                return null;
            }

            @Override
            public void addRolesToUser(User user, List<Role> roles) {

            }

            @Override
            public List<Role> findRolesForUser(Long id_user) {
                return null;
            }

            @Override
            public void addRoleRight(Long id_role, Long id_right) {

            }
        });
    }

    @Test
    public void findAll() {
        assertEquals(0, userService.findAll().size());
    }

    @Test
    public void findByIdEx() throws Exception {
        userService.findById(1L);
    }
    @Test
    public void deleteAll(){
         userService.deleteAll();
    }

    @Test
    public void create() {
        assertTrue(userService.create(new User(), "admin"));
    }

}