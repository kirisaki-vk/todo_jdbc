package Connection;

public class Credentials {
    public final String HOST;
    public final String DATABASE;
    public final String USER;
    public final String PASSWORD;

    public Credentials() {
        this.HOST = "localhost";
        this.DATABASE = "yabank";
        this.USER = "postgres";
        this.PASSWORD = "1234";
    }
}
