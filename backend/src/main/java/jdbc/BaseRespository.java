package jdbc;

import java.sql.SQLException;
import java.sql.Connection;

public abstract class BaseRespository {

    private static final Connection connection;

    static {
        try {
            connection = ConnectionDatabase.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
