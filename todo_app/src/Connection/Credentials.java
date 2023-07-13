package Connection;

public class Credentials {
    public final String HOST;
    public final String DATABASE;
    public final String USER;
    public final String PASSWORD;

    public Credentials() {
        this.HOST = "localhost";
        this.DATABASE = "todo_app";
        this.USER = "postgres";
        this.PASSWORD = "1234";
    }

    public Credentials(String database) {
        this.HOST = "localhost";
        this.DATABASE = database;
        this.USER = "postgres";
        this.PASSWORD = "1234";
    }

    public Credentials(String user, String password) {
        this.HOST = "localhost";
        this.DATABASE = "todo_app";
        this.USER = user;
        this.PASSWORD = password;
    }
}
