package Repo;

import java.util.List;
import java.util.Map;
import Repo.Repository;
import Models.HasID;

public class InMemoryRepo<T extends HasID> implements Repository<T> {

    Map<Integer,T> data;
    @Override
    public void create(T obj) {
        data.putIfAbsent(obj.getID(),obj);
    }

    @Override
    public T get(Integer id) {
        return data.get(id);
    }

    @Override
    public void update(T obj) {
        data.replace(obj.getID(), obj);
    }

    @Override
    public void delete(Integer id) {
        data.remove(id);
    }

    @Override
    public List<T> getAll() {
        return data.values().stream().toList();
    }
}
