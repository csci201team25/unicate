package csci201team25.unicate_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/comment-servlet")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JsonObject json = new Gson().fromJson(sb.toString(), JsonObject.class);
        String commentText = json.get("text").getAsString();
        int userId = json.get("userId").getAsInt();
        int actId = json.get("actId").getAsInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/unicate?serverTimezone=UTC",
                    "root", "root")) {

                try (PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Comments (userID, bodyText, actID) VALUES (?, ?, ?)")) {

                    stmt.setInt(1, userId);
                    stmt.setString(2, commentText);
                    stmt.setInt(3, actId);
                    stmt.executeUpdate();

                    resp.setStatus(HttpServletResponse.SC_OK);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String actIdParam = req.getParameter("actId");
        if (actIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Missing actId parameter\"}");
            return;
        }

        int actId;
        try {
            actId = Integer.parseInt(actIdParam);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid actId parameter\"}");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/unicate?serverTimezone=UTC",
                    "root", "root")) {

                String sql = "SELECT Comments.bodyText, Users.Username FROM Comments " +
                             "JOIN Users ON Comments.userID = Users.userID " +
                             "WHERE Comments.actID = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, actId);

                    try (var rs = stmt.executeQuery()) {
                        java.util.List<JsonObject> commentList = new java.util.ArrayList<>();

                        while (rs.next()) {
                            JsonObject comment = new JsonObject();
                            comment.addProperty("username", rs.getString("Username"));
                            comment.addProperty("bodyText", rs.getString("bodyText"));
                            commentList.add(comment);
                        }

                        String json = new Gson().toJson(commentList);
                        resp.getWriter().write(json);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Server error\"}");
        }
    }
}

