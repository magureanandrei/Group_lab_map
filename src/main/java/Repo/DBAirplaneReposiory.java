package Repo;

import Exceptions.DatabaseException;
import Models.Airplane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A repository implementation that interacts with the database to store and retrieve Airplane data.
 *
 */
public class DBAirplaneReposiory extends DBRepository<Airplane> {
    /**
     * Constructs a new DBAirplaneRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
    public DBAirplaneReposiory(String dbUrl) throws DatabaseException {
            super(dbUrl);
    }

    /**
     * Creates a new Airplane object in the database.
     *
     * @param obj The Airplane object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void create(Airplane obj) throws DatabaseException {
        String sql = "INSERT INTO Airplane (ID, model, capacity, available) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getModel());
            statement.setInt(3, obj.getCapacity());
            statement.setBoolean(4, obj.getAvailable());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves an Airplane object from the database by its ID.
     *
     * @param id The ID of the Airplane to retrieve.
     * @return The Airplane object, or null if not found.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public Airplane get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM Airplane WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Updates an existing Airplane object in the database.
     *
     * @param obj The Airplane object to update.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void update(Airplane obj) throws DatabaseException {
        String sql = "UPDATE Airplane SET model = ?, capacity = ?, available = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getModel());
            statement.setInt(2, obj.getCapacity());
            statement.setBoolean(3, obj.getAvailable());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Deletes an Airplane object from the database by its ID.
     *
     * @param id The ID of the Airplane to delete.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM Airplane WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves all Airplane objects from the database.
     *
     * @return A list of all Airplane objects.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public List<Airplane> getAll() throws DatabaseException {
        String sql = "SELECT * FROM Airplane";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Airplane> airplanes = new ArrayList<>();
            while (resultSet.next()) {
                airplanes.add(extractFromResultSet(resultSet));
            }

            return airplanes;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Extracts an Airplane object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the Airplane data.
     * @return The extracted Airplane object.
     * @throws SQLException If there is an error retrieving data from the ResultSet.
     */
    private static Airplane extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Airplane(
                resultSet.getInt("ID"),
                resultSet.getString("model"),
                resultSet.getInt("capacity"),
                resultSet.getBoolean("available")
        );
    }
}

