package repository.user;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.util.List;

public class UserRepositoryMockTest {

    private static UserRepository userRepository;

    @BeforeClass
    public static void setupClass() {

        JDBConnectionWrapper connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(true);
        userRepository = new UserRepositoryCacheDecorator(new UserRepositoryMock());
    }

    @Before
    public void setup() {

        userRepository.deleteAll();
    }

    @Test
    public void findAll() {
        List<User> noUsers = userRepository.findAll();
        Assert.assertTrue(noUsers.isEmpty());
    }

    @Test
    public void findById() throws EntityNotFoundException {

        User user1 = new UserBuilder()
                .setId(1L)
                .setName("Ivan")
                .setUsername("ivan@99")
                .setPassword("ivan@99")
                .build();

        User user2 = new UserBuilder()
                .setId(2L)
                .setName("Marin")
                .setUsername("@99")
                .setPassword("@99")
                .build();

        User user3 = new UserBuilder()
                .setId(3L)
                .setName("Marina")
                .setUsername("99")
                .setPassword("99")
                .build();

        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);

        User user = null;

        List<User> noUsers = userRepository.findAll();
        User u0 = noUsers.get(0);

        for (User u: noUsers) {
            if(u.getId().equals(userRepository.findById(u0.getId()).getId())) {
                user = u;
            }
        }
    }

    @Test
    public void create() {
        User userNo = new UserBuilder()
                .setId(1L)
                .setName("Ivan")
                .setUsername("ivan@99")
                .setPassword("ivan@99")
                .build();

        Assert.assertTrue(userRepository.create(userNo));
    }

    @Test
    public void update() throws EntityNotFoundException {

        User user1 = new UserBuilder()
                .setId(1L)
                .setName("Ivan")
                .setUsername("ivan@99")
                .setPassword("ivan@99")
                .build();

        User user2 = new UserBuilder()
                .setId(2L)
                .setName("Marin")
                .setUsername("@99")
                .setPassword("@99")
                .build();

        User user3 = new UserBuilder()
                .setId(3L)
                .setName("Marina")
                .setUsername("99")
                .setPassword("99")
                .build();

        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);

        User user = user1;

        List<User> noUsers = userRepository.findAll();
        User u0 = noUsers.get(0);

        for (User u: noUsers) {
            if(u.equals(userRepository.findById(1L))) {
                user = new UserBuilder()
                        .setId(u.getId())
                        .setName(u.getName())
                        .setPassword(u.getPassword())
                        .build();
            }
        }

        if (user != null) {
            user.setName("Phillipe");
        }

        Assert.assertTrue(userRepository.update(user));
    }

    @Test
    public void deleteAll() {
        userRepository.deleteAll();
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }
}