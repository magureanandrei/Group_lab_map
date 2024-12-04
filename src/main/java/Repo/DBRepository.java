package Repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Models.HasID;

public abstract class DBRepository<T extends HasID> implements Repository<T>, AutoCloseable {

    protected final Connection connection;

    public DBRepository(String dbUrl) {
        try {
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}