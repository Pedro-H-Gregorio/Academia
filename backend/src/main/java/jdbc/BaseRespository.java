package jdbc;

import jdbc.interfaces.JDBCRepository;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

public class BaseRespository <E,T> implements JDBCRepository<E,T> {

    private static Connection connection;
    private Class<E> entityClass;

    static {
        try {
            connection = ConnectionDatabase.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(E object) {
        return 0;
    }

    @Override
    public E update(E object) {
        return null;
    }

    @Override
    public int delete(T object) {
        return 0;
    }

    @Override
    public List<E> getAll() {
        return List.of();
    }

    @Override
    public E getById(T object) {
        return null;
    }
}
