package servlets.post;

import db.UserOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletePost")
public class DeletePost  extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idPost = req.getParameter("idPost");
        System.out.println("idPost : " + idPost);
        try {
            if (UserOperation.deletePost(idPost) != 0) {
                System.out.println("Post is deleted");
                resp.getWriter().print("200");
            } else {
                System.out.println("Post isn't deleted");
                resp.getWriter().print("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
