package pl.coderslab.web;

import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

@WebServlet("/app/recipe/edit")
public class RecipeEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeID = Integer.parseInt(req.getParameter("id"));
        RecipeDAO recipeDAO = new RecipeDAO();
        Recipe recipe = recipeDAO.readRecipe(recipeID);
        req.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/jsp/recipeEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeID = Integer.parseInt(req.getParameter("recipeID"));
        String recipeName = req.getParameter("recipeName");
        String recipeDescription = req.getParameter("recipeDescription");
        int preparationTime = Integer.parseInt(req.getParameter("preparationTime"));
        String preparation = req.getParameter("preparation");
        String ingredients = req.getParameter("ingredients");
        LocalDateTime created = LocalDateTime.parse(req.getParameter("created"));
        LocalDateTime updated = LocalDateTime.now();
        Recipe recipe = new Recipe(recipeID, recipeName, ingredients, recipeDescription, created, updated, preparationTime, preparation);

        RecipeDAO recipeDAO = new RecipeDAO();
        recipeDAO.update(recipe);

        resp.sendRedirect("/app/recipe/list");

    }
}
