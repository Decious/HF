package net.voinilo;

import net.voinilo.beans.Roles;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RolesCrud {
    public boolean create(Roles role) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO `roles`(`role`)" +
                    " VALUES ('%s')", role.getRole());
            statement.executeUpdate(sql);
            if (1== statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS)){
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()){
                    int id = keys.getInt(1);
                    role.setId(id);
                    return true;
                }
            }
            return (false);
        }
    }

    public boolean update(Roles role){
        return false;
    }

    public boolean read(Roles role){
        return false;
    }
    public boolean delete(Roles role){
        return false;
    }
}
