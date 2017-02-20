package db;

import model.Post;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOperation {

    public static int createStudent(User user) throws Exception {
        int studentId = generateId("user") + 1;
        String sql = "INSERT INTO USER(id, nickname, email, password, sex) VALUES(?, ?, ?, ?, ?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        System.out.println("EMAIL IS " + user.getEmail());
        pstmt.setInt(1, studentId);
        pstmt.setString(2, user.getNickname());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPassword());
        pstmt.setString(5, user.getSex());
        DBUtil.beginTransaction();
        int result = pstmt.executeUpdate();
        if (result != 0) {
            DBUtil.commit();
            System.out.println("Commit");
        } else {
            DBUtil.rollback();
            System.out.println("Rollback");
        }
        DBUtil.closeDBUtil(null, pstmt, con);
        return studentId;
    }

    public static int createPost(Post post) throws Exception {
        int postId = generateId("post") + 1;
        String sql = "INSERT INTO POST(idPost, idUser, text, coords, datePost, location) VALUES(?, ?, ?, ?, ?, ?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, postId);
        pstmt.setInt(2, post.getIdUser());
        pstmt.setString(3, post.getText());
        pstmt.setString(4, post.getCoords());
        pstmt.setString(5, post.getDate());
        pstmt.setString(6, post.getLocation());
        DBUtil.beginTransaction();
        int result = pstmt.executeUpdate();
        if (result != 0) {
            DBUtil.commit();
            System.out.println("Commit");
        } else {
            DBUtil.rollback();
            System.out.println("Rollback");
        }
        DBUtil.closeDBUtil(null, pstmt, con);
        return postId;
    }

    private static int generateId(String table) {
        String queryUser = "SELECT MAX(id) from USER";
        String queryPost = "SELECT MAX(idPost) from POST";
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet;
        int res = 0;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            if (table.equals("user")) {
                resultSet = stmt.executeQuery(queryUser);
            } else {
                resultSet = stmt.executeQuery(queryPost);
            }
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DBUtil.closeDBUtil(null, stmt, con);
        return res;
    }

    public static boolean checkEmail(String email) throws Exception{
        String query = "SELECT id FROM USER WHERE email = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            DBUtil.closeDBUtil(null, preparedStatement, con);
            return false;
        } else {
            DBUtil.closeDBUtil(null, preparedStatement, con);
            return true;
        }
    }

    public static boolean checkUser(String email, String password) throws Exception{
        if (checkEmail(email)){
            System.out.println("There isn't user");
            return false;
        } else {
            String pass;
            String query = "SELECT password FROM USER WHERE email = ?";
            Connection con = DBUtil.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pass = resultSet.getString("password");
            } else {
                DBUtil.closeDBUtil(null, preparedStatement, con);
                return false;
            }
            DBUtil.closeDBUtil(null, preparedStatement, con);
            if (pass.equals(password)){
                return true;
            } else {
                return false;
            }
        }
    }
    public static String getUserName(String email) throws Exception{
        String query = "SELECT nickname FROM USER WHERE email = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        String nickname = null;
        if (resultSet.next()) {
            nickname = resultSet.getString("nickname");
        }
        DBUtil.closeDBUtil(null, preparedStatement, con);
        return nickname;
    }
    public static int getIdUser(String email) throws Exception{
        String query = "SELECT id FROM USER WHERE email = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        DBUtil.closeDBUtil(null, preparedStatement, con);
        return id;
    }

    public static ArrayList<Post> getPosts(int idUser) throws Exception{
        ArrayList<Post> list = new ArrayList<Post>();
        String query = "SELECT idPost, text, coords, datePost, location FROM POST WHERE idUser = ? ORDER BY idPost DESC";
        Connection con = DBUtil.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, idUser);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Post post = new Post();
            post.setIdPost(resultSet.getInt("idPost"));
            post.setText(resultSet.getString("text"));
            post.setCoords(resultSet.getString("coords"));
            post.setDate(resultSet.getString("datePost"));
            post.setLocation(resultSet.getString("location"));
            list.add(post);
        }
        DBUtil.closeDBUtil(null, preparedStatement, con);
        return list;
    }

    public static int deletePost(String idPost) throws Exception{
        String query = "DELETE FROM POST WHERE idPost ="+idPost;
        Connection con = DBUtil.getConnection();
//        PreparedStatement preparedStatement = con.prepareStatement(query);
        Statement stmt = null;
        stmt = con.createStatement();
        return stmt.executeUpdate(query);
    }

    public static ArrayList<Post> getMarks() throws Exception{
        ArrayList<Post> list = new ArrayList<Post>();
        String query = "SELECT text, datePost, location, coords FROM POST WHERE location != 'null' ";
        Connection con = DBUtil.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Post post = new Post();
            post.setText(resultSet.getString("text"));
            post.setDate(resultSet.getString("datePost"));
            post.setLocation(resultSet.getString("location"));
            post.setCoords(resultSet.getString("coords"));
            list.add(post);
        }
        DBUtil.closeDBUtil(null, preparedStatement, con);
        return list;
    }
}
