package servlets.auth;

import db.UserOperation;
import model.Error;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/enter")
public class EnterServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        try {
            if (UserOperation.checkUser(email, pass)) {
                HttpSession session = request.getSession();
                session.setAttribute("name", UserOperation.getUserName(email));
                session.setAttribute("id", UserOperation.getIdUser(email));
                response.sendRedirect("/myPage");
            } else {
                Error error = new Error();
                error.setMessage("Invalid e-mail or password");
                request.setAttribute("error", "Invalid e-mail or password");
                request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
