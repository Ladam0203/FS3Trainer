package fs3.dal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionManagerPoolTest {
    @Test
    public void testGetConnection() {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();

        Assertions.assertNotNull(cm);
    }
}
