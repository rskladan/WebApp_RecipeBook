package pl.coderslab.web;

import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/details")
public class RecipeDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int recipeId = Integer.parseInt(req.getParameter("id"));
        RecipeDAO recipeDAO = new RecipeDAO();
        Recipe recipe = recipeDAO.readRecipe(recipeId);

        req.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/jsp/recipeDetails.jsp").forward(req, resp);
//        resp.sendRedirect("/jsp/recipeDetails.jsp");
    }
}
