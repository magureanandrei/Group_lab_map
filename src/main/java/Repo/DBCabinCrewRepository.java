package Repo;
import Exceptions.DatabaseException;
import Models.CabinCrew;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCabinCrewRepository extends DBRepository<CabinCrew> {

    public DBCabinCrewRepository(String dbUrl) throws DatabaseException {
        super(dbUrl);
    }

    @Override
    public void create(CabinCrew obj) throws DatabaseException {
        String sql = "INSERT INTO CabinCrew (ID, nume, email, profession) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getID());
            statement.setString(2, obj.getNume());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getProfession());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public CabinCrew get(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM CabinCrew WHERE ID = ?";

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
    public void update(CabinCrew obj) throws DatabaseException {
        String sql = "UPDATE CabinCrew SET nume = ?, email = ?, profession = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getNume());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getProfession());
            statement.setInt(4, obj.getID());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM CabinCrew WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<CabinCrew> getAll() throws DatabaseException {
        String sql = "SELECT * FROM CabinCrew";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<CabinCrew> cabinCrews = new ArrayList<>();
            while (resultSet.next()) {
                cabinCrews.add(extractFromResultSet(resultSet));
            }

            return cabinCrews;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private static CabinCrew extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new CabinCrew(
                resultSet.getString("nume"),
                resultSet.getInt("ID"),
                resultSet.getString("email"),
                resultSet.getString("profession")
        );
    }
}
