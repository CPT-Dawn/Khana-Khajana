<%-- Created by IntelliJ IDEA. User: tyagi Date: 07/04/2024 Time: 13:19 To
change this template use File | Settings | File Templates. --%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Khana Khajana - Recipe Recommendations</title>
    <link rel="stylesheet" href="style2.css" />
  </head>
  <body background="background2.jpeg">
    <div class="background"></div>
    <div class="container">
      <h1>Welcome to Khana Khajana</h1>
      <p>Find delicious recipes based on the ingredients you have!</p>
      <form id="ingredientForm">
        <label for="ingredients">Enter your ingredients:</label><br />
        <textarea
          id="ingredients"
          name="ingredients"
          rows="4"
          cols="40"
          placeholder="Enter ingredients separated by commas"
        ></textarea
        ><br />
        <button type="submit">Find Recipes</button>
      </form>
      <div id="recipeResults" class="hidden">
        <h2>Recommended Recipes:</h2>
        <ul id="recipeList"></ul>
      </div>
    </div>
    <script src="script2.js"></script>
  </body>
</html>
