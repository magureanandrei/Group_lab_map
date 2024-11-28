package Repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.HasID;

/**
 * A repository implementation that stores main.java.data in memory.
 *
 * @param <T> The type of objects stored in the repository, which must implement HasId.
 */
public class InMemoryRepo<T extends HasID> implements Repository<T> {

    Map<Integer,T> data = new HashMap<>();;

    /**
     * {@inheritDoc}
     * Creates a new object in the repository.
     */
    @Override
    public void create(T obj) {
        data.putIfAbsent(obj.getID(),obj);
    }

    /**
     * {@inheritDoc}
     * Retrieves an object from the repository by its ID.
     */
    @Override
    public T get(Integer id) {
        return data.get(id);
    }

    /**
     * {@inheritDoc}
     * Updates an existing object in the repository.
     */
    @Override
    public void update(T obj) {
        data.replace(obj.getID(), obj);
    }

    /**
     * {@inheritDoc}
     * Deletes an object from the repository by its ID.
     */
    @Override
    public void delete(Integer id) {
        data.remove(id);
    }

    /**
     * {@inheritDoc}
     * Retrieves all objects from the repository.
     */
    @Override
    public List<T> getAll() {
        return data.values().stream().toList();
    }
}
