package net.voinilo;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static volatile Connection connection = null;
    public static Connection getConnection() throws SQLException{
        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        synchronized (CN.URL_DB){
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(CN.URL_DB,CN.USER_DB,CN.PASSWORD_DB);
                System.out.println("OK");
            }
        }

        return connection;
    }
}
