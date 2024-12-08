package Repo;
import Exceptions.DatabaseException;
import Models.Pilot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * A repository implementation that interacts with the database to store and retrieve Pilot data.
 */
public class DBPilorRepository  extends DBRepository<Pilot> {

    /**
     * Constructs a new DBPilorRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
    public DBPilorRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
    }

    /**
     * Creates a new Pilot object in the database.
     *
     * @param obj The Pilot object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void create(Pilot obj) throws DatabaseException {
        String sql = "INSERT INTO Pilot (ID, nume, email, availability) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getNume());
            statement.setString(3, obj.getEmail());
            statement.setBoolean(4, obj.getAvailability());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves a Pilot object from the database by its ID.
     *
     * @param id The ID of the Pilot to retrieve.
     * @return The Pilot object, or null if not found.
     */
    @Override
    public Pilot get(Integer id) {
        String sql = "SELECT * FROM Pilot WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates an existing Pilot object in the database.
     *
     * @param obj The Pilot object to update.
     */
    @Override
    public void update(Pilot obj) {
        String sql = "UPDATE Pilot SET nume = ?, email = ?, availability = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getNume());
            statement.setString(2, obj.getEmail());
            statement.setBoolean(3, obj.getAvailability());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a Pilot object from the database by its ID.
     *
     * @param id The ID of the Pilot to delete.
     */
    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Pilot WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all Pilot objects from the database.
     *
     * @return A list of all Pilot objects.
     */
    @Override
    public List<Pilot> getAll() {
        String sql = "SELECT * FROM Pilot";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Pilot> pilots = new ArrayList<>();
            while (resultSet.next()) {
                pilots.add(extractFromResultSet(resultSet));
            }

            return pilots;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts a Pilot object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the Pilot data.
     * @return The extracted Pilot object.
     * @throws SQLException If there is an error retrieving data from the ResultSet.
     */
    private static Pilot extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Pilot(
                resultSet.getString("nume"),
                resultSet.getInt("ID"),
                resultSet.getString("email"),
                resultSet.getBoolean("availability")
        );
    }
}

