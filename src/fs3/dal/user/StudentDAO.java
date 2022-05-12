package fs3.dal.user;

import fs3.be.Student;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private String tableName = "Students";
    private String[] columns = {"userId", "name"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String selectAll = "SELECT * FROM " + tableName;

    public void setStudent(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = (Student) user;
                student.setName(rs.getString("name"));
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
    }
}
