package fs3.dal.user;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAO {
    String tableName = "Teachers";
    String[] columns = {"userId", "name"};

    String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public void setTeacher(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Teacher teacher = (Teacher) user;
                teacher.setName(rs.getString("name"));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }
}
