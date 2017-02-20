package servlets;

import db.UserOperation;
import model.Error;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("name") == null) {
            System.out.println("Session is null");
            request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
        } else {
            System.out.println("Session isn't null");
            response.sendRedirect("jsp/page.jsp");
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("nickname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        String sex = request.getParameter("sex");

        User user = new User();
        user.setNickname(name);
        user.setEmail(email);
        user.setPassword(pass);
        user.setSex(sex);


        try {
            if (!UserOperation.checkEmail(email)){
                System.out.println("EMAIL ERROR");
                request.setCharacterEncoding("UTF-8");
                response.getWriter().print("error");
            } else {
                System.out.println("SUCCESS!!!");
                try {
                    user.setId(UserOperation.createStudent(user));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpSession session = request.getSession();
                session.setAttribute("name", name);
                session.setAttribute("id", user.getId());
                response.getWriter().print("success");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
