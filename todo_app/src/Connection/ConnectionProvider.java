package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    public static Connection getConnection (Credentials credentials) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgres://" + credentials.HOST + "/" + credentials.DATABASE,
                credentials.USER,
                credentials.PASSWORD
        );
    }

    public static Connection getConnection() throws SQLException{
        Credentials credentials = new Credentials();
        return DriverManager.getConnection(
                "jdbc:postgresql://" + credentials.HOST + "/" + credentials.DATABASE,
                credentials.USER,
                credentials.PASSWORD
        );
    }

}
