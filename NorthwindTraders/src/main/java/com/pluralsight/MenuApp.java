package com.pluralsight;

import java.sql.*;

import java.util.Scanner;

public class MenuApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("What do you want to do?");
            System.out.println("1) Display all products");
            System.out.println("2) Display all customers");
            System.out.println("3) Display all categories");
            System.out.println("4) Display all products in chosen category");
            System.out.println("0) Exit");
            System.out.print("Select an option: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Displaying all products...");
                    displayAllProducts(args);
                }
                case 2 -> {
                    System.out.println("Displaying all customers...");
                    displayAllCustomers(args);
                }
                case 3 -> {
                    System.out.println("Displaying all categories...");
                    displayAllCategories(args);
                }
                case 4 -> {
                    System.out.println("Displaying all categories...");
                    displayAllProductsinCategory(args);
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void displayAllProducts(String[] args){
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = args[0];
        String password = args[1];

        String query = "SELECT * FROM Products";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);){
            // Establishing connection
            try(ResultSet results = statement.executeQuery()) {
            System.out.printf("%-10s %-35s %-12s %-15s%n", "ProductId", "ProductName", "UnitPrice", "UnitsInStock");
            System.out.println("------------------------------------------------------------------------");

            // Processing the result set
            while (results.next()) {
                int productId = results.getInt("ProductID");
                String productName = results.getString("ProductName");
                double unitPrice = results.getDouble("UnitPrice");
                int unitsInStock = results.getInt("UnitsInStock");
                System.out.printf("%-10s %-35s %-12s %-15s%n", productId, productName, unitPrice, unitsInStock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayAllCustomers(String[] args){
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = args[0];
        String password = args[1];

        String query = "SELECT * FROM Customers";


        try  (Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);) {
            try(ResultSet results = statement.executeQuery()) {
            System.out.printf("%-25s %-35s %-20s %-20s %-12s%n", "ContactName", "CompanyName", "City", "Country", "Phone");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");

            // Processing the result set
            while (results.next()) {
                String contactName = results.getString("ContactName");
                String companyName = results.getString("CompanyName");
                String city = results.getString("City");
                String country = results.getString("Country");
                String phone = results.getString("Phone");
                System.out.printf("%-25s %-35s %-20s %-20s %-12s%n", contactName, companyName, city, country, phone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
                } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayAllCategories(String[] args){
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = args[0];
        String password = args[1];

        String query = "SELECT * FROM Categories";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);){
            // Executing query

            try(ResultSet results = statement.executeQuery()) {
            System.out.printf("%-15s %-10s%n", "CategoryId", "CategoryName");
            System.out.println("------------------------------------------------");

            // Processing the result set
            while (results.next()) {
                int categoryID = results.getInt("CategoryID");
                String categoryName = results.getString("CategoryName");

                System.out.printf("%-15s %-10s%n", categoryID, categoryName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayAllProductsinCategory(String[] args){
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = args[0];
        String password = args[1];

        Scanner input = new Scanner(System.in);

        System.out.print("Enter CategoryID of Products to Display: ");
        int categoryID = input.nextInt();

        String query = "SELECT * FROM Products WHERE CategoryID = ?";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);){
            // Executing query
            statement.setInt(1, categoryID);
            try(ResultSet results = statement.executeQuery()) {

                System.out.printf("%-10s %-35s %-12s %-15s%n", "ProductId", "ProductName", "UnitPrice", "UnitsInStock");
                System.out.println("------------------------------------------------------------------------");

                // Processing the result set
                while (results.next()) {
                    int productId = results.getInt("ProductID");
                    String productName = results.getString("ProductName");
                    double unitPrice = results.getDouble("UnitPrice");
                    int unitsInStock = results.getInt("UnitsInStock");
                    System.out.printf("%-10s %-35s %-12s %-15s%n", productId, productName, unitPrice, unitsInStock);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


