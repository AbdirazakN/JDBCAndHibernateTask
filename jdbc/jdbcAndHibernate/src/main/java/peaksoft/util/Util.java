package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:postgresql://localhost:5432/users";
    private static final String username = "postgres";
    private static final String password = "postgres00";

    public static Connection getConnection() {

        Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Successfully Connected!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return connection;
    }

}
