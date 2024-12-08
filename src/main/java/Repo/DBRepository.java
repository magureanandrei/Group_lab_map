package Repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Exceptions.DatabaseException;
import Models.HasID;
/**
 * A repository implementation that stores main.java.data in database.
 *
 * @param <T> The type of objects stored in the repository, which must implement HasId.
 */
public abstract class DBRepository<T extends HasID> implements Repository<T>, AutoCloseable {

    protected final Connection connection;

    /**
     * Constructs a new  DBRepository and establishes a connection to the database.
     *
     * @param dbUrl The URL of the database to connect to.
     * @throws DatabaseException If there is an error while attempting to connect to the database.
     */
    public DBRepository(String dbUrl) throws DatabaseException {
        try {
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Closes the database connection.
     *
     * @throws Exception If there is an error while closing the connection.
     */
    @Override
    public void close() throws Exception {
        connection.close();
    }
}