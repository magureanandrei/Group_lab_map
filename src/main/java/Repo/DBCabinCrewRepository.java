package Repo;
import Exceptions.DatabaseException;
import Models.CabinCrew;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * A repository implementation that interacts with the database to store and retrieve CabinCrew data.
 */
public class DBCabinCrewRepository extends DBRepository<CabinCrew> {

    /**
     * Constructs a new DBCabinCrewRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
    public DBCabinCrewRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
    }

    /**
     * Creates a new CabinCrew object in the database.
     *
     * @param obj The CabinCrew object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void create(CabinCrew obj) throws DatabaseException {
        String sql = "INSERT INTO CabinCrew (ID, nume, email, profession) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getNume());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getProfession());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves a CabinCrew object from the database by its ID.
     *
     * @param id The ID of the CabinCrew to retrieve.
     * @return The CabinCrew object, or null if not found.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public CabinCrew get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM CabinCrew WHERE ID = ?";

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
     * Updates an existing CabinCrew object in the database.
     *
     * @param obj The CabinCrew object to update.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void update(CabinCrew obj) throws DatabaseException {
        String sql = "UPDATE CabinCrew SET nume = ?, email = ?, profession = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getNume());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getProfession());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Deletes a CabinCrew object from the database by its ID.
     *
     * @param id The ID of the CabinCrew to delete.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM CabinCrew WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves all CabinCrew objects from the database.
     *
     * @return A list of all CabinCrew objects.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public List<CabinCrew> getAll() throws DatabaseException {
        String sql = "SELECT * FROM CabinCrew";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<CabinCrew> cabinCrews = new ArrayList<>();
            while (resultSet.next()) {
                cabinCrews.add(extractFromResultSet(resultSet));
            }

            return cabinCrews;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Extracts a CabinCrew object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the CabinCrew data.
     * @return The extracted CabinCrew object.
     * @throws SQLException If there is an error retrieving data from the ResultSet.
     */
    private static CabinCrew extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new CabinCrew(
                resultSet.getString("nume"),
                resultSet.getInt("ID"),
                resultSet.getString("email"),
                resultSet.getString("profession")
        );
    }
}
