package Repo;

import Models.HasID;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A repository implementation that stores data in a file.
 *
 * @param <T> The type of objects stored in the repository, which must implement HasId.
 */
public class InFileRepo<T extends HasID> implements Repository<T> {
    private final String filename;

    /**
     * Constructs a new FileRepository with the specified file path.
     *
     * @param filename The path to the file where data will be stored.
     */
    public InFileRepo(String filename){
        this.filename=filename;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void create(T obj) {
        doInFile(map->map.putIfAbsent(obj.getID(),obj));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(Integer id) {
        return read().get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T obj) {
        doInFile(map->map.replace(obj.getID(),obj));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) {
        doInFile(map->map.remove(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return read().values().stream().toList();
    }

    /**
     * Reads the data from the file.
     *
     * @return The data stored in the file, or an empty map if the file is empty or does not exist.
     */
    private Map<Integer,T> read(){
        try(ObjectInputStream input=new ObjectInputStream(new FileInputStream(filename))){
            return  (Map<Integer,T>)input.readObject();
        }catch (IOException | ClassNotFoundException e){
            return Map.of();
        }
    }

    /**
     * Writes the data to the file.
     *
     * @param map The data to write to the file.
     */
    private void write(Map<Integer, T> map){
        try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(filename))){
            output.writeObject(map);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Performs an operation on the data stored in the file.
     *
     * @param consumer The function to apply to the data.
     */
    private void doInFile(Consumer<Map<Integer,T>> consumer){
        Map<Integer,T> map=read();
        consumer.accept(map);
        write(map);
    }
}
