package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/app/plan/add")
public class PlanAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/planAdd.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        Date planCreated = new Date();

        Plan plan = new Plan(planName,planDescription,planCreated);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);

        response.sendRedirect("/app/plan/add");


    }
}
