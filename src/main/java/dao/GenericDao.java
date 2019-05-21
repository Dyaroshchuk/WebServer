package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    int add(T t);

    List<T> getAll(Class<T> entityClass);

    int delete(Long id);

    int edit(T t);

    Optional<T> get(Long id);
}
