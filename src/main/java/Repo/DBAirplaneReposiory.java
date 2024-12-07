package Repo;

import Exceptions.DatabaseException;
import Models.Airplane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAirplaneReposiory extends DBRepository<Airplane> {

    public DBAirplaneReposiory(String dbUrl) throws DatabaseException {
            super(dbUrl);
    }

    @Override
    public void create(Airplane obj) throws DatabaseException {
        String sql = "INSERT INTO Airplane (ID, model, capacity, available) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getModel());
            statement.setInt(3, obj.getCapacity());
            statement.setBoolean(4, obj.getAvailable());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Airplane get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM Airplane WHERE ID = ?";

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
    public void update(Airplane obj) throws DatabaseException {
        String sql = "UPDATE Airplane SET model = ?, capacity = ?, available = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getModel());
            statement.setInt(2, obj.getCapacity());
            statement.setBoolean(3, obj.getAvailable());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM Airplane WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Airplane> getAll() throws DatabaseException {
        String sql = "SELECT * FROM Airplane";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Airplane> airplanes = new ArrayList<>();
            while (resultSet.next()) {
                airplanes.add(extractFromResultSet(resultSet));
            }

            return airplanes;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private static Airplane extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Airplane(
                resultSet.getInt("ID"),
                resultSet.getString("model"),
                resultSet.getInt("capacity"),
                resultSet.getBoolean("available")
        );
    }
}

