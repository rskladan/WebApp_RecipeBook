package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        AdminsDao adminsDao = new AdminsDao();
        Admins adminToVerify = new Admins(email, password);
        Admins adminFromDatabase = adminsDao.findAdminsByEmail(email);

        if (isVerifiedUser(adminToVerify, adminFromDatabase)) {
            HttpSession session = req.getSession();
            session.setAttribute("admin", adminFromDatabase);
            resp.sendRedirect("/app");
        } else {
            String errorMessage = "Błędne dane logowania!";
            req.setAttribute("error", errorMessage);
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }

    }

    private boolean isVerifiedUser(Admins adminToVerify, Admins adminFromDatabase) {

        if (adminFromDatabase == null) return false;
        if (!adminToVerify.getEmail().equals(adminFromDatabase.getEmail())) return false;
        if (!BCrypt.checkpw(adminToVerify.getPassword(), adminFromDatabase.getPassword())) return false;
        return true;

    }
}
