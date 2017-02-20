package servlets.map;

import db.UserOperation;
import model.Post;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/map")
public class MapServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/map.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Post> list = null;
        try {
            list = UserOperation.getMarks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        if (list != null) {
            System.out.println("Marks not null");
//            for (Post post: list) {
            Post post = list.get(0);
                try {
                    jsonObject.put("name", UserOperation.getUserName(String.valueOf(post.getIdUser())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jsonObject.put("text", post.getText());
                jsonObject.put("datePost", post.getDate());
                jsonObject.put("coords", post.getCoords());

            resp.setContentType("application/json");
            resp.getWriter().print(jsonObject);
        } else{
            resp.getWriter().print("empty");
        }

    }
}
