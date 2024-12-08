package Repo;

import Exceptions.DatabaseException;
import Models.Airplane;
import Models.Airport;
import Models.Flight;
import Models.Pilot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * A repository implementation that interacts with the database to manage Flight entities.
 */
public class DBFlightRepository extends DBRepository<Flight> {

        private final DBPilorRepository pilotRepository;
        private final DBAirplaneReposiory airplaneRepository;
        private final DBAirportRepository airportRepository;


    /**
     * Constructs a new DBFlightRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
        public DBFlightRepository(String dbUrl) throws DatabaseException {
            super(dbUrl);
            this.pilotRepository = new DBPilorRepository(dbUrl);
            this.airplaneRepository = new DBAirplaneReposiory(dbUrl);
            this.airportRepository = new DBAirportRepository(dbUrl);
        }

    /**
     * Creates a new Flight object in the database.
     *
     * @param flight The Flight object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void create(Flight flight) throws DatabaseException {
            String sql = "INSERT INTO Flight (flightID, fromLocation, toLocation, pilotID, airplaneID, airportID, flightDate, amount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, flight.getID());
                statement.setString(2, flight.getFrom());
                statement.setString(3, flight.getTo());
                statement.setInt(4, flight.getPilot().getID());
                statement.setInt(5, flight.getAirplane().getID());
                statement.setInt(6, flight.getAirport().getID());
                statement.setDate(7, java.sql.Date.valueOf(flight.getDate()));
                statement.setDouble(8, flight.getAmount());

                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Retrieves a Flight object from the database by its ID.
     *
     * @param id The ID of the Flight to retrieve.
     * @return The Flight object, or null if not found.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public Flight get(Integer id) throws DatabaseException {
            String sql = "SELECT * FROM Flight WHERE flightID = ?";

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
     * Updates an existing Flight object in the database.
     *
     * @param flight The Flight object to update.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void update(Flight flight) throws DatabaseException {
            String sql = "UPDATE Flight SET fromLocation = ?, toLocation = ?, pilotID = ?, airplaneID = ?, airportID = ?, flightDate = ?, amount = ? " +
                    "WHERE flightID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flight.getFrom());
                statement.setString(2, flight.getTo());
                statement.setInt(3, flight.getPilot().getID());
                statement.setInt(4, flight.getAirplane().getID());
                statement.setInt(5, flight.getAirport().getID());
                statement.setDate(6, java.sql.Date.valueOf(flight.getDate()));
                statement.setDouble(7, flight.getAmount());
                statement.setInt(8, flight.getID());

                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Deletes a Flight object from the database by its ID.
     *
     * @param id The ID of the Flight to delete.
     */
        @Override
        public void delete(Integer id) {
            String sql = "DELETE FROM Flight WHERE flightID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException("Error while deleting flight: " + e.getMessage(), e);
            }
        }

    /**
     * Retrieves all Flight objects from the database.
     *
     * @return A list of all Flight objects.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public List<Flight> getAll() throws DatabaseException {
            String sql = "SELECT * FROM Flight";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                List<Flight> flights = new ArrayList<>();
                while (resultSet.next()) {
                    flights.add(extractFromResultSet(resultSet));
                }

                return flights;
            } catch (SQLException | DatabaseException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Extracts a Flight object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the Flight data.
     * @return The extracted Flight object.
     * @throws SQLException        If there is an error retrieving data from the ResultSet.
     * @throws DatabaseException If there is an error retrieving related entities.
     */
        private Flight extractFromResultSet(ResultSet resultSet) throws SQLException, DatabaseException {
            int flightID = resultSet.getInt("flightID");
            String from = resultSet.getString("fromLocation");
            String to = resultSet.getString("toLocation");
            String date = resultSet.getDate("flightDate").toString();
            double amount = resultSet.getDouble("amount");

            int pilotID = resultSet.getInt("pilotID");
            int airplaneID = resultSet.getInt("airplaneID");
            int airportID = resultSet.getInt("airportID");

            Pilot pilot = pilotRepository.get(pilotID);
            Airplane airplane = airplaneRepository.get(airplaneID);
            Airport airport = airportRepository.get(airportID);

            return new Flight(flightID, from, to, pilot, airplane, airport, date, amount);
        }
    }

