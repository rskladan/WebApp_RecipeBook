package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.PlanDetailsDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planId);
        PlanDetailsDao planDetailsDao = new PlanDetailsDao();
        List<PlanDetails> planDetailsList = planDetailsDao.findAllDetailsFromPlan(planId);
        Map<String, Integer> dayMap = new HashMap<>();

        for (int i = 0; i < planDetailsList.size(); i++) {
            dayMap.put(planDetailsList.get(i).getDayName(), 1);
        }

        req.setAttribute("plan", plan);
        req.setAttribute("planDetails", planDetailsList);
        req.setAttribute("dayMap", dayMap);

        getServletContext().getRequestDispatcher("/jsp/planDetails.jsp").forward(req, resp);
    }
}
