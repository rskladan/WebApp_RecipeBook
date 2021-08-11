package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/app/plan/edit")
public class PlanEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int planID = Integer.parseInt(req.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planID);
        req.setAttribute("plan",plan);
        getServletContext().getRequestDispatcher("/jsp/planEdit.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int planID = Integer.parseInt(req.getParameter("planID"));
        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");

        Plan plan = new Plan(planID,planName,planDescription);
        PlanDao planDao = new PlanDao();
        planDao.update(plan);
        resp.sendRedirect("/app/plan/list");
    }
}
