package jdbc;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

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
    private static String mongodbUrl;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            try (InputStream inputStream = Files.newInputStream(Paths.get(".env"))) {
                Properties properties = new Properties();
                properties.load(inputStream);
                user = properties.getProperty("USER");
                password = properties.getProperty("PASSWORD");
                url = properties.getProperty("URL");
                mongodbUrl = properties.getProperty("MONGODB_URL");

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do PostgreSQL não encontrado!", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo .env: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, user, password);
    }

    public static MongoClient getMongoClient() throws SQLException {
        return MongoClients.create(new ConnectionString(mongodbUrl));
    }
}
