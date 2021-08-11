package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class PlanListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Admins admins = (Admins) session.getAttribute("admin");
        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.findAllById(admins.getId());
        System.out.println(planList);
        req.setAttribute("planList",planList);
        getServletContext().getRequestDispatcher("/jsp/planList.jsp").forward(req,resp);


    }
}
