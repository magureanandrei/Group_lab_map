package Repo;
import Exceptions.DatabaseException;
import Models.Pilot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBPilorRepository  extends DBRepository<Pilot> {

    public DBPilorRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
    }

    @Override
    public void create(Pilot obj) throws DatabaseException {
        String sql = "INSERT INTO Pilot (ID, nume, email, availability) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getNume());
            statement.setString(3, obj.getEmail());
            statement.setBoolean(4, obj.getAvailability());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Pilot get(Integer id) {
        String sql = "SELECT * FROM Pilot WHERE ID = ?";

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
    public void update(Pilot obj) {
        String sql = "UPDATE Pilot SET nume = ?, email = ?, availability = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getNume());
            statement.setString(2, obj.getEmail());
            statement.setBoolean(3, obj.getAvailability());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Pilot WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pilot> getAll() {
        String sql = "SELECT * FROM Pilot";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Pilot> pilots = new ArrayList<>();
            while (resultSet.next()) {
                pilots.add(extractFromResultSet(resultSet));
            }

            return pilots;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Pilot extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Pilot(
                resultSet.getString("nume"),
                resultSet.getInt("ID"),
                resultSet.getString("email"),
                resultSet.getBoolean("availability")
        );
    }
}

