package fs3.dal.user;

import fs3.be.Student;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private final StudentDAO studentDAO = new StudentDAO();

    String tableName = "Users";
    String[] columns = {"id", "username", "password", "roleId"};
    String select = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = ? AND " + columns[2] + " = ?";

    public User readUser(String username, String password) throws Exception {
        User user = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = constructObject(rs);
            }
            else {
                return null;
            }

            if (Student.class.equals(user.getClass())) {
                studentDAO.setStudent(user);
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return user;
    }

    private User constructObject(ResultSet rs) throws Exception {
        User user = UserFactory.createUser(rs.getString(columns[1]), rs.getString(columns[2]), rs.getInt(columns[3]));
        user.setId(rs.getInt(columns[0]));
        return user;
    }
}
