package Repo;

import Exceptions.DatabaseException;
import Models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * A repository implementation that interacts with the database to store and retrieve Payment data.
 */
public class DBPaymentRepository extends DBRepository<Payment> {

    private final DBPassengerRepository passengerRepository;

    /**
     * Constructs a new DBPaymentRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
    public DBPaymentRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
        this.passengerRepository = new DBPassengerRepository(dbUrl);
    }

    /**
     * Creates a new Payment object in the database.
     *
     * @param obj The Payment object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void create(Payment obj) throws DatabaseException {
        String sql = "INSERT INTO Payment (ID, description, amount, passengerID) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getDescription());
            statement.setDouble(3, obj.getAmount());
            statement.setInt(4, obj.getPassenger().getID());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves a Payment object from the database by its ID.
     *
     * @param id The ID of the Payment to retrieve.
     * @return The Payment object, or null if not found.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public Payment get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM Payment WHERE ID = ?";

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
     * Updates an existing Payment object in the database.
     *
     * @param obj The Payment object to update.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void update(Payment obj) throws DatabaseException {
        String sql = "UPDATE Payment SET description = ?, amount = ?, passengerID = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getDescription());
            statement.setDouble(2, obj.getAmount());
            statement.setInt(3, obj.getPassenger().getID());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Deletes a Payment object from the database by its ID.
     *
     * @param id The ID of the Payment to delete.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM Payment WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves all Payment objects from the database.
     *
     * @return A list of all Payment objects.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public List<Payment> getAll() throws DatabaseException {
        String sql = "SELECT * FROM Payment";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Payment> payments = new ArrayList<>();
            while (resultSet.next()) {
                payments.add(extractFromResultSet(resultSet));
            }

            return payments;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Extracts a Payment object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the Payment data.
     * @return The extracted Payment object.
     * @throws SQLException If there is an error retrieving data from the ResultSet.
     * @throws DatabaseException If there is an error retrieving the associated Passenger object.
     */
    private Payment extractFromResultSet(ResultSet resultSet) throws SQLException, DatabaseException {
        int id = resultSet.getInt("ID");
        String description = resultSet.getString("description");
        double amount = resultSet.getDouble("amount");
        int passengerId = resultSet.getInt("passengerID");

        Passenger passenger = passengerRepository.get(passengerId);

        return new Payment(id, description, amount, passenger);
    }
}

