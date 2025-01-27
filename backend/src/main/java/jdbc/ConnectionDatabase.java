package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class ConnectionDatabase {
    private static String user;
    private static String password;
    private static String url;

    static  {
        try (InputStream inputStream = Files.newInputStream(Paths.get("/.env"))) {
            Properties properties = new Properties();
            properties.load(inputStream);
            user = properties.getProperty("USER");
            password = properties.getProperty("PASSWORD");
            url = properties.getProperty("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try(Connection con = DriverManager.getConnection(url, user, password)){
            return con;
        } catch (SQLException e){
            throw new SQLException(e);
        }
    }
}
