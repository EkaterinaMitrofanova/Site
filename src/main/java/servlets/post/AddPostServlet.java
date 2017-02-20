package servlets.post;

import db.UserOperation;
import model.Post;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/page.jsp").forward(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String c = request.getParameter("data");
        String text = request.getParameter("text");
        String date = request.getParameter("date");
        String coords = request.getParameter("coords");
        String location = request.getParameter("loc");
        System.out.println("POST TEXT :" + text);
        System.out.println("POST location :" + location);

        HttpSession session = request.getSession();
        Post post = new Post();
        post.setIdUser((Integer) session.getAttribute("id"));
        post.setText(text);
        post.setDate(date);
        if (location.equals("")){
            post.setLocation(null);
        } else {
            post.setLocation(location);
        }
        post.setCoords(coords);

        try {
            post.setIdPost(UserOperation.createPost(post));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", post.getIdPost());
        jsonObject.put("date", post.getDate());
        System.out.println("Post before send: " + post.getCoords());
        jsonObject.put("coords", post.getCoords());

        response.getWriter().print(jsonObject);
    }
}
