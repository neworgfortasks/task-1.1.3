package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public static Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String createTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "age INT)";
            statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String dropTable = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name,last_name,age) values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User with name " + name + " was added to database.");
    }

    public void removeUserById(long id) {
        String removeUserByID = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserByID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectAllUsers = "SELECT * FROM users";
        try (ResultSet resultSet = connection.createStatement().executeQuery(selectAllUsers)) {
            while (resultSet.next()) {
                userList.add(new User(resultSet.getLong("id"),
                        resultSet.getByte("age"),
                        resultSet.getString("last_name"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String cleanTable = "DELETE FROM users";
            statement.executeUpdate(cleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
