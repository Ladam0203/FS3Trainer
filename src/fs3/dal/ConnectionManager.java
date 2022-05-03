package fs3.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class ConnectionManager {
    private final SQLServerDataSource ds;
    protected ConnectionManager()
    {
        ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("FS3_RAM");
        ds.setPortNumber(1433);
        ds.setUser("CSe21B_1");
        ds.setPassword("CSe21B_1");
    }

    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}
