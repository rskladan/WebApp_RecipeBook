package pl.coderslab.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/*")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

//        if there is no attribute ADMIN in the session, it means that the login verification was failed or there is no logged user
//        User then needs to be redirected to the /login page
//        this filter is valid only when servlets starts with /app/

        if(session.getAttribute("admin") == null) {
            getServletContext().getRequestDispatcher("/login").forward(req,res);
        }

        chain.doFilter(req, res);
    }
}
