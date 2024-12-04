package Repo;

import Models.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAirportRepository extends DBRepository<Airport> {

        public DBAirportRepository(String dbUrl) {
            super(dbUrl);
        }

        @Override
        public void create(Airport obj) {
            String sql = "INSERT INTO Airport (ID, name, location, number_of_airstrips, avaliable) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, obj.getID());
                statement.setString(2, obj.getName());
                statement.setString(3, obj.getLocation());
                statement.setInt(4, obj.getNumber_of_airstrips());
                statement.setBoolean(5, obj.getAvaliable());

                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Airport get(Integer id) {
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
                throw new RuntimeException(e);
            }
        }

        @Override
        public void update(Airport obj) {
            String sql = "UPDATE Airport SET name = ?, location = ?, number_of_airstrips = ?, avaliable = ? WHERE ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, obj.getName());
                statement.setString(2, obj.getLocation());
                statement.setInt(3, obj.getNumber_of_airstrips());
                statement.setBoolean(4, obj.getAvaliable());
                statement.setInt(5, obj.getID());

                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void delete(Integer id) {
            String sql = "DELETE FROM Airport WHERE ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Airport> getAll() {
            String sql = "SELECT * FROM Airport";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                List<Airport> airports = new ArrayList<>();
                while (resultSet.next()) {
                    airports.add(extractFromResultSet(resultSet));
                }

                return airports;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

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

