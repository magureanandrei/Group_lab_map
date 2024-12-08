package Repo;

import Exceptions.DatabaseException;
import Models.Pair;
import Models.Passenger;
import Models.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A repository implementation that interacts with the database to store and retrieve Reservation data.
 */
public class DBReservationRepository extends DBRepository<Reservation> {

    private final DBPassengerRepository passengerRepository;

    /**
     * Constructs a new  DBReservationRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database.
     * @throws DatabaseException If there is an error connecting to the database.
     */
    public DBReservationRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
        this.passengerRepository = new DBPassengerRepository(dbUrl);
    }

    /**
     * Adds a new reservation to the database.
     *
     * @param reservation The reservation to create.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void create(Reservation reservation) throws DatabaseException {
        String sql = "INSERT INTO Reservation (id, reservationDate, passengerID, fromLocation, toLocation) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getID());
            statement.setDate(2, java.sql.Date.valueOf(reservation.getDate()));
            statement.setInt(3, reservation.getPassenger().getID());

            Pair flight = reservation.getFlight();
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

    /**
     * Retrieves a reservation by its ID.
     *
     * @param id The ID of the reservation.
     * @return The reservation, or null if no reservation is found with the given ID.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public Reservation get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM Reservation WHERE id = ?";

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
     * Updates an existing reservation in the database.
     *
     * @param reservation The reservation with updated details.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void update(Reservation reservation) throws DatabaseException {
        String sql = "UPDATE Reservation SET reservationDate = ?, passengerID = ?, fromLocation = ?, toLocation = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(reservation.getDate()));
            statement.setInt(2, reservation.getPassenger().getID());

            Pair flight = reservation.getFlight();
            if (flight != null) {
                statement.setString(3, flight.getFrom());
                statement.setString(4, flight.getTo());
            } else {
                statement.setNull(3, java.sql.Types.VARCHAR);
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            statement.setInt(5, reservation.getID());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Deletes a reservation from the database by its ID.
     *
     * @param id The ID of the reservation to delete.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM Reservation WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Retrieves all reservations from the database.
     *
     * @return A list of all reservations.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
    @Override
    public List<Reservation> getAll() throws DatabaseException {
        String sql = "SELECT * FROM Reservation";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                reservations.add(extractFromResultSet(resultSet));
            }

            return reservations;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Extracts a  Reservation from a  ResultSet.
     *
     * @param resultSet The result set containing the reservation data.
     * @return A  Reservation object.
     * @throws SQLException If there is an error reading from the result set.
     * @throws DatabaseException If there is an error fetching the associated passenger.
     */
    private Reservation extractFromResultSet(ResultSet resultSet) throws SQLException, DatabaseException {
        int id = resultSet.getInt("id");
        String date = resultSet.getDate("reservationDate").toString();

        int passengerID = resultSet.getInt("passengerID");
        String fromLocation = resultSet.getString("fromLocation");
        String toLocation = resultSet.getString("toLocation");

        Passenger passenger = passengerRepository.get(passengerID);
        Pair flight = (fromLocation != null && toLocation != null) ? new Pair(fromLocation, toLocation) : null;

        return new Reservation(id, date, passenger, flight);
    }
}
