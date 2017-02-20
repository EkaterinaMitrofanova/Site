package servlets;

import db.UserOperation;
import model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/myPage")
//@WebServlet(urlPatterns = {"/myPage/*"})
public class MyPage extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("name") == null){
            System.out.println("Session is closed");
            response.sendRedirect("/");
        } else {
            System.out.println("Session isn't closed");
            ArrayList<Post> list = null;
            try {
                list = UserOperation.getPosts((Integer) session.getAttribute("id"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (list != null){
                System.out.println("List isn't null");
                request.setAttribute("list", list);
            } else {
                System.out.println("List is null");
            }
            request.getRequestDispatcher("jsp/page.jsp").forward(request, response);
        }

    }


}
