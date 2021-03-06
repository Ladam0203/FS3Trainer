package fs3.dal;

import java.util.Stack;

public class ConnectionManagerPool {
    private static ConnectionManagerPool instance = null;
    Stack<ConnectionManager> pool;

    private ConnectionManagerPool() {
        pool = new Stack<ConnectionManager>();
    }

    public static ConnectionManagerPool getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerPool();
        }
        return instance;
    }

    public ConnectionManager getConnectionManager() {
        if (pool.empty()) {
            return new ConnectionManager();
        } else {
            return pool.pop();
        }
    }

    public void returnConnectionManager(ConnectionManager connection) {
        pool.push(connection);
    }
}
