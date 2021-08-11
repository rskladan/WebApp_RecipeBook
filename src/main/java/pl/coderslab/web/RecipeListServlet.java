package pl.coderslab.web;

import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/list")
public class RecipeListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        RecipeDAO recipeDAO = new RecipeDAO();

        List<Recipe> recipeList = recipeDAO.findAll(admin.getId());

        session.setAttribute("recipeList", recipeList);
        session.setAttribute("adminName",admin.getFirst_name());
        getServletContext().getRequestDispatcher("/jsp/recipeList.jsp").forward(req, resp);

    }
}
