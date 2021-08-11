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

@WebServlet("/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.sendRedirect("/jsp/recipeAdd.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipeName = req.getParameter("recipeName");
        String recipeDescription = req.getParameter("recipeDescription");
        int recipePreparationTime = Integer.parseInt(req.getParameter("recipePreparationTime"));
        String preparation = req.getParameter("preparation");
        String ingredients = req.getParameter("ingredients");

        RecipeDAO recipeDAO = new RecipeDAO();
        Recipe recipe = new Recipe(recipeName, ingredients, recipeDescription, recipePreparationTime, preparation);
        HttpSession session = req.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        Recipe addedRecipe =recipeDAO.createRecipe(recipe, admin);

//        dopisac sprawdzenie czy udalo sie dodac przepis do bazy
//        jezeli tak to niech bedzie mozliwosc dodania kolejnego
//        a jezeli nie to wyrzuci na strone główną
//        w przyszłości trzeba dodać info że nie udało się utworzyć przepisu i żeby była możliwośc poprawienia tego na stronie formularza
        req.setAttribute("addedRecipe", addedRecipe);
        resp.sendRedirect("/app/recipe/add");
    }
}
