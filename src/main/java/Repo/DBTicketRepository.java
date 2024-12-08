package Repo;
import Exceptions.DatabaseException;
import Models.Payment;
import Models.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A repository implementation that interacts with the database to store and retrieve Ticket data.
 */
public class DBTicketRepository extends DBRepository<Ticket> {

        private final DBPaymentRepository paymentRepository;

    /**
     * Constructs a new DBTicketRepository with the specified database URL.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error connecting to the database.
     */
        public DBTicketRepository(String dbUrl) throws DatabaseException {
            super(dbUrl);
            this.paymentRepository = new DBPaymentRepository(dbUrl);
        }

    /**
     * Creates a new Ticket object in the database.
     *
     * @param obj The Ticket object to be created.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void create(Ticket obj) throws DatabaseException {
            String sql = "INSERT INTO Ticket (ID, title, description, paymentID, ticketDate) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, obj.getID());
                statement.setString(2, obj.getTitle());
                statement.setString(3, obj.getDescription());
                statement.setInt(4, obj.getPayment().getID());
                statement.setDate(5, java.sql.Date.valueOf(obj.getDate()));

                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Retrieves a Ticket object from the database by its ID.
     *
     * @param id The ID of the Ticket to retrieve.
     * @return The Ticket object, or null if not found.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public Ticket get(Integer id) throws DatabaseException {
            String sql = "SELECT * FROM Ticket WHERE ID = ?";

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
     * Updates an existing Ticket object in the database.
     *
     * @param obj The Ticket object to update.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void update(Ticket obj) throws DatabaseException {
            String sql = "UPDATE Ticket SET title = ?, description = ?, paymentID = ?, ticketDate = ? WHERE ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getDescription());
                statement.setInt(3, obj.getPayment().getID());
                statement.setDate(4, java.sql.Date.valueOf(obj.getDate()));
                statement.setInt(5, obj.getID());

                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Deletes a Ticket object from the database by its ID.
     *
     * @param id The ID of the Ticket to delete.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public void delete(Integer id) throws DatabaseException {
            String sql = "DELETE FROM Ticket WHERE ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Retrieves all Ticket objects from the database.
     *
     * @return A list of all Ticket objects.
     * @throws DatabaseException If there is an error executing the SQL query.
     */
        @Override
        public List<Ticket> getAll() throws DatabaseException {
            String sql = "SELECT * FROM Ticket";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                List<Ticket> tickets = new ArrayList<>();
                while (resultSet.next()) {
                    tickets.add(extractFromResultSet(resultSet));
                }

                return tickets;
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }

    /**
     * Extracts a Ticket object from the given ResultSet.
     *
     * @param resultSet The ResultSet containing the Ticket data.
     * @return The extracted Ticket object.
     * @throws SQLException If there is an error retrieving data from the ResultSet.
     * @throws DatabaseException If there is an error retrieving the associated Payment object.
     */
        private Ticket extractFromResultSet(ResultSet resultSet) throws SQLException, DatabaseException {
            int id = resultSet.getInt("ID");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            int paymentId = resultSet.getInt("paymentID");
            String date = resultSet.getDate("ticketDate").toString();

            Payment payment = paymentRepository.get(paymentId);

            return new Ticket(id, title, description, payment, date);
        }
    }

