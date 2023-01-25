package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String query = """
                    create table users(
                    id serial primary key,
                    name varchar(50) not null,
                    surname varchar(50) not null ,
                    age smallint not null);
                    """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Successfully created table Users!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                                    drop table users;
                    """);
            System.out.println("Successfully deleted table Users!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                insert into users(name,surname,age)values (?,?,?);
                """)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.execute();
            System.out.println("Successfully saved!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                DELETE FROM users WHERE id = ?;
                """)){
            preparedStatement.setLong(1,id);
            int i = preparedStatement.executeUpdate();
            if (i>0){
                System.out.println("Successfully deleted!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String query = """
                select * from users;
                """;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    truncate table users;""");
            System.out.println("Successfully cleaned!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}