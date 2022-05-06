package fs3.dal.user;

import fs3.be.Student;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    String tableName = "Students";
    String[] columns = {"userId", "name"};

    String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public void setStudent(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                setObject(user, rs);
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
    }

    private void setObject(User user, ResultSet rs) throws SQLException {
        Student student = (Student) user;
        student.setName(rs.getString("name"));
    }
}
