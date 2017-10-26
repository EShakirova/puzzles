package db.dao;

import java.util.List;

public interface ICommonDAO<T> {
    List<T> getAll() throws Exception;
    T getByID(int in_id)throws Exception;
    boolean insert(T obj);
    void update(T obj)throws Exception;
    void delete(T obj)throws Exception;
}
