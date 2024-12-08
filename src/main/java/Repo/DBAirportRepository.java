package Repo;

import Exceptions.DatabaseException;
import Models.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * A repository implementation that interacts with the database to store and retrieve Airport data.
 */
public class DBAirportRepository extends DBRepository<Airport> {

    /**
     * Constructs a new DBAirportRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
        public DBAirportRepository(String dbUrl) throws DatabaseException {
            super(dbUrl);
        }

    /**
     * Creates a new Airport object in the database.
     *
     * @param obj The Airport object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void create(Airport obj) throws DatabaseException {
            String sql = "INSERT INTO Airport (ID, name, location, number_of_airstrips, avaliable) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, obj.getID());
                statement.setString(2, obj.getName());
                statement.setString(3, obj.getLocation());
                statement.setInt(4, obj.getNumber_of_airstrips());
                statement.setBoolean(5, obj.getAvaliable());

                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Retrieves an Airport object from the database by its ID.
     *
     * @param id The ID of the Airport to retrieve.
     * @return The Airport object, or null if not found.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public Airport get(Integer id) throws DatabaseException {
            String sql = "SELECT * FROM Airport WHERE ID = ?";

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
     * Updates an existing Airport object in the database.
     *
     * @param obj The Airport object to update.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void update(Airport obj) throws DatabaseException {
            String sql = "UPDATE Airport SET name = ?, location = ?, number_of_airstrips = ?, avaliable = ? WHERE ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, obj.getName());
                statement.setString(2, obj.getLocation());
                statement.setInt(3, obj.getNumber_of_airstrips());
                statement.setBoolean(4, obj.getAvaliable());
                statement.setInt(5, obj.getID());

                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Deletes an Airport object from the database by its ID.
     *
     * @param id The ID of the Airport to delete.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void delete(Integer id) throws DatabaseException {
            String sql = "DELETE FROM Airport WHERE ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Retrieves all Airport objects from the database.
     *
     * @return A list of all Airport objects.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public List<Airport> getAll() throws DatabaseException {
            String sql = "SELECT * FROM Airport";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                List<Airport> airports = new ArrayList<>();
                while (resultSet.next()) {
                    airports.add(extractFromResultSet(resultSet));
                }

                return airports;
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Extracts an Airport object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the Airport data.
     * @return The extracted Airport object.
     * @throws SQLException If there is an error retrieving data from the ResultSet.
     */
        private static Airport extractFromResultSet(ResultSet resultSet) throws SQLException {
            return new Airport(
                    resultSet.getInt("ID"),
                    resultSet.getString("name"),
                    resultSet.getString("location"),
                    resultSet.getInt("number_of_airstrips"),
                    resultSet.getBoolean("avaliable")
            );
        }
    }

