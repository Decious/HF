package net.voinilo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex_01_Connection {
    public static void main(String[] args) {


        try (Connection connection = DriverManager.getConnection(CN.URL_DB, CN.USER_DB, CN.PASSWORD_DB)) {
        if (!connection.isClosed()) System.out.println("Connection established");
        if (connection.isClosed()) System.out.println("Connection interrupted");


        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }
}
