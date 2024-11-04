package service;

import java.sql.SQLException;
import java.util.List;

public interface CommonService<T> {

    List<T> getAll() throws SQLException;

    T getByID(Integer id) throws SQLException;

    T create(T element) throws SQLException;

    T updateByID(Integer id, T element) throws SQLException;

    Integer deleteById(Integer id) throws SQLException;

}
