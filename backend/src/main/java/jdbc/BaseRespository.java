package jdbc;

import com.mongodb.client.MongoClient;

import java.sql.SQLException;
import java.sql.Connection;

public abstract class BaseRespository {

    private static final Connection connection;
    private static final MongoClient mongoClient;

    static {
        try {
            connection = ConnectionDatabase.getConnection();
            mongoClient = ConnectionDatabase.getMongoClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }
}
