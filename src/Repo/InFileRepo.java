package Repo;

import Models.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A repository implementation that stores data in a file.
 *
 * @param <T> The type of objects stored in the repository, which must implement HasId.
 */
public class InFileRepo<T extends HasID> implements Repository<T> {
    private final String filePath;
    private Function<String, T> fromCSV;

    /**
     * Constructs a new FileRepository with the specified file path.
     *
     * @param filePath The path to the file where data will be stored.
     */
    public InFileRepo(String filePath, Function<String, T> fromCSV) {
        this.filePath = filePath;
        this.fromCSV=fromCSV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(T obj) {
        doInFile(data -> data.putIfAbsent(obj.getID(), obj));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(Integer id) {
        return readDataFromFile().get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T obj) {
        doInFile(data -> data.replace(obj.getID(), obj));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) {
        doInFile(data -> data.remove(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return readDataFromFile().values().stream().toList();
    }

    /**
     * Performs an operation on the data stored in the file.
     *
     * @param function The function to apply to the data.
     */
    private void doInFile(Consumer<Map<Integer, T>> function) {
        Map<Integer, T> objects = readDataFromFile();
        function.accept(objects);
        writeDataToFile(objects);
    }

    /**
     * Reads the data from the file.
     *
     * @return The data stored in the file, or an empty map if the file is empty or does not exist.
     */
    private Map<Integer, T> readDataFromFile() {
        Map<Integer, T> objects = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                T obj = fromCSV.apply(line);
                objects.put(obj.getID(), obj);
            }
            return objects;
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Writes the data to the file.
     *
     * @param objects The data to write to the file.
     */
    private void writeDataToFile(Map<Integer, T> objects) {
        if(objects.isEmpty()) return;

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(String.join(",", objects.get(1).getHeader()) + "\n");

            for(Map.Entry<Integer, T> entry : objects.entrySet()){
                writer.write(entry.getValue().toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

}


