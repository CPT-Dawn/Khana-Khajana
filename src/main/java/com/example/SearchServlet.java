package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SearchServlet extends HttpServlet {
    Connection connection;
    PreparedStatement statement;

    public void init() throws ServletException {
        // Initialize database connection

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ingredients";
            String user = "root";
            String password = "141204";
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = connection.prepareStatement("SELECT * FROM ingredientsdataset WHERE `Cleaned-Ingredients` LIKE ? LIMIT 10");
            System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve user input (ingredients) from the form
        String ingredients = request.getParameter("ingredients");
        System.out.println(ingredients);
        try {
            // Set the ingredients parameter for the prepared statement
            statement.setString(1, "%" + ingredients + "%");

            // Execute the query to retrieve dishes matching the provided ingredients
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Dishes matching your ingredients:</h2>");
            while (resultSet.next()) {
                // Display the details of each dish
                String dishName = resultSet.getString("Recipe Name");
                String dishIngredients = resultSet.getString("Cleaned-Ingredients");
                String dishurl = resultSet.getString("URL");
                String dishtimetaken = resultSet.getString("Total Time In Mins");
                System.out.println(dishName);
                System.out.println(dishIngredients);
                System.out.println(dishurl);
                System.out.println(dishtimetaken);
                out.println("<p>Dish Name: " + dishName + "</p>");
                out.println("<p>Ingredients: " + dishIngredients + "</p>");
                out.println("<p>Ingredients: " + dishurl + "</p>");
                out.println("<p>Ingredients: " + dishtimetaken + "</p>");
                out.println("<hr>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error executing SQL statement.", e);
        }
    }

    public void destroy() {
        // Close database connection
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
