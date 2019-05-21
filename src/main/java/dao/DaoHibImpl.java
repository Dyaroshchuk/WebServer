package dao;

import java.util.List;
import java.util.Optional;

public interface DaoHibImpl<T> {

    int add(T t);

    List<T> getAll();

    int delete(Long id);

    int edit(T t);

    Optional<T> get(Long id);

    Optional<T> get(String login);
}
