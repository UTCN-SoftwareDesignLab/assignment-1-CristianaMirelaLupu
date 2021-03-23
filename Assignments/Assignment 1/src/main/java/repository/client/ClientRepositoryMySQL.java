package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {

        this.connection = connection;
    }
    @Override
    public List<Client> findAll() {
        String sql = "SELECT * from client";

        List<Client> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Client client = new ClientBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setSsn(resultSet.getString("ssn"))
                        .setAddress(resultSet.getString("address"))
                        .build();
                clients.add(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + CLIENT+ " where id =" + id;
            ResultSet userResultSet = statement.executeQuery(fetchRoleSql);
            if (userResultSet.next()) {
                Client client = new ClientBuilder()
                        .setId(userResultSet.getLong("id"))
                        .setName(userResultSet.getString("name"))
                        .setSsn(userResultSet.getString("ssn"))
                        .setAddress(userResultSet.getString("address"))
                        .build();

                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findByName(String name) {

        String sql = "SELECT * from client WHERE name = " + name;

        Client client = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                client = getClientFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public Client findBySsn(String ssn) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + CLIENT+ " where ssn =" + ssn;
            ResultSet userResultSet = statement.executeQuery(fetchRoleSql);
            if (userResultSet.next()) {
                Client client = new ClientBuilder()
                        .setName(userResultSet.getString("name"))
                        .setSsn(userResultSet.getString("ssn"))
                        .setAddress(userResultSet.getString("address"))
                        .build();

                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Client client) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, client.getName());
            insertUserStatement.setString(2, client.getSsn());
            insertUserStatement.setString(3, client.getAddress());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long id_client = rs.getLong(1);
            client.setId(id_client);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        String sql = "UPDATE client SET  name = ? , address = ? WHERE id = ?";

        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement(sql);
            updateStatement.setString(1, client.getName());
            updateStatement.setString(2, client.getAddress());
            updateStatement.setString(3, client.getId().toString());

            updateStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from client WHERE id >= 0";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong ("id"))
                .setName(rs.getString("name"))
                .setSsn(rs.getString("ssn"))
                .setAddress(rs.getString("address"))
                .build();
    }
}
