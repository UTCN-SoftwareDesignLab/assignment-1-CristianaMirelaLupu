package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.*;

public class AccountRepositoryMySQL implements AccountRepository {
    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {

        this.connection = connection;
    }
    @Override
    public List<Account> findAll() {
        String sql = "SELECT * from account";

        List<Account> accounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Account account = new AccountBuilder()
                        .setId(rs.getLong ("id"))
                        .setType(rs.getString("type"))
                        .setAmount(rs.getFloat("amount"))
                        .setCreationDate(rs.getDate("creationDate"))
                        .setClient(rs.getLong ("client_id"))
                        .build();
                accounts.add(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + ACCOUNT+ " where id =" + id;
            ResultSet rs = statement.executeQuery(fetchRoleSql);
            if (rs.next()) {
                   Account account = new AccountBuilder()
                           .setId(rs.getLong ("id"))
                           .setType(rs.getString("type"))
                           .setAmount(rs.getFloat("amount"))
                           .setCreationDate(rs.getDate("creationDate"))
                           .setClient(rs.getLong ("client_id"))
                        .build();

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, account.getType());
            insertStatement.setFloat(2, account.getAmount());
            insertStatement.setDate(3, account.getCreationDate());
            insertStatement.setLong(4, account.getClient());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            rs.next();
            long id_acc = rs.getLong(1);
            account.setId(id_acc);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE account SET  type = ? , amount = ? WHERE id = ?";

        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement(sql);
            updateStatement.setString(1, account.getType());
            updateStatement.setFloat(2, account.getAmount());
            updateStatement.setString(3, account.getId().toString());

            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from " + ACCOUNT+ " where id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account WHERE id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong ("id"))
                .setType(rs.getString("type"))
                .setAmount(rs.getFloat("amount"))
                .setCreationDate(rs.getDate("creationDate"))
                .setClient(rs.getLong ("client_id"))
                .build();
    }
}

