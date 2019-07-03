package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StatusDao {
    private static final String READ_ALL_QUERY =
            "SELECT * FROM status ORDER BY id";

    public List<Status> readAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<Status> statuses = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setValue(resultSet.getString("value"));
                statuses.add(status);
            }
            return statuses;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }


}
