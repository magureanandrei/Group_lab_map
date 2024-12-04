package Repo;

import Models.Pair;
import Models.Passenger;
import Models.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReservationRepository extends DBRepository<Reservation> {

    private final DBPassengerRepository passengerRepository;

    public DBReservationRepository(String dbUrl) {
        super(dbUrl);
        this.passengerRepository = new DBPassengerRepository(dbUrl);
    }

    @Override
    public void create(Reservation reservation) {
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
            throw new RuntimeException("Error while creating reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public Reservation get(Integer id) {
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
            throw new RuntimeException("Error while reading reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Reservation reservation) {
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
            throw new RuntimeException("Error while updating reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Reservation WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Reservation> getAll() {
        String sql = "SELECT * FROM Reservation";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                reservations.add(extractFromResultSet(resultSet));
            }

            return reservations;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all reservations: " + e.getMessage(), e);
        }
    }

    private Reservation extractFromResultSet(ResultSet resultSet) throws SQLException {
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
