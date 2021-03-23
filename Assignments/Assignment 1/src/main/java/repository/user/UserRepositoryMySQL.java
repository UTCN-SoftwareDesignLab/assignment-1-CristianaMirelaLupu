package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

        @Override
        public List<User> findAll() {
            String sql = "SELECT * from user";

            List<User> users = new ArrayList<>();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                        User user = new UserBuilder()
                                .setId(resultSet.getLong("id"))
                                .setUsername(resultSet.getString("username"))
                                .setPassword(resultSet.getString("password"))
                                .setRoles(rightsRolesRepository.findRolesForUser(resultSet.getLong("id")))
                                .build();
                        users.add(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return users;
        }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + USER+ " where id =" + id;
            ResultSet userResultSet = statement.executeQuery(fetchRoleSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setId(userResultSet.getLong("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles( rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();

            return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles( rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }


    @Override
    public boolean update(User user) {
        String sql = "UPDATE user SET  username = ? , password = ? WHERE id = ?";

        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement(sql);
            updateStatement.setString(1, user.getUsername());
            updateStatement.setString(2, user.getPassword());
            updateStatement.setString(3, user.getId().toString());

            updateStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long id_user = rs.getLong(1);
            user.setId(id_user);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from " + USER+ " where id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
