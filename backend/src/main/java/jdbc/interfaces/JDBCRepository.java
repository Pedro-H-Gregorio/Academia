package jdbc.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface JDBCRepository<E,T> {
    int save(E object) throws SQLException;
    E update(E object) throws SQLException;
    int delete(T object) throws SQLException;
    List<E> getAll() throws SQLException;
    E getById(T object) throws SQLException;
    int deleteAll() throws SQLException;
}
