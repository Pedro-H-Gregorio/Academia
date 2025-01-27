package jdbc.interfaces;

import java.util.List;

public interface JDBCRepository<E,T> {
    int save(E object);
    E update(E object);
    int delete(T object);
    List<E> getAll();
    E getById(T object);
}
