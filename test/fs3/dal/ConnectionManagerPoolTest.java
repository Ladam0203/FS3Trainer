package fs3.dal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionManagerPoolTest {
    @Test
    public void testGetConnection() {
        Assertions.assertNotNull(ConnectionManagerPool.getInstance().getConnectionManager());
    }
}
