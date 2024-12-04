package Repo;

import Models.Airplane;
import Models.Airport;
import Models.Flight;
import Models.Pilot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFlightRepository extends DBRepository<Flight> {

        private final DBPilorRepository pilotRepository;
        private final DBAirplaneReposiory airplaneRepository;
        private final DBAirportRepository airportRepository;

        public DBFlightRepository(String dbUrl) {
            super(dbUrl);
            this.pilotRepository = new DBPilorRepository(dbUrl);
            this.airplaneRepository = new DBAirplaneReposiory(dbUrl);
            this.airportRepository = new DBAirportRepository(dbUrl);
        }

        @Override
        public void create(Flight flight) {
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
                throw new RuntimeException("Error while creating flight: " + e.getMessage(), e);
            }
        }

        @Override
        public Flight get(Integer id) {
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
                throw new RuntimeException("Error while reading flight: " + e.getMessage(), e);
            }
        }

        @Override
        public void update(Flight flight) {
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
                throw new RuntimeException("Error while updating flight: " + e.getMessage(), e);
            }
        }

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

        @Override
        public List<Flight> getAll() {
            String sql = "SELECT * FROM Flight";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                List<Flight> flights = new ArrayList<>();
                while (resultSet.next()) {
                    flights.add(extractFromResultSet(resultSet));
                }

                return flights;
            } catch (SQLException e) {
                throw new RuntimeException("Error while getting all flights: " + e.getMessage(), e);
            }
        }

        private Flight extractFromResultSet(ResultSet resultSet) throws SQLException {
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

