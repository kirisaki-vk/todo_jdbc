package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection;

    public static void connectionProvider (Credentials credentials) throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgres://" + credentials.HOST + "/" + credentials.DATABASE,
                credentials.USER,
                credentials.PASSWORD
        );
    }
}
