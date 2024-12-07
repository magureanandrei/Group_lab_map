package Repo;
import Exceptions.DatabaseException;
import Models.Pair;
import Models.Passenger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBPassengerRepository extends DBRepository<Passenger> {

    public DBPassengerRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
    }

    @Override
    public void create(Passenger obj) throws DatabaseException {
        String sql = "INSERT INTO Passenger (ID, nume, email, fromLocation, toLocation) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getNume());
            statement.setString(3, obj.getEmail());

            Pair flight = obj.getFlight();
            if (flight != null) {
                statement.setString(4, flight.getFrom());
                statement.setString(5, flight.getTo());
            } else {
                statement.setNull(4, java.sql.Types.VARCHAR);
                statement.setNull(5, java.sql.Types.VARCHAR);
            }

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Passenger get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM Passenger WHERE ID = ?";

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
    public void update(Passenger obj) throws DatabaseException {
        String sql = "UPDATE Passenger SET nume = ?, email = ?, fromLocation = ?, toLocation = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getNume());
            statement.setString(2, obj.getEmail());

            Pair flight = obj.getFlight();
            if (flight != null) {
                statement.setString(3, flight.getFrom());
                statement.setString(4, flight.getTo());
            } else {
                statement.setNull(3, java.sql.Types.VARCHAR);
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            statement.setInt(5, obj.getID());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM Passenger WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Passenger> getAll() throws DatabaseException {
        String sql = "SELECT * FROM Passenger";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Passenger> passengers = new ArrayList<>();
            while (resultSet.next()) {
                passengers.add(extractFromResultSet(resultSet));
            }

            return passengers;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private static Passenger extractFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("nume");
        int id = resultSet.getInt("ID");
        String email = resultSet.getString("email");

        String flightFrom = resultSet.getString("fromLocation");
        String flightTo = resultSet.getString("toLocation");
        Pair flight = (flightFrom != null && flightTo != null) ? new Pair(flightFrom, flightTo) : null;

        return new Passenger(name, id, email, flight);
    }
}

