package Repo;

import Exceptions.DatabaseException;
import Models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBPaymentRepository extends DBRepository<Payment> {

    private final DBPassengerRepository passengerRepository;

    public DBPaymentRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
        this.passengerRepository = new DBPassengerRepository(dbUrl);
    }

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

    private Payment extractFromResultSet(ResultSet resultSet) throws SQLException, DatabaseException {
        int id = resultSet.getInt("ID");
        String description = resultSet.getString("description");
        double amount = resultSet.getDouble("amount");
        int passengerId = resultSet.getInt("passengerID");

        Passenger passenger = passengerRepository.get(passengerId);

        return new Payment(id, description, amount, passenger);
    }
}

