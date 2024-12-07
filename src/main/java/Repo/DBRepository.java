package Repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Exceptions.DatabaseException;
import Models.HasID;

public abstract class DBRepository<T extends HasID> implements Repository<T>, AutoCloseable {

    protected final Connection connection;

    public DBRepository(String dbUrl) throws DatabaseException {
        try {
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}