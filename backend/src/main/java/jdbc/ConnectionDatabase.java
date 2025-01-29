package jdbc;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.minio.MinioClient;

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
    private static String minioUrl;
    private static String minioUser;
    private static String minioPassword;

    static {

        try (InputStream inputStream = Files.newInputStream(Paths.get(".env"))) {
            Properties properties = new Properties();
            properties.load(inputStream);
            user = properties.getProperty("USER");
            password = properties.getProperty("PASSWORD");
            url = properties.getProperty("URL");
            mongodbUrl = properties.getProperty("MONGODB_URL");
            minioUrl = properties.getProperty("MINIO_URL");
            minioUser = properties.getProperty("MINIO_USER");
            minioPassword = properties.getProperty("MINIO_PASS");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo .env: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, user, password);
    }

    public static MongoClient getMongoClient() {
        return MongoClients.create(new ConnectionString(mongodbUrl));
    }

    public static MinioClient getMinioClient(){
        return MinioClient.builder().endpoint(minioUrl).credentials(minioUser, minioPassword).build();
    }
}
