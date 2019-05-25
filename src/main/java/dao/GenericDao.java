package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    int add(T t);

    List<T> getAll(Class<T> entityClass);

    int delete(Class<T> entityClass, Long id);

    int update(T t);

    Optional<T> get(Class<T> entityClass, Long id);
}
