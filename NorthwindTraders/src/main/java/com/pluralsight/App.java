package com.pluralsight;

import java.sql.*;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

// load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 1. open a connection to the database
// use the database URL to point to the correct database
        Connection connection;
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/NorthWind",
                "root",
                "Branderson1!");

        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products" ;
        try {
            // Establishing connection
            PreparedStatement statement = connection.prepareStatement(query);
            // Executing query
            ResultSet results = statement.executeQuery();

            System.out.printf("%-10s %-35s %-12s %-15s%n", "ProductID", "ProductName", "UnitPrice", "UnitsInStock");
            System.out.println("-------------------------------------------------------------------------------");

            // Processing the result set
            while (results.next()) {
                // Replace with your column names and types
               int productId = results.getInt("ProductID");
               String productName = results.getString("ProductName");
               double unitPrice = results.getDouble("UnitPrice");
               int unitsInStock = results.getInt("UnitsInStock");

                System.out.printf("%-10d %-35s %-12.2f %-15d%n", productId, productName, unitPrice, unitsInStock);
            }

            // Closing resources
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
// 3. Close the connection
        connection.close();
    }
}
