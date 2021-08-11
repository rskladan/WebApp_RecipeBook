package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet ("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname= req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("repassword");

        if(isSamePassword(password, rePassword)){

            Admins admin = new Admins(name,surname,email,password,0,1);
            AdminsDao adminDao = new AdminsDao();
            adminDao.create(admin);
            resp.sendRedirect("/login");

        }else{

            String errorMessage = "Hasła nie pasują!";
            req.setAttribute("error",errorMessage);
            getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(req,resp);

        }


    }

    private boolean isSamePassword(String pass1, String pass2){

        if(!pass1.equals(pass2)) return false;
        return true;

    }
}
