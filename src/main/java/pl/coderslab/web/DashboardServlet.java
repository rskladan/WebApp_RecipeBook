package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.PlanDetailsDao;
import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        RecipeDAO recipeDAO = new RecipeDAO();
        PlanDao planDao = new PlanDao();
        PlanDetailsDao planDetailsDao = new PlanDetailsDao();

        Admins admins = (Admins) session.getAttribute("admin");
        int adminID = admins.getId();
        Plan plan = planDao.findLastAddedPlan(adminID);
        List<PlanDetails> planDetailsList = planDetailsDao.findLastAddedPlanDetails(adminID);

        req.setAttribute("numberOfRecipes", recipeDAO.numberOfRecipes(admins));
        req.setAttribute("numberOfPlans", planDao.numberOfPlans(admins));
        req.setAttribute("adminName", admins.getFirst_name());
        req.setAttribute("planName", plan.getName());
        req.setAttribute("planDetailsList", planDetailsList);

        getServletContext().getRequestDispatcher("/jsp/dashboard.jsp").forward(req,resp);


    }
}
