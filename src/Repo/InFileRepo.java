package Repo;

import Models.HasID;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class InFileRepo<T extends HasID> implements Repository<T> {
    private final String filename;
    public InFileRepo(String filename){
        this.filename=filename;
    }
    @Override
    public void create(T obj) {
        doInFile(map->map.putIfAbsent(obj.getID(),obj));
    }

    @Override
    public T get(Integer id) {
        return read().get(id);
    }

    @Override
    public void update(T obj) {
        doInFile(map->map.replace(obj.getID(),obj));
    }

    @Override
    public void delete(Integer id) {
        doInFile(map->map.remove(id));
    }

    @Override
    public List<T> getAll() {
        return read().values().stream().toList();
    }

    private Map<Integer,T> read(){
        try(ObjectInputStream input=new ObjectInputStream(new FileInputStream(filename))){
            return  (Map<Integer,T>)input.readObject();
        }catch (IOException | ClassNotFoundException e){
            return Map.of();
        }
    }

    private void write(Map<Integer, T> map){
        try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(filename))){
            output.writeObject(map);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void doInFile(Consumer<Map<Integer,T>> consumer){
        Map<Integer,T> map=read();
        consumer.accept(map);
        write(map);
    }
}
