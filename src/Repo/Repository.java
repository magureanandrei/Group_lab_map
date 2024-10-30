package Repo;

import Models.*;

import java.util.List;

public interface Repository<T extends HasID> {
    void create(T obj);
    T get(Integer id);
    void update(T obj);
    void delete(Integer id);
    List<T> getAll();
}